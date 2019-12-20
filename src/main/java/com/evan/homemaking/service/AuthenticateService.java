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
