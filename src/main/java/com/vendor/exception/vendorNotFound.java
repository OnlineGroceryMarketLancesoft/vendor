package com.vendor.exception;

import org.springframework.http.HttpStatus;

public class vendorNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;

    public vendorNotFound(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
