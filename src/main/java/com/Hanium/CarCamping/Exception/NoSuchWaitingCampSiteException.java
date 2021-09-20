package com.Hanium.CarCamping.Exception;

public class NoSuchWaitingCampSiteException extends RuntimeException {
    public NoSuchWaitingCampSiteException() {
        super();
    }

    public NoSuchWaitingCampSiteException(String message) {
        super(message);
    }
}
