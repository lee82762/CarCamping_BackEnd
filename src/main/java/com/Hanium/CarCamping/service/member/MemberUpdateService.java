package com.Hanium.CarCamping.service.member;

import com.Hanium.CarCamping.Exception.DuplicatedNickNameException;
import com.Hanium.CarCamping.domain.dto.member.UpdateDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUpdateService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void memberUpdate(UpdateDto updateDto, Member member){
        if (memberRepository.existsByNickname((updateDto.getNickname()))) {
            throw new DuplicatedNickNameException();
        }
        member.setNickname(updateDto.getNickname());
        member.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        memberRepository.save(member);
    }
}
