package com.Hanium.CarCamping.Exception;

public class AlreadyParticipateException extends RuntimeException {
    public AlreadyParticipateException() {
        super();
    }

    public AlreadyParticipateException(String message) {
        super(message);
    }
}
