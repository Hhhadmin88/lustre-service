package com.evan.homemaking.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @ClassName HomemakingException
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 16:13
 */
public abstract class HomemakingException extends RuntimeException {

    private Object errorData;

    public HomemakingException(String message) {
        super(message);
    }

    public HomemakingException(String message, Throwable cause) {
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
    public HomemakingException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
