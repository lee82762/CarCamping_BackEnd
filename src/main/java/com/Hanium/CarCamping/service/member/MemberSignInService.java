package com.Hanium.CarCamping.service.member;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.config.security.jwt.MemberNotFoundException;
import com.Hanium.CarCamping.domain.dto.member.signInDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignInService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public String signIn(signInDto memberSignInDto) {
        Member member = memberRepository.findByEmail(memberSignInDto.getEmail()).orElseThrow(MemberNotFoundException::new);
        return jwtService.createJwt(member.getEmail());
    }
}
