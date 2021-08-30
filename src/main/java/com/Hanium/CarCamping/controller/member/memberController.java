package com.Hanium.CarCamping.controller.member;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.member.*;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @PostMapping("/signIn")
    public Result signIn(@RequestBody signInDto signInDto) {
        String jwt = memberSignInService.signIn(signInDto);
        return responseService.getSingleResult(jwt);
    }

    @PostMapping(value = "/signUp")
    public Result createMember(@RequestBody createDto memberCreateDto) {
        getDto savedMember = memberCreateService.createMember(memberCreateDto);
        System.out.println(URI.create("/signUp/" + savedMember.getId()));
        return responseService.getSuccessResult();
    }

    @PostMapping(value = "/member/update/nickname")
    public Result main(@RequestHeader("token") String token, @RequestBody UpdateNickNameDto updateNickNameDto) {
        String email = jwtService.findEmailByJwt(token);
        memberUpdateService.memberNicknameUpdate(updateNickNameDto, email);
        return responseService.getSuccessResult();
    }
    @PostMapping(value = "/member/update/password")
    public Result main(@RequestHeader("token") String token, @RequestBody UpdatePasswordDto updatePasswordDto) {
        String email = jwtService.findEmailByJwt(token);
        memberUpdateService.memberPasswordUpdate(updatePasswordDto, email);
        return responseService.getSuccessResult();
    }


    @DeleteMapping(value = "/memberDelete")
    public Result deleteMember(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        Member member = jwtService.findMemberByToken(token);
        memberDeleteService.deleteMember(member);
        return responseService.getSuccessResult();
    }

    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader("token") String token) {
        return responseService.getSuccessResult();
    }

    @PostMapping(value = "/checkLoginId")
    public Result checkLoginId(@RequestBody checkDto check) {
        return responseService.getSingleResult(memberRepository.existsByEmail(check.getCheck()));
    }

    @PostMapping(value = "/checkNickName")
    public Result checkNickName(@RequestBody checkDto check) {
        return responseService.getSingleResult(memberRepository.existsByNickname(check.getCheck()));
    }
    @GetMapping(value="/myInfo")
    public Result getMyInfo(@RequestHeader("token")String token) {
        return responseService.getSingleResult(ResponseMyInfoDto.convertToDto(jwtService.findMemberByToken(token)));

    }
}
