package com.Hanium.CarCamping.service.member;

import com.Hanium.CarCamping.Exception.WrongPasswordException;
import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.Exception.MemberNotFoundException;
import com.Hanium.CarCamping.domain.dto.member.signInDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignInService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String signIn(signInDto memberSignInDto) {

        Member member = memberRepository.findByEmail(memberSignInDto.getEmail()).orElseThrow(MemberNotFoundException::new);
        checkPw(memberSignInDto,member);


        return jwtService.createJwt(member.getEmail());
    }

    private void checkPw(signInDto memberSignInDto, Member member) {
        if(passwordEncoder.matches(memberSignInDto.getPassword(), member.getPassword()))
            return;
        throw new WrongPasswordException();
    }
}
