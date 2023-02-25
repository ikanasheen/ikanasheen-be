package com.binus.thesis.fisheryapp.base.exception;

import com.binus.thesis.fisheryapp.base.dto.Status;

public abstract class BaseException extends RuntimeException {

    protected static final String TYPE_APPLICATION = "APPLICATION ERROR";
    protected static final String TYPE_SYSTEMS = "SYSTEMS ERROR";
    protected static final String TYPE_ENGINE = "ENGINE ERROR";

    protected String type;
    protected String key;
    protected String moduleName;
    protected String parameter;
    protected Status status;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Status getStatus() {
        return status;
    }

    public String getKey() {
        return key;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getParameter() {
        return parameter;
    }

}
