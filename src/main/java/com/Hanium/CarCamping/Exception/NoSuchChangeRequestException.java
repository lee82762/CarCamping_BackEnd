package com.Hanium.CarCamping.Exception;

public class NoSuchChangeRequestException extends RuntimeException {
    public NoSuchChangeRequestException() {
        super();
    }

    public NoSuchChangeRequestException(String message) {
        super(message);
    }
}
