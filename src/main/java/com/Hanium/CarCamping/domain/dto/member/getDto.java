package com.Hanium.CarCamping.domain.dto.member;

import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class getDto {

    private Long id;
    private String email;

    @Builder
    public getDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }



    public static getDto toDto(Member member){
        return getDto.builder()
                .email(member.getEmail())
                .id(member.getId())
                .build();
    }
}
