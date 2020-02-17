package com.evan.lustre.service;

import com.evan.lustre.common.model.param.LoginParam;
import com.evan.lustre.security.token.AuthToken;
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
