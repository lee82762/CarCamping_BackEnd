package com.Hanium.CarCamping.domain.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateNickNameDto {
    private String nickname;

    @Builder
    public UpdateNickNameDto(String nickname) {
        this.nickname=nickname;
    }

}
