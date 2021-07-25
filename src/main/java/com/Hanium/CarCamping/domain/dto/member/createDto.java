package com.Hanium.CarCamping.domain.dto.member;


import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class createDto {

    private String email;
    private String password;
    private String nickname;
    private Integer point;

    public Member of(){
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .point(this.point)
                .role("USER")
                .build();
    }

    @Builder
    public createDto(String email,String password,String nickname,Integer point) {
        this.email = email;
        this.password = password;
        this.nickname=nickname;
        this.point=point;
    }
}