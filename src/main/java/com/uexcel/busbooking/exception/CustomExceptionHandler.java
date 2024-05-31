package com.uexcel.busbooking.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
@ResponseStatus
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    ErrorMessage errorMessage;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomExceptions(CustomException exception, WebRequest request) {

        if (exception.getErrorCode().equals("400")) {
            errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

        if (exception.getErrorCode().equals("401")) {
            errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        }

        if (exception.getErrorCode().equals("404")) {
            errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        if (exception.getErrorCode().equals("402")) {
            errorMessage = new ErrorMessage(HttpStatus.PAYMENT_REQUIRED, exception.getMessage());

        }

        if (exception.getErrorCode().equals("403")) {
            errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, exception.getMessage());

        }

        if (exception.getErrorCode().equals("409")) {
            errorMessage = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());

        }

        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception exception, WebRequest request ) {
        if (exception instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NullPointerException: " + exception.getMessage());
        }
        if (exception instanceof DataIntegrityViolationException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry we encountered error while storing the data! " + exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sorry we encountered an error: " + exception.getMessage());
    }

}
