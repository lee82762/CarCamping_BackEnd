package com.Hanium.CarCamping.service.member;

import com.Hanium.CarCamping.config.security.filter.DuplicatedEmailException;
import com.Hanium.CarCamping.domain.dto.member.createDto;
import com.Hanium.CarCamping.domain.dto.member.getDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public getDto createMember(createDto memberCreateDto) {
        if(memberRepository.existsByEmail(memberCreateDto.getEmail()))
            throw new DuplicatedEmailException();

        memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));

        Member savedMember = memberRepository.save(memberCreateDto.of());

        return getDto.toDto(savedMember);
    }

}
