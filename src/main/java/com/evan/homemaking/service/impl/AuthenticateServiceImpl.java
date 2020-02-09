package com.evan.homemaking.service.impl;

import cn.hutool.core.lang.Validator;
import com.evan.homemaking.common.cache.UserCache;
import com.evan.homemaking.common.exception.BadRequestException;
import com.evan.homemaking.common.exception.NotFoundException;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.LoginParam;
import com.evan.homemaking.common.utils.HomemakingUtil;
import com.evan.homemaking.common.utils.SecurityUtil;
import com.evan.homemaking.security.token.AuthToken;
import com.evan.homemaking.service.AuthenticateService;
import com.evan.homemaking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.evan.homemaking.common.consts.Security.ACCESS_TOKEN_EXPIRED_SECONDS;

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

    @NonNull
    @Override
    public AuthToken authenticate(@NonNull LoginParam loginParam) {
        Assert.notNull(loginParam, "Login param must not be null");

        String mismatchTip = "用户名或者密码不正确";

        String userName = loginParam.getUsername();
        final User user;
        try {
            user = Validator.isEmail(userName) ? userService.getByEmailOfNonNull(userName) :
                    userService.getByAccountIdOfNonNull(userName);
        } catch (NotFoundException e) {
            log.error("Failed to find user by userName: " + userName, e);
            throw new BadRequestException(mismatchTip);
        }

        if (!userService.matchPassword(user, loginParam.getPassword())) {
            log.error("The password of loginParam is not match to the user password");

            throw new BadRequestException(mismatchTip);
        }
        if (userCache.getMultiple(SecurityUtil.buildAccessTokenKey(user), String.class).isPresent()) {
            //If this user is already logged in
            throw new BadRequestException("您已登录，请不要重复登录");
        }
        return buildAuthToken(user);
    }

    @Override
    public void clearToken() {
        User currentUser = userService.getCurrentUser();
        userCache.delete(SecurityUtil.buildAccessTokenKey(currentUser));
        log.info("用户缓存清除成功，userId:{}", currentUser.getId());
    }

    @NonNull
    private AuthToken buildAuthToken(User user) {
        Assert.notNull(user, "User must not be null");

        // Generate new token
        AuthToken token = new AuthToken();

        token.setAccessToken(HomemakingUtil.randomUUIDWithoutDash());
        token.setExpiredIn(ACCESS_TOKEN_EXPIRED_SECONDS);
        token.setRefreshToken(HomemakingUtil.randomUUIDWithoutDash());

        userCache.putMultiple(SecurityUtil.buildAccessTokenKey(user), token.getAccessToken(), ACCESS_TOKEN_EXPIRED_SECONDS);
        return token;
    }
}
