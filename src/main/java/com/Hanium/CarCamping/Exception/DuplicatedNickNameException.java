package com.Hanium.CarCamping.Exception;

public class DuplicatedNickNameException extends RuntimeException {
    public DuplicatedNickNameException() {
        super();
    }

    public DuplicatedNickNameException(String message) {
        super(message);
    }
}
