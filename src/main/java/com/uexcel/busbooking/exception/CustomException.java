package com.uexcel.busbooking.exception;

import lombok.Getter;

@Getter
    public class CustomException extends RuntimeException {
        private String errorCode;
        private String errorMessage;

        public CustomException() {
        }

        public CustomException(String message) {
            super(message);
        }

        public CustomException(String message, Throwable cause) {
            super(message, cause);
        }

        public CustomException(Throwable cause) {
            super(cause);
        }

        public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

        public CustomException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
            this.errorMessage = message;
        }
    }

