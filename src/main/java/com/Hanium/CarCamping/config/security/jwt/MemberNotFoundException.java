package com.Hanium.CarCamping.config.security.jwt;

public class MemberNotFoundException extends BusinessLogicException {
    public MemberNotFoundException() {
        super(ErrorCodeType.MEMBER_NOT_FOUND);
    }
}

