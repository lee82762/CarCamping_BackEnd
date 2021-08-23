package com.Hanium.CarCamping.service.Review;

import com.Hanium.CarCamping.Exception.NoSuchMemberException;
import com.Hanium.CarCamping.Exception.NotReviewWriterException;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.dto.member.createDto;
import com.Hanium.CarCamping.domain.dto.member.getDto;
import com.Hanium.CarCamping.domain.dto.review.CreateReviewDto;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.Review;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.repository.ReviewRepository;
import com.Hanium.CarCamping.service.CampSite.CampsiteService;
import com.Hanium.CarCamping.service.Review_Member.ReviewMemberService;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ReviewServiceTest {
    @Autowired ReviewService reviewService;
    @Autowired CampsiteService campsiteService;
    @Autowired MemberRepository memberRepository;
    @Autowired MemberCreateService memberCreateService;
    @Autowired ReviewMemberService reviewMemberService;
    @Autowired ReviewRepository reviewRepository;

    @BeforeEach
    public void setUpMemberAndCampSite() {
        Member m1 = setUpMember("tester1@naver.com","차박러1");
        Member m2 = setUpMember("tester2@naver.com","차박러2");
        campsiteService.saveCampSite(setUpCampSite("안양시 차박지",5.0f),m1);
        campsiteService.saveCampSite(setUpCampSite("수원시 차박지",4.0f),m1);
        campsiteService.saveCampSite(setUpCampSite("파주시 차박지",4.5f),m2);

    }
    @Test
    public void 리뷰_저장_테스트() throws Exception {
        //given
        Member member1 = memberRepository.findByNickname("차박러1").orElseThrow(NoSuchMemberException::new);
        Member member2 = memberRepository.findByNickname("차박러2").orElseThrow(NoSuchMemberException::new);
        CampSite campsite1 = campsiteService.findByName("안양시 차박지");
        CreateReviewDto reviewDto = setUpReviewDto("좋아요", 5.0f);
        CreateReviewDto reviewDto1 = setUpReviewDto("싫어요", 1.0f);

        //when
        reviewService.saveReview(reviewDto,member1,campsite1);
        reviewService.saveReview(reviewDto1,member2,campsite1);

        //then
        List<Review> result = reviewService.getCampSiteReviewByScoreASC(campsite1.getCampsite_id());
        assertThat(reviewService.getAllReview().size()).isEqualTo(2);
        assertThat(result.size()).isEqualTo(2);
        for (Review review : result) {
            System.out.println(review);
        }
    }
    @Test
    public void 리뷰_평점별정렬() throws Exception {
        //given
        Member member1 = memberRepository.findByNickname("차박러1").orElseThrow(NoSuchMemberException::new);
        Member member2 = memberRepository.findByNickname("차박러2").orElseThrow(NoSuchMemberException::new);
        CampSite campsite1 = campsiteService.findByName("안양시 차박지");
        CreateReviewDto reviewDto = setUpReviewDto("좋아요", 5.0f);
        CreateReviewDto reviewDto1 = setUpReviewDto("괜찮아요", 2.5f);
        CreateReviewDto reviewDto2 = setUpReviewDto("싫어요", 1.0f);

        //when
        reviewService.saveReview(reviewDto,member1,campsite1);
        reviewService.saveReview(reviewDto1,member2,campsite1);
        reviewService.saveReview(reviewDto2,member2,campsite1);

        //then
        List<Review> campSiteReviewByScoreDESC = reviewService.getCampSiteReviewByScoreDESC(campsite1.getCampsite_id());
        List<Review> campSiteReviewByScoreASC = reviewService.getCampSiteReviewByScoreASC(campsite1.getCampsite_id());
        assertThat(campSiteReviewByScoreDESC.get(0).getScore()).isEqualTo(5.0f);
        assertThat(campSiteReviewByScoreDESC.get(2).getScore()).isEqualTo(1.0f);
        assertThat(campSiteReviewByScoreASC.get(0).getScore()).isEqualTo(1.0f);
        assertThat(campSiteReviewByScoreASC.get(1).getScore()).isEqualTo(2.5f);

    }
    @Test
    public void 리뷰_시간순_정렬() throws Exception {
        //given
        Member member1 = memberRepository.findByNickname("차박러1").orElseThrow(NoSuchMemberException::new);
        Member member2 = memberRepository.findByNickname("차박러2").orElseThrow(NoSuchMemberException::new);
        CampSite campsite1 = campsiteService.findByName("안양시 차박지");
        CreateReviewDto reviewDto = setUpReviewDto("좋아요", 5.0f);
        CreateReviewDto reviewDto1 = setUpReviewDto("싫어요", 1.0f);
        CreateReviewDto reviewDto2 = setUpReviewDto("괜찮아요", 2.5f);

        //when
        Long review_id = reviewService.saveReview(reviewDto, member1, campsite1);
        reviewService.saveReview(reviewDto1,member2,campsite1);
        reviewService.saveReview(reviewDto2,member2,campsite1);

        //then
        List<Review> campSiteReviewByDateASC = reviewService.getCampSiteReviewByDateASC(campsite1.getCampsite_id());
        List<Review> campSiteReviewByDateDESC = reviewService.getCampSiteReviewByDateDESC(campsite1.getCampsite_id());
        Review review = reviewService.getReview(review_id);

        assertThat(campSiteReviewByDateASC.get(0).getScore()).isEqualTo(campSiteReviewByDateDESC.get(2).getScore());
        assertThat(campSiteReviewByDateASC.get(1).getScore()).isEqualTo(campSiteReviewByDateDESC.get(1).getScore());
        assertThat(campSiteReviewByDateASC.get(0).getDate()).isEqualTo(review.getDate());
        for (Review review1 : campSiteReviewByDateDESC) {
            System.out.println(review1);
        }
    }
    @Test
    public void 리뷰삭제_테스트() throws Exception {
        //given
        Member member1 = memberRepository.findByNickname("차박러1").orElseThrow(NoSuchMemberException::new);
        Member member2 = memberRepository.findByNickname("차박러2").orElseThrow(NoSuchMemberException::new);
        CampSite campsite1 = campsiteService.findByName("안양시 차박지");
        CreateReviewDto reviewDto = setUpReviewDto("좋아요", 5.0f);
        Long review_id = reviewService.saveReview(reviewDto, member1, campsite1);
        //when
        reviewService.deleteReview(member1.getEmail(),review_id);

        //then
        assertThat(reviewService.getAllReview().size()).isEqualTo(0);

    }
    @Test
    public void 리뷰작성자가_아닐때_테스트() throws Exception {
        Member member1 = memberRepository.findByNickname("차박러1").orElseThrow(NoSuchMemberException::new);
        Member member2 = memberRepository.findByNickname("차박러2").orElseThrow(NoSuchMemberException::new);
        CampSite campsite1 = campsiteService.findByName("안양시 차박지");
        CreateReviewDto reviewDto = setUpReviewDto("좋아요", 5.0f);
        Long review_id = reviewService.saveReview(reviewDto, member1, campsite1);
        //when
        NotReviewWriterException e = assertThrows(NotReviewWriterException.class, () -> reviewService.deleteReview(member2.getEmail(), review_id));

        //then
        assertThat(e.getMessage()).isEqualTo("리뷰 작성자가 아닙니다");

    }
    @Test
    public void 리뷰탑3_테스트() throws Exception {
        //given
        Member member1 = memberRepository.findByNickname("차박러1").orElseThrow(NoSuchMemberException::new);
        Member member2 = memberRepository.findByNickname("차박러2").orElseThrow(NoSuchMemberException::new);
        Member member3 = setUpMember("testerr@naver.com", "차박러3");
        Member member4 = setUpMember("testerrr@naver.com", "차박러4");

        CampSite campsite1 = campsiteService.findByName("안양시 차박지");
        CreateReviewDto reviewDto = setUpReviewDto("좋아요", 5.0f);
        CreateReviewDto reviewDto1 = setUpReviewDto("싫어요", 1.0f);
        CreateReviewDto reviewDto2 = setUpReviewDto("중간이야", 3.0f);
        CreateReviewDto reviewDto3 = setUpReviewDto("괜찮아요", 4.0f);

        Long review_id1 = reviewService.saveReview(reviewDto, member1, campsite1);
        Long review_id2 = reviewService.saveReview(reviewDto1, member1, campsite1);
        Long review_id3 = reviewService.saveReview(reviewDto2, member1, campsite1);
        Long review_id4 = reviewService.saveReview(reviewDto3, member1, campsite1);

        Review review1 = reviewRepository.getById(review_id1);
        Review review2 = reviewRepository.getById(review_id2);
        Review review3 = reviewRepository.getById(review_id3);
        Review review4 = reviewRepository.getById(review_id4);

        //when
        reviewMemberService.createReviewMember(review2,member2,1);
        reviewMemberService.createReviewMember(review2,member3,1);
        reviewMemberService.createReviewMember(review2,member4,1);
        reviewMemberService.createReviewMember(review1,member3,-1);
        reviewMemberService.createReviewMember(review1,member4,-1);
        reviewMemberService.createReviewMember(review3,member2,1);
        reviewMemberService.createReviewMember(review3,member3,1);
        reviewMemberService.createReviewMember(review4,member2,-1);
        reviewMemberService.createReviewMember(review4,member3,1);
        reviewMemberService.createReviewMember(review4,member4,1);


        List<Review> reviews = reviewService.mostRecommendedTop3Review(campsite1.getCampsite_id());
        //then
        assertThat(reviews.get(0).getRecommend()).isEqualTo(3);
        assertThat(reviews.get(1).getRecommend()).isEqualTo(2);
        assertThat(reviews.get(2).getRecommend()).isEqualTo(1);

    }



    public CreateReviewDto setUpReviewDto(String s,float f) {
        CreateReviewDto ReviewDto = CreateReviewDto.builder()
                .title(s)
                .content("차박지 입니다").
                        score(f).build();
        return ReviewDto;
    }

    public  CreateCampSiteDto setUpCampSite(String s, float f) {
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

    public  Member setUpMember(String email,String name) {
        getDto member = memberCreateService.createMember(createDto.builder().
                email(email)
                .password("1234")
                .nickname(name)
                .point(3)
                .build());
        return memberRepository.findByEmail(member.getEmail()).orElseThrow();
    }

}
