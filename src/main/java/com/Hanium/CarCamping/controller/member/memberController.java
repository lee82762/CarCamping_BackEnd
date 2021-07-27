package com.Hanium.CarCamping.controller.member;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.member.UpdateDto;
import com.Hanium.CarCamping.domain.dto.member.createDto;
import com.Hanium.CarCamping.domain.dto.member.getDto;
import com.Hanium.CarCamping.domain.dto.member.signInDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import com.Hanium.CarCamping.service.member.MemberSignInService;
import com.Hanium.CarCamping.service.member.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class memberController {

    private final MemberSignInService memberSignInService;
    private final MemberCreateService memberCreateService;
    private final MemberUpdateService memberUpdateService;
    private final JwtService jwtService;

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody signInDto signInDto ) {
        System.out.println(signInDto);
        HttpHeaders httpHeaders=new HttpHeaders();
        String jwt=memberSignInService.signIn(signInDto);
        httpHeaders.add("token",jwt);
        return new ResponseEntity(httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity createMember(@RequestBody createDto memberCreateDto) {
        getDto savedMember = memberCreateService.createMember(memberCreateDto);
        System.out.println(URI.create("/signUp/"+savedMember.getId()));
        return ResponseEntity.created(URI.create("/signUp/" + savedMember.getId())).body(savedMember);
    }

    @PostMapping(value = "/memberUpdate")
    public ResponseEntity main(@RequestParam("token") String token, @RequestBody UpdateDto updateDto){
        Member member=jwtService.findMemberByToken(token);
        memberUpdateService.memberUpdate(updateDto,member);
        return new ResponseEntity(HttpStatus.OK);
    }

}
