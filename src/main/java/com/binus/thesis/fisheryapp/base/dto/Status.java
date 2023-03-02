package com.binus.thesis.fisheryapp.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(Include.NON_NULL)
@JsonView(Views.Basic.class)
public class Status implements BaseInterface {

    private static final long serialVersionUID = -3214983983781773869L;

    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_DESC = "Success";

    public static final String ERROR_EXIST_CODE = "405";
    public static final String ERROR_EXIST_DESC = "Data already exist";

    public static final String ERROR_NOTFOUND_CODE = "405";
    public static final String ERROR_NOTFOUND_DESC = "Data Not Found";

    public static final String ERROR_INVALID_DATA_CODE = "405";
    public static final String ERROR_INVALID_DATA_DESC = "Invalid Data";

    public static final String APP_ERROR_CODE = "500";
    public static final String APP_ERROR_DESC = "Application Error";

    public static final String SYS_ERROR_CODE = "500";
    public static final String SYS_ERROR_DESC = "Systems Error";

    public static final String ERROR_CODE = "500";
    public static final String ERROR_DESC = "Error";

    private String responseCode;

    private String responseDesc;

    private String responseMessage;

    private String stacktrace;

    public Status() {
    }

    public Status(String responseCode) {
        this.responseCode = responseCode;
        if (responseCode.equals(APP_ERROR_CODE)) {
            this.responseDesc = APP_ERROR_DESC;
        }
        if (responseCode.equals(SYS_ERROR_CODE)) {
            this.responseDesc = SYS_ERROR_DESC;
        }
        if (responseCode.equals(ERROR_CODE)) {
            this.responseDesc = ERROR_DESC;
        }
    }

    public Status(String responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }

    public Status(String responseCode, String responseDesc, String responseMessage) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.responseMessage = responseMessage;
    }

    public Status(String responseCode, String responseDesc, String responseMessage, String stacktrace) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.responseMessage = responseMessage;
        this.stacktrace = stacktrace;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    @Override
    public String toString() {
        return "Status{" +
                "responsecode='" + responseCode + '\'' +
                ", responsedesc='" + responseDesc + '\'' +
                ", responsemessage='" + responseMessage + '\'' +
                ", stacktrace='" + stacktrace + '\'' +
                '}';
    }

    public static Status DATA_ALREADY_EXIST(String message) {
        return new Status(ERROR_EXIST_CODE, ERROR_EXIST_DESC, message);
    }

    public static Status SUCCESS() {
        return SUCCESS("");
    }

    public static Status SUCCESS(String message) {
        return new Status(SUCCESS_CODE, SUCCESS_DESC, message);
    }

    public static Status DATA_NOT_FOUND(String message) {
        return new Status(ERROR_NOTFOUND_CODE, ERROR_NOTFOUND_DESC, message);
    }

    public static Status INVALID(String message) {
        return new Status(ERROR_INVALID_DATA_CODE, ERROR_INVALID_DATA_DESC, message);
    }

    public static Status APP_ERROR() {
        return APP_ERROR("");
    }

    public static Status APP_ERROR(String message) {
        return new Status(APP_ERROR_CODE, APP_ERROR_DESC, message);
    }

    public static Status SYS_ERROR() {
        return SYS_ERROR("");
    }

    public static Status SYS_ERROR(String message) {
        return new Status(SYS_ERROR_CODE, SYS_ERROR_DESC, message);
    }

    public static Status ERROR() {
        return ERROR("an error has occured");
    }

    public static Status ERROR(String message) {
        return new Status(ERROR_CODE, ERROR_DESC, message);
    }
}
