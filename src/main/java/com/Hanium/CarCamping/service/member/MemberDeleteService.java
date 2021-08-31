package com.Hanium.CarCamping.service.member;

import com.Hanium.CarCamping.Exception.NoSuchMemberException;
import com.Hanium.CarCamping.Exception.WrongPasswordException;
import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.member.checkDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberDeleteService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void deleteMember(String token, checkDto checkDto){
        Member member = memberRepository.findByEmail(jwtService.findEmailByJwt(token)).orElseThrow(NoSuchMemberException::new);
        if (!passwordEncoder.matches(checkDto.getCheck(), member.getPassword())) {
            throw new WrongPasswordException();
        }
        memberRepository.delete(member);
    }
}
