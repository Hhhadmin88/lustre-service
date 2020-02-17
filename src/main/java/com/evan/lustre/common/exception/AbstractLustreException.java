package com.evan.lustre.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @ClassName AbstractLustreException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 16:13
 */
public abstract class AbstractLustreException extends RuntimeException {

    private Object errorData;

    public AbstractLustreException(String message) {
        super(message);
    }

    public AbstractLustreException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Gets a HttpStatus.
     *
     * @return httpStatus.
     */
    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public AbstractLustreException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
