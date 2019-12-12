package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName NotFoundException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 14:31
 */
public class BadRequestException extends HomemakingException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
