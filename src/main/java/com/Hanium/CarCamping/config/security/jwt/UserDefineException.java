package com.Hanium.CarCamping.config.security.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDefineException extends RuntimeException {
    private String originalErrorMessage;

    public UserDefineException(String message) {
        super(message);
    }

    @Builder
    public UserDefineException(String message, String originalErrorMessage) {
        super(message);
        this.originalErrorMessage = originalErrorMessage;
    }
}
