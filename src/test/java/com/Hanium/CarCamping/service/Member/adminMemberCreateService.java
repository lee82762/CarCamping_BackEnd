package com.Hanium.CarCamping.service.Member;

import com.Hanium.CarCamping.domain.dto.member.createDto;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class adminMemberCreateService {
    @Autowired
    MemberCreateService memberCreateService;
    @Autowired
    MemberRepository memberRepository;


    @Rollback(value = false)
    @Test
    public void 어드민회원() throws Exception {
        //given

        //when


        //then

    }
}
