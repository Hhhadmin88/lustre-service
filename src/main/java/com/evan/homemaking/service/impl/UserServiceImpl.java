package com.evan.homemaking.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.digest.BCrypt;
import com.evan.homemaking.common.enums.RoleEnum;
import com.evan.homemaking.common.exception.BadRequestException;
import com.evan.homemaking.common.exception.NotFoundException;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.RegisterParam;
import com.evan.homemaking.repository.UserRepository;
import com.evan.homemaking.security.authentication.AuthenticationImpl;
import com.evan.homemaking.security.context.SecurityContextHolder;
import com.evan.homemaking.security.context.SecurityContextImpl;
import com.evan.homemaking.security.support.UserDetail;
import com.evan.homemaking.service.UserService;
import com.evan.homemaking.service.base.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 14:20
 */
@Service
@Slf4j
public class UserServiceImpl extends AbstractCrudService<User, Integer> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    @NonNull
    public User getByAccountId(@NonNull String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    @Override
    @NonNull
    public User getByEmail(@NonNull String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void registerUser(@NonNull RegisterParam registerParam) {
        checkRoleParam(registerParam.getRole());
        checkAccountIdAndEmailDuplicated(registerParam);
        User user = new User();
        BeanUtils.copyProperties(registerParam, user);
        setPassword(user);
        userRepository.save(user);
    }

    private void checkAccountIdAndEmailDuplicated(RegisterParam registerParam) {
        if (userRepository.findByAccountId(registerParam.getAccountId()) != null) {
            throw new BadRequestException("用户名不能重复");
        }
        if (userRepository.findByEmail(registerParam.getEmail()) != null) {
            throw new BadRequestException("邮箱不能重复");
        }
    }

    @Override
    public void setPassword(@NonNull User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    }

    @Override
    public boolean matchPassword(@NonNull User user, @NonNull String plainPassword) {
        Assert.notNull(user, "User must not be null");

        return StringUtils.isNotBlank(user.getPassword()) && BCrypt.checkpw(plainPassword, user.getPassword());
    }

    @NonNull
    @Override
    public Optional<User> getCurrentRequestUser(@NonNull String userName) {
        User user = Validator.isEmail(userName) ?
                getByEmail(userName) : getByAccountId(userName);
        Optional.ofNullable(user).orElseThrow(() -> new NotFoundException("当前登录用户" + userName + "不存在"));
        return Optional.of(user);
    }

    @Override
    public void storeCurrentUser(@NonNull User user) {
        SecurityContextHolder.setContext(new SecurityContextImpl(new AuthenticationImpl(new UserDetail(user))));
    }

    @NonNull
    @Override
    public User getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getUserDetail().getUser();
    }

    private void checkRoleParam(@NonNull String role) {
        if (RoleEnum.ADMIN.getRole().equals(role)) {

            log.error("User can not register to be an admin.");
            throw new BadRequestException("无法通过注册成为管理员，请从后台添加");
        }
        if (Integer.parseInt(role) > Integer.parseInt(RoleEnum.ADMIN.getRole())) {
            log.error("User role param is illegal.");
            throw new BadRequestException("用户角色标识参数不合法，请重新填写");
        }
    }
}
