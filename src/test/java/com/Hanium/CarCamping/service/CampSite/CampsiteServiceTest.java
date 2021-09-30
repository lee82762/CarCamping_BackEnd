package com.Hanium.CarCamping.service.CampSite;

import com.Hanium.CarCamping.Exception.DuplicateCampSiteException;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.dto.member.createDto;
import com.Hanium.CarCamping.domain.dto.member.getDto;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static com.Hanium.CarCamping.domain.Region.경기도;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CampsiteServiceTest {
    @Autowired MemberCreateService memberCreateService;
    @Autowired MemberRepository memberRepository;
    @Autowired CampsiteService campsiteService;


    @Test
    public void 검색테스트() throws Exception {
        //given
        List<CampSite> result = campsiteService.getCampSiteBySearchWordAndRegion("테스트",Region.valueOf("충청도"));

        //when
        for (CampSite campSite : result) {
            System.out.println(campSite.getName());
        }

        //then

    }
/*
    @Test
    public void 차박지_등록() throws Exception {
        //given
        Member member = setUpMember();
        campsiteService.saveCampSite(CreateCampSiteDto.builder()
                .name("테스트 차박지")
                .address("안양시 동안구")
                .explanation("설명")
                .image("htts://www.naver.com")
                .region("경기도")
                .score(4.0f)
                .videoLink("https://youtube.com")
                .build(), memberRepository.findById(member.getId()).orElseThrow());
        //when
        int size = campsiteService.getAllCampSiteList().size();
        CampSite result = campsiteService.findByName("테스트 차박지");
        //then
        //assertThat(size).isEqualTo(1);
        //assertThat(result.getAddress()).isEqualTo("안양시 동안구");

    }
    @Test
    public void 차박지_검색및_정렬_기능() throws Exception {
        //given
        Member member = setUpMember();
        campsiteService.saveCampSite(setUpCampSite("안양시 차박지", 5F),member);
        campsiteService.saveCampSite(setUpCampSite("수원시 차박지", 3F),member);
        campsiteService.saveCampSite(setUpCampSite("파주시 차박지", 4F),member);
        //when
        List<CampSite> byRegion = campsiteService.getCampSiteByRegionAndScoreDESC(경기도);
        CampSite result1 = campsiteService.findByName("안양시 차박지");
        CampSite result2 = campsiteService.findById(result1.getCampsite_id());
        //then
        for (CampSite campSite : byRegion) {
            System.out.println(campSite.getScore());
        }
        assertThat(result1).isEqualTo(result2);
        //assertThat(byRegion.size()).isEqualTo(3);
        assertThat(result1.getCampsite_id()).isEqualTo(result2.getCampsite_id());

    }
    @Test
    public void 이미존재하는_차박지생성에러() throws Exception {
        //given
        Member member = setUpMember();
        campsiteService.saveCampSite(CreateCampSiteDto.builder()
                .name("테스트 차박지")
                .address("안양시 동안구")
                .explanation("설명")
                .image("htts://www.naver.com")
                .region("경기도")
                .score(4.0f)
                .videoLink("https://youtube.com")
                .build(), memberRepository.findById(member.getId()).orElseThrow());
        //when
        CreateCampSiteDto duplicateCampSite = CreateCampSiteDto.builder()
                .name("테스트 차박지")
                .address("안양시 동안구")
                .explanation("설명")
                .image("htts://www.naver.com")
                .region("경기도")
                .score(4.0f)
                .videoLink("https://youtube.com")
                .build();
        CreateCampSiteDto regionDiff = CreateCampSiteDto.builder()
                .name("테스트 차박지")
                .address("안양시 동안구")
                .explanation("설명")
                .image("htts://www.naver.com")
                .region("강원도")
                .score(4.0f)
                .videoLink("https://youtube.com")
                .build();
        DuplicateCampSiteException e = Assertions.assertThrows(DuplicateCampSiteException.class
                , () -> campsiteService.saveCampSite(duplicateCampSite, memberRepository.findById(member.getId()).orElseThrow()));
        campsiteService.saveCampSite(regionDiff,memberRepository.findById(member.getId()).orElseThrow());
        //then
        assertThat(e.getMessage()).isEqualTo("이미 등록되어있는 차박지입니다");
        //지역은 다르면 등록 가능
        //assertThat(campsiteService.getAllCampSiteList().size()).isEqualTo(2);




    }


    public CreateCampSiteDto setUpCampSite(String s,float f) {
        return CreateCampSiteDto.builder()
                .name(s)
                .address("안양시 동안구")
                .explanation("설명")
                .image("htts://www.naver.com")
                .region("경기도")
                .score(f)
                .videoLink("https://youtube.com")
                .build();
    }

    public Member setUpMember() {
        getDto member = memberCreateService.createMember(createDto.builder().
                email("test13@naver.com")
                .password("1234")
                .nickname("test")
                .point(3)
                .build());
        return memberRepository.findByEmail(member.getEmail()).orElseThrow();
    }

 */




}