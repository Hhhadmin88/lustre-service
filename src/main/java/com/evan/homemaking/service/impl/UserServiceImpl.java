package com.evan.homemaking.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.digest.BCrypt;
import com.alibaba.druid.sql.repository.SchemaResolveVisitor;
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
    public User getByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void registerUser(RegisterParam registerParam) {
        User user = new User();
        BeanUtils.copyProperties(registerParam, user);
        setPassword(user);
        //TODO Verify username and password are duplicates
        userRepository.save(user);
    }

    @Override
    public void setPassword(@NonNull User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    }

    @Override
    public boolean matchPassword(User user, String plainPassword) {
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
}
