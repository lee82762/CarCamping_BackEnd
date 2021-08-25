package com.Hanium.CarCamping.service.Member;

import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.domain.entity.member.Role;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberCreateServiceTest {


    @Autowired
    MemberCreateService memberCreateService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 사용자_등록() throws Exception {

        Member member= build();
        System.out.println(member.getEmail());
        System.out.println(member.getPassword());



    }
    public static Member build(){
        return Member.builder()
                .email("test1@naver.com1")
                .password("1234")
                .nickname("테스트")
                .point(1)
                .role(Role.USER)
                .build();
    }

}