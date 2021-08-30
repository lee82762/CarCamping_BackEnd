package com.Hanium.CarCamping.service.member;

import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberDeleteService {
    private final MemberRepository memberRepository;

    public void deleteMember(Member member){
        memberRepository.delete(member);
    }
}
