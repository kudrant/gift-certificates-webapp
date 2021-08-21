package com.epam.esm.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @Autowired
    public GlobalControllerExceptionHandler() {
    }

    @ExceptionHandler({GiftCertificateControllerException.class})
    public ResponseEntity<Object> handleException(GiftCertificateControllerException ex) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("errorMessage", ex.getMessage());
        parameters.put("errorCode", ex.getErrorCode());
        return new ResponseEntity<>(parameters, ex.getHttpStatus());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleUnregisterException(RuntimeException ex) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("errorMessage", ex.getMessage());
        parameters.put("errorCode", 50000);
        return new ResponseEntity<>(parameters, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
