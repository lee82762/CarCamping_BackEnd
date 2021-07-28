package com.Hanium.CarCamping.domain.dto.member;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateDto {
    private String password;
    private String nickname;

    @Builder
    public UpdateDto(String password, String nickname) {
        this.password = password;
        this.nickname=nickname;
    }
}
