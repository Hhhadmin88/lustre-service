package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * @ClassName BeanUtilsException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/11 3:10
 */
public class BeanUtilException extends HomemakingException {
    public BeanUtilException(String message) {
        super(message);
    }

    public BeanUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
