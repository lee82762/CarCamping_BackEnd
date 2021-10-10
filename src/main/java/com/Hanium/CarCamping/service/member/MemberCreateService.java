package com.Hanium.CarCamping.service.member;

import com.Hanium.CarCamping.Exception.DuplicatedEmailException;
import com.Hanium.CarCamping.Exception.DuplicatedNickNameException;
import com.Hanium.CarCamping.domain.dto.member.createDto;
import com.Hanium.CarCamping.domain.dto.member.getDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.domain.entity.member.Role;
import com.Hanium.CarCamping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCreateService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    public getDto createMember(createDto memberCreateDto) {
        if(memberRepository.existsByEmail(memberCreateDto.getEmail()))
            throw new DuplicatedEmailException();
        if (memberRepository.existsByNickname((memberCreateDto.getNickname()))) {
            throw new DuplicatedNickNameException();
        }
        memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
        Member result = memberCreateDto.of();
        result.setRole(Role.USER);
        Member member = memberRepository.save(result);
        redisTemplate.opsForZSet().add("ranking",member.getNickname(), member.getPoint());
        return getDto.toDto(member);
    }
    public getDto createAdminMember(createDto memberCreateDto){
        memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
        Member result = memberCreateDto.of();
        result.setRole(Role.ADMIN);
        Member member = memberRepository.save(result);
        return getDto.toDto(member);
    }

}
