package com.evan.lustre.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * @ClassName NotFoundException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 14:31
 */
public class BadRequestExceptionAbstract extends AbstractLustreException {

    public BadRequestExceptionAbstract(String message) {
        super(message);
    }

    public BadRequestExceptionAbstract(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
