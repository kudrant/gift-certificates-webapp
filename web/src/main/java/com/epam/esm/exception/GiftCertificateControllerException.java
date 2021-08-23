package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class GiftCertificateControllerException extends RuntimeException{

    private int errorCode;
    private HttpStatus httpStatus;

    public GiftCertificateControllerException() {
        super();
    }

    public GiftCertificateControllerException(String message, int errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public GiftCertificateControllerException(String message) {
        super(message);
    }

    public GiftCertificateControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public GiftCertificateControllerException(Throwable cause) {
        super(cause);
    }

    protected GiftCertificateControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
