package com.Hanium.CarCamping.domain.dto.member;

import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMyInfoDto {
    private Long member_id;
    private String loginId;
    private String nickname;
    private int point;
    public static ResponseMyInfoDto convertToDto(Member member) {
        ResponseMyInfoDto responseMyInfoDto=new ResponseMyInfoDto();
        responseMyInfoDto.loginId= member.getEmail();
        responseMyInfoDto.member_id=member.getId();
        responseMyInfoDto.nickname=member.getNickname();
        responseMyInfoDto.point=member.getPoint();
        return responseMyInfoDto;
    }
}
