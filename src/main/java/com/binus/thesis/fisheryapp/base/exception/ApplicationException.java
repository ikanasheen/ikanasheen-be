package com.binus.thesis.fisheryapp.base.exception;


import com.binus.thesis.fisheryapp.base.dto.Status;

public class ApplicationException extends BaseException {


    public ApplicationException() {
        super();
        type = TYPE_APPLICATION;
    }

    public ApplicationException(String message) {
        super(message);
        type = TYPE_APPLICATION;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        type = TYPE_APPLICATION;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
        type = TYPE_APPLICATION;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        type = TYPE_APPLICATION;
    }

    public ApplicationException(Status status) {
        super(status.getResponseMessage());
        this.status = status;
        type = TYPE_APPLICATION;
    }

    public ApplicationException(String key, String moduleName, String parameter) {
        super(String.format("%s not found with %s : '%s'", key, moduleName, parameter));
        this.key = key;
        this.moduleName = moduleName;
        this.parameter = parameter;
        type = TYPE_APPLICATION;
    }

}
