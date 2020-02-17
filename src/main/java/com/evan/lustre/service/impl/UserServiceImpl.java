package com.evan.lustre.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.digest.BCrypt;
import com.evan.lustre.common.enums.RoleEnum;
import com.evan.lustre.common.exception.BadRequestExceptionAbstract;
import com.evan.lustre.common.exception.NotFoundExceptionAbstract;
import com.evan.lustre.common.exception.UnAuthorizedExceptionAbstract;
import com.evan.lustre.common.model.dto.UserDTO;
import com.evan.lustre.common.model.entity.User;
import com.evan.lustre.common.model.param.RegisterParam;
import com.evan.lustre.common.model.param.UserParam;
import com.evan.lustre.repository.UserRepository;
import com.evan.lustre.security.authentication.AuthenticationImpl;
import com.evan.lustre.security.context.SecurityContextHolder;
import com.evan.lustre.security.context.SecurityContextImpl;
import com.evan.lustre.security.support.UserDetail;
import com.evan.lustre.service.UserService;
import com.evan.lustre.service.base.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;
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
    public Optional<User> getByAccountId(@NonNull String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    @Override
    @NonNull
    public Optional<User> getByEmail(@NonNull String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByAccountIdOfNonNull(String accountId) {
        return getByAccountId(accountId).orElseThrow(() -> new NotFoundExceptionAbstract("当前账号不存在"));
    }

    @Override
    public User getByEmailOfNonNull(String email) {
        return getByEmail(email).orElseThrow(() -> new NotFoundExceptionAbstract("当前邮箱不存在"));
    }

    @Override
    public void registerUser(@NonNull RegisterParam registerParam) {
        checkRoleParam(registerParam.getRole());
        checkPropertyDuplicated(registerParam);
        User user = registerParam.convertTo();
        setEncryptedPassword(user);
        userRepository.save(user);
    }

    @Override
    public UserDTO updateOne(@NonNull Integer userId, @NonNull UserParam userParam) {
        User currentUser = getCurrentUser();
        Optional<User> targetUserOptional = userRepository.findById(userId);
        Boolean checkResult = targetUserOptional.map(
                user -> {
                    boolean isCurrentUser = currentUser.getAccountId().equals(user.getAccountId());
                    boolean isAdmin = RoleEnum.ADMIN.getRole().equals(currentUser.getRole());
                    return isCurrentUser || isAdmin;
                }
        ).orElseThrow(() -> new NotFoundExceptionAbstract("没有找到当前id对应的user"));
        User targetUser = targetUserOptional.get();
        if (!checkResult) {
            log.error("Current user have not the right that update target user." +
                    "current user Id:{},target user Id:{}", currentUser.getId(), targetUser.getId());
            throw new UnAuthorizedExceptionAbstract("更新失败，当前用户不具备更新目标用户的权限");
        }
        userParam.update(targetUser);
        return new UserDTO().convertFrom(update(targetUser));
    }

    private void checkPropertyDuplicated(@NonNull RegisterParam registerParam) {
        if (userRepository.existsUserByAccountId(registerParam.getAccountId())) {
            throw new BadRequestExceptionAbstract("账户ID不能重复,请重新填写");
        } else if (userRepository.existsUserByNickName(registerParam.getNickName())) {
            throw new BadRequestExceptionAbstract("当前昵称已被使用,请重新填写");
        } else if (userRepository.existsUserByEmail(registerParam.getEmail())) {
            throw new BadRequestExceptionAbstract("当前邮箱已用于注册,请重新填写");
        } else if (userRepository.existsUserByPhoneNumber(registerParam.getPhoneNumber())) {
            throw new BadRequestExceptionAbstract("当前电话已用于注册,请重新填写");
        }
    }

    @Override
    public void setEncryptedPassword(@NonNull User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    }

    @Override
    public boolean matchPassword(@NonNull User user, @NonNull String plainPassword) {
        Assert.notNull(user, "User must not be null");

        return StringUtils.isNotBlank(user.getPassword()) && BCrypt.checkpw(plainPassword, user.getPassword());
    }

    @Override
    public User getOneUserByUserName(@NonNull String userName) {
        return Validator.isEmail(userName) ? getByEmailOfNonNull(userName) :
                getByAccountIdOfNonNull(userName);
    }

    @Override
    public User getOneUserById(@NonNull Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundExceptionAbstract("没有找到userId:" + userId + "对应的用户"));
    }

    @Override
    public void storeCurrentUser(@NonNull User user) {
        SecurityContextHolder.setContext(new SecurityContextImpl(new AuthenticationImpl(new UserDetail(user))));
    }

    @Override
    public String convertUserIdToName(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(User::getNickName)
                .orElseThrow(() -> new NotFoundExceptionAbstract("当前查找userId:" + userId + "对应的用户不存在"));
    }

    @NonNull
    @Override
    public User getCurrentUser() {
        return Objects.requireNonNull(SecurityContextHolder.getContext()
                .getAuthentication()).getUserDetail().getUser();
    }

    private void checkRoleParam(@NonNull Integer role) {
        if (RoleEnum.ADMIN.getRole().equals(role)) {
            log.error("User can not register to be an admin.");
            throw new BadRequestExceptionAbstract("无法通过注册成为管理员，请从后台添加");
        }
        if (role > RoleEnum.ADMIN.getRole()) {
            log.error("User role param is illegal.");
            throw new BadRequestExceptionAbstract("用户角色标识参数不合法，请重新填写");
        }
    }
}
