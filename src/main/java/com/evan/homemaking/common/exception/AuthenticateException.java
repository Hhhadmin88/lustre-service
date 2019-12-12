package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName AuthenticateException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 20:28
 */
public class AuthenticateException extends HomemakingException {
    public AuthenticateException(String message) {
        super(message);
    }

    public AuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
