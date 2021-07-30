package com.Hanium.CarCamping.Exception;

public class NoSuchCampSiteException extends RuntimeException {
    public NoSuchCampSiteException() {


    }

    public NoSuchCampSiteException(String message) {
        super(message);
    }
}
