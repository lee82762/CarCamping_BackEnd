package com.Hanium.CarCamping.domain.entity.member;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    //값 수정
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;

}
