package com.Hanium.CarCamping.Exception;

import com.Hanium.CarCamping.config.security.jwt.BusinessLogicException;
import com.Hanium.CarCamping.config.security.jwt.ErrorCodeType;

public class MemberNotFoundException extends BusinessLogicException {
    public MemberNotFoundException() {
        super(ErrorCodeType.MEMBER_NOT_FOUND);
    }
}

