package com.habibullahdm.auth.exception;

import com.habibullahdm.auth.utils.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleService(ServiceException ex) {
        var error = ex.getErrorCode();
        var body = new ErrorResponse(
                error.getCode(),
                error.getMessage(),
                ZonedDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        var body = new ErrorResponse(
                ErrorCode.GENERAL_ERROR.getCode(),
                ErrorCode.GENERAL_ERROR.getMessage(),
                ZonedDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(body);
    }
}
