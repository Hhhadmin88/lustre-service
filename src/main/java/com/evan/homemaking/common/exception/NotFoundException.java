package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName NotFoundException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 14:31
 */
public class NotFoundException extends HomemakingException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
