package com.farmmate.global.error;

public record ErrorResponse(String errorClassName, String errorMessage) {

    public static ErrorResponse of(String errorClassName, String errorMessage) {
        return new ErrorResponse(errorClassName, errorMessage);
    }
}
