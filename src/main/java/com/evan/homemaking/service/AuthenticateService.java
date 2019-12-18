package com.evan.homemaking.service;

import com.evan.homemaking.common.model.param.LoginParam;
import com.evan.homemaking.security.token.AuthToken;
import org.springframework.lang.NonNull;

/**
 * @ClassName authenticateService
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 11:17
 */
public interface AuthenticateService {
    /**
     * Expired seconds.
     */
    int ACCESS_TOKEN_EXPIRED_SECONDS = 3600;

    int REFRESH_TOKEN_EXPIRED_DAYS = 30;

    /**
     * Authenticates.
     *
     * @param loginParam login param must not be null
     * @return authentication token
     */
    @NonNull
    AuthToken authenticate(@NonNull LoginParam loginParam);

    /**
     * Clears authentication.
     */
    void clearToken();
}
