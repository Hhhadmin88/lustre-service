package com.evan.lustre.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * @ClassName NoAuthorizedException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/12 0:42
 */
public class UnAuthorizedExceptionAbstract extends AbstractLustreException {
    public UnAuthorizedExceptionAbstract(String message) {
        super(message);
    }

    public UnAuthorizedExceptionAbstract(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
