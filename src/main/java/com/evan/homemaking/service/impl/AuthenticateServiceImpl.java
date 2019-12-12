package com.evan.homemaking.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.Validator;
import com.evan.homemaking.common.cache.UserCache;
import com.evan.homemaking.common.exception.BadRequestException;
import com.evan.homemaking.common.exception.NotFoundException;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.LoginParam;
import com.evan.homemaking.common.utils.SecurityUtil;
import com.evan.homemaking.security.context.SecurityContext;
import com.evan.homemaking.security.context.SecurityContextHolder;
import com.evan.homemaking.security.token.AuthToken;
import com.evan.homemaking.service.AuthenticateService;
import com.evan.homemaking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @ClassName AuthenticateServiceImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 11:23
 */
@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {
    private final UserService userService;

    private final UserCache userCache;

    public AuthenticateServiceImpl(UserService userService, UserCache userCache) {
        this.userService = userService;
        this.userCache = userCache;
    }

    @Override
    public AuthToken authenticate(LoginParam loginParam) {
        Assert.notNull(loginParam, "Login param must not be null");

        String mismatchTip = "用户名或者密码不正确";

        String userName = loginParam.getUsername();
        final User user;
        try {
            user = Validator.isEmail(userName) ? userService.getByEmail(userName) :
                    userService.getByAccountId(userName);
        } catch (NotFoundException e) {
            log.error("Failed to find user by userName: " + userName, e);
            throw new BadRequestException(mismatchTip);
        }
        Optional.ofNullable(user).orElseThrow(()->new BadRequestException("该用户不存在"));
        if (!userService.matchPassword(user, loginParam.getPassword())) {
            log.error("The password of loginParam is not match to the user password");

            throw new BadRequestException(mismatchTip);
        }
        if (SecurityContextHolder.getContext().isAuthenticated()) {
            //If this user is already logged in
            throw new BadRequestException("您已登录，请不要重复登录");
        }

        return buildAuthToken(user);
    }

    @Override
    public void clearToken() {

    }

    @NonNull
    private AuthToken buildAuthToken(User user) {
        Assert.notNull(user, "User must not be null");

        // Generate new token
        AuthToken token = new AuthToken();

        token.setAccessToken(UUID.randomUUID().toString());
        token.setExpiredIn(ACCESS_TOKEN_EXPIRED_SECONDS);
        token.setRefreshToken(UUID.randomUUID().toString());

        userCache.putMultiple(SecurityUtil.buildAccessTokenKey(user), token.getAccessToken(), ACCESS_TOKEN_EXPIRED_SECONDS);
        return token;
    }
}
