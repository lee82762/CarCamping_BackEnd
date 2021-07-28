package com.Hanium.CarCamping.config.security.filter;

import com.Hanium.CarCamping.config.security.jwt.BusinessLogicException;
import com.Hanium.CarCamping.config.security.jwt.ErrorCodeType;

public class WrongPasswordException extends BusinessLogicException {
    public WrongPasswordException() {
        super(ErrorCodeType.WRONG_PASSWORD);
    }
}
