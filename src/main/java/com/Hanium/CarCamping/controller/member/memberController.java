package com.Hanium.CarCamping.controller.member;

import com.Hanium.CarCamping.domain.dto.member.createDto;
import com.Hanium.CarCamping.domain.dto.member.getDto;
import com.Hanium.CarCamping.domain.dto.member.signInDto;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import com.Hanium.CarCamping.service.member.MemberSignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class memberController {

    private final MemberSignInService memberSignInService;
    private final MemberCreateService memberCreateService;

    @PostMapping("/signIn")
    public String signIn(@RequestBody signInDto signInDto) {
        return memberSignInService.signIn(signInDto);
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity createMember(@RequestBody createDto memberCreateDto) {
        getDto savedMember = memberCreateService.createMember(memberCreateDto);
        return ResponseEntity.created(URI.create("/sign/" + savedMember.getId())).body(savedMember);

    }
}
