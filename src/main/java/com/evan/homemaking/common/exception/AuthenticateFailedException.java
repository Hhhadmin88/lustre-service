package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName AuthenticateException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 20:28
 */
public class AuthenticateFailedException extends HomemakingException {
    public AuthenticateFailedException(String message) {
        super(message);
    }

    public AuthenticateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
