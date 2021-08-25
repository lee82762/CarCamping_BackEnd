package com.Hanium.CarCamping.Exception;

public class DuplicateCampSiteException extends RuntimeException {
    public DuplicateCampSiteException() {
        super();
    }

    public DuplicateCampSiteException(String message) {
        super(message);
    }
}
