package com.Hanium.CarCamping.Exception;

public class NoSuchMemberException extends RuntimeException {
    public NoSuchMemberException() {
        super();
    }

    public NoSuchMemberException(String message) {
        super(message);
    }
}
