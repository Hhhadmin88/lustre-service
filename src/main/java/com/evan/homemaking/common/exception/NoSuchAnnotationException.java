package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * @ClassName NoSuchAnnotationException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/13 13:30
 */
public class NoSuchAnnotationException extends HomemakingException {
    public NoSuchAnnotationException(String message) {
        super(message);
    }

    public NoSuchAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
