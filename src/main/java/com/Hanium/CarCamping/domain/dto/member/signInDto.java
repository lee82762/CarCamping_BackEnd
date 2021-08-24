package com.Hanium.CarCamping.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class signInDto {
    private String email;
    private String password;

    @Builder
    public signInDto(String email,String password) {
        this.email = email;
        this.password = password;
    }
}
