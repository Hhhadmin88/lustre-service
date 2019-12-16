package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * @ClassName NotLoginException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/16 16:36
 */
public class NotLoginException extends HomemakingException {
    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
