package com.Hanium.CarCamping.domain.dto.member;

import lombok.Builder;
import lombok.Data;

@Data
public class signInDto {
    private String email;
    private String password;

    @Builder
    public signInDto(String email,String password) {
        this.email = email;
        this.password = password;
    }
}
