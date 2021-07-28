package com.Hanium.CarCamping.config.security.filter;

import com.Hanium.CarCamping.config.security.jwt.BusinessLogicException;
import com.Hanium.CarCamping.config.security.jwt.ErrorCodeType;

public class DuplicatedEmailException extends BusinessLogicException {
    public DuplicatedEmailException() {
        super(ErrorCodeType.DUPLICATED_EMAIL);
    }
}

