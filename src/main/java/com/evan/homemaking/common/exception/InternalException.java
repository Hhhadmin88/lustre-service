package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName InternalException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 0:39
 */
public class InternalException extends HomemakingException {
    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
