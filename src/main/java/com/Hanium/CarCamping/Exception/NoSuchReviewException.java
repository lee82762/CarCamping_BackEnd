package com.Hanium.CarCamping.Exception;

public class NoSuchReviewException extends RuntimeException {
    public NoSuchReviewException() {
        super();
    }

    public NoSuchReviewException(String message) {
        super(message);
    }
}
