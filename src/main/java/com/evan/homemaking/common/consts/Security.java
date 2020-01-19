package com.evan.homemaking.common.consts;

/**
 * @ClassName Security
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 0:16
 */
public class Security {
    /**
     * Access token cache prefix.
     */
    public static final String ACCESS_TOKEN_CACHE_PREFIX = "homemaking.user.access.token.";

    /**
     * Refresh token cache prefix.
     */
    public final static String REFRESH_TOKEN_CACHE_PREFIX = "homemaking.user.refresh.token.";

    /**
     * Token expire time.
     */
    public static final int ACCESS_TOKEN_EXPIRED_SECONDS = 86400;

    /**
     * Register request uri.
     */
    public static final String REGISTER_REQUEST_URI = "/api/user/register";

    /**
     * Login request uri.
     */
    public static final String LOGIN_REQUEST_URI = "/api/user/login";

    /**
     * Request access token.
     */
    public static final String REQUEST_ACCESS_TOKEN = "User-Authorization";

    /**
     * Request username in header.
     */
    public static final String REQUEST_USER_ACCOUNT = "User-Account";

    /**
     * Cache check expiration interval.
     */
    public static final int CACHE_CHECK_EXPIRATION_INTERVAL_MILLI = 60 * 1000 * 10;
}
