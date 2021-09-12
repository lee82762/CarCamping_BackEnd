package com.Hanium.CarCamping.controller.member;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.campsite.ResponseCampSiteListDto;
import com.Hanium.CarCamping.domain.dto.member.*;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.dto.review.ResponseOneReviewDto;
import com.Hanium.CarCamping.domain.dto.review.ResponseReviewDto;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.Review;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.service.CampSite.CampsiteService;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import com.Hanium.CarCamping.service.Review.ReviewService;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import com.Hanium.CarCamping.service.member.MemberDeleteService;
import com.Hanium.CarCamping.service.member.MemberSignInService;
import com.Hanium.CarCamping.service.member.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ReviewService reviewService;
    private final CampsiteService campsiteService;

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
    public Result deleteMember(@RequestHeader("token") String token, @RequestBody checkDto checkDto) {
        jwtService.isUsable(token);
        memberDeleteService.deleteMember(token, checkDto);
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

    @GetMapping(value = "/myInfo")
    public Result getMyInfo(@RequestHeader("token") String token) {
        Member member = jwtService.findMemberByToken(token);
        return responseService.getSingleResult(ResponseMyInfoDto.convertToDto(jwtService.findMemberByToken(token)));

    }

    @PostMapping(value = "/updateProfile")
    public Result changeProfileImage(@RequestParam("images") MultipartFile multipartFile, @RequestHeader("token") String token) throws IOException {
        memberUpdateService.setProfilePhoto(multipartFile, jwtService.findEmailByJwt(token));
        return responseService.getSuccessResult();
    }

    @DeleteMapping(value = "/deleteProfile")
    public Result deleteProfile(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        memberUpdateService.deleteProfile(jwtService.findEmailByJwt(token));
        return responseService.getSuccessResult();
    }
    @GetMapping(value="/myReview")
    public Result getMyReview(@RequestHeader("token")String token){
        jwtService.isUsable(token);
        List<Review> myReview = reviewService.getMyReview(jwtService.findEmailByJwt(token));
        return responseService.getListResult(myReview.stream().map(ResponseReviewDto::convertToReviewDto).collect(Collectors.toList()));
    }
    @GetMapping(value="/myCampSite")
    public Result getMyCampSite(@RequestHeader("token")String token){
        jwtService.isUsable(token);
        List<CampSite> myCampSite = campsiteService.getMyCampSite(jwtService.findEmailByJwt(token));
        return responseService.getListResult(myCampSite.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }
}
