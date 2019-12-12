package com.evan.homemaking.security.authentication;

import com.evan.homemaking.security.support.UserDetail;
import org.springframework.lang.NonNull;

/**
 * @ClassName AuthenticationImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 20:36
 */
public class AuthenticationImpl implements Authentication {
    private final UserDetail userDetail;

    public AuthenticationImpl(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @NonNull
    @Override
    public UserDetail getUserDetail() {
        return userDetail;
    }
}
