package com.evan.lustre.common.utils;

import com.evan.lustre.common.model.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import static com.evan.lustre.common.consts.Security.ACCESS_TOKEN_CACHE_PREFIX;
import static com.evan.lustre.common.consts.Security.REFRESH_TOKEN_CACHE_PREFIX;

/**
 * @ClassName SecurityUtil
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 0:20
 */
public class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * Build a  access token key.
     *
     * @param user user object.
     * @return key of access token.
     */
    @NonNull
    public static String buildAccessTokenKey(@NonNull User user) {
        Assert.notNull(user, "User must not be null");

        return ACCESS_TOKEN_CACHE_PREFIX + user.getId();
    }

    /**
     * Build a refresh token key.
     *
     * @param user user object.
     * @return key of refresh token.
     */
    @NonNull
    public static String buildRefreshTokenKey(@NonNull User user) {
        Assert.notNull(user, "User must not be null");

        return REFRESH_TOKEN_CACHE_PREFIX + user.getId();
    }
}
