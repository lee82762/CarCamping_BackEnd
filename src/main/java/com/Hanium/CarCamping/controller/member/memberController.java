package com.Hanium.CarCamping.controller.member;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.member.UpdateDto;
import com.Hanium.CarCamping.domain.dto.member.createDto;
import com.Hanium.CarCamping.domain.dto.member.getDto;
import com.Hanium.CarCamping.domain.dto.member.signInDto;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import com.Hanium.CarCamping.service.member.MemberDeleteService;
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
@RequestMapping("/api")
public class memberController {

    private final MemberSignInService memberSignInService;
    private final MemberCreateService memberCreateService;
    private final MemberUpdateService memberUpdateService;
    private final MemberDeleteService memberDeleteService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    @PostMapping("/signIn")
    public Result signIn(@RequestBody signInDto signInDto ) {
        String jwt=memberSignInService.signIn(signInDto);
        return responseService.getSingleResult(jwt);
    }

    @PostMapping(value = "/signUp")
    public Result createMember(@RequestBody createDto memberCreateDto) {
        getDto savedMember = memberCreateService.createMember(memberCreateDto);
        System.out.println(URI.create("/signUp/"+savedMember.getId()));
        return responseService.getSuccessResult();
    }

    @PostMapping(value = "/memberUpdate")
    public Result main(@RequestParam("token") String token, @RequestBody UpdateDto updateDto){
        Member member=jwtService.findMemberByToken(token);
        memberUpdateService.memberUpdate(updateDto,member);
        return responseService.getSuccessResult();
    }

    @DeleteMapping(value = "/memberDelete")
    public Result deleteMember(@RequestParam("token")String token){
        jwtService.isUsable(token);
        Member member=jwtService.findMemberByToken(token);
        memberDeleteService.deleteMember(member);
        return responseService.getSuccessResult();
    }

    @GetMapping(value = "/logout")
    public Result logout(@RequestParam("token")String token){
        return responseService.getSuccessResult();
    }

}
