package com.evan.lustre.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * @ClassName InternalException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 0:39
 */
public class InternalExceptionAbstract extends AbstractLustreException {
    public InternalExceptionAbstract(String message) {
        super(message);
    }

    public InternalExceptionAbstract(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
