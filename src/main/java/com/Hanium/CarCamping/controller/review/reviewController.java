package com.Hanium.CarCamping.controller.review;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.dto.review.CreateReviewDto;
import com.Hanium.CarCamping.domain.dto.review.ResponseReviewDto;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.Review;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.service.CampSite.CampsiteService;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import com.Hanium.CarCamping.service.Review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class reviewController {
    private final ReviewService reviewService;
    private final JwtService jwtService;
    private final CampsiteService campsiteService;
    private final ResponseService responseService;

    @PostMapping("/review/{camping_id}")
    public Result registerReview(CreateReviewDto createReviewDto,
                                 @RequestParam("token") String token,
                                 @PathVariable Long camping_id) {
        Member member = jwtService.findMemberByToken(token);
        CampSite campsite = campsiteService.findById(camping_id);
        reviewService.saveReview(createReviewDto, member, campsite);
        return responseService.getSuccessResult();
    }

    @GetMapping("/campingReview/{camping_id}/gradeUp")
    public Result getReviewListByGrade(@RequestParam("token") String token, @PathVariable Long camping_id) {
        jwtService.isUsable(token);
        List<Review> result = reviewService.getCampSiteReviewByScoreDESC(camping_id);
        return responseService.getListResult(result.stream().map(ResponseReviewDto::convertToReviewDto).collect(Collectors.toList()));
    }

    @GetMapping("/campingReview/{camping_id}/gradeDown")
    public Result getReviewListByGradeDown(@RequestParam("token") String token, @PathVariable Long camping_id) {
        jwtService.isUsable(token);
        List<Review> result = reviewService.getCampSiteReviewByScoreASC(camping_id);
        return responseService.getListResult(result.stream().map(ResponseReviewDto::convertToReviewDto).collect(Collectors.toList()));
    }

    @GetMapping("/campingReview/{camping_id}/latestUP")
    public Result getReviewListByDateUP(@RequestParam("token") String token, @PathVariable Long camping_id) {
        jwtService.isUsable(token);
        List<Review> result = reviewService.getCampSiteReviewByDateDESC(camping_id);
        return responseService.getListResult(result.stream().map(ResponseReviewDto::convertToReviewDto).collect(Collectors.toList()));
    }

    @GetMapping("/campingReview/{camping_id}/latestDOWN")
    public Result getReviewListByDateDOWN(@RequestParam("token") String token, @PathVariable Long camping_id) {
        jwtService.isUsable(token);
        List<Review> result = reviewService.getCampSiteReviewByDateASC(camping_id);
        return responseService.getListResult(result.stream().map(ResponseReviewDto::convertToReviewDto).collect(Collectors.toList()));
    }

    @GetMapping("/campingReview/{review_id}")
    public Result getReview(@RequestParam("token") String token, @PathVariable Long review_id) {
        jwtService.isUsable(token);
        return responseService.getSingleResult(ResponseReviewDto.convertToReviewDto(reviewService.getReview(review_id)));
    }

    @DeleteMapping("campingReview/{review_id}")
    public Result deleteReview(@RequestParam("token") String token, @PathVariable Long review_id) {
        jwtService.isUsable(token);
        reviewService.deleteReview(jwtService.findEmailByJwt(token),review_id);
        return responseService.getSuccessResult();
    }
    @GetMapping
    public Result getMostRecommend3Review(@RequestParam("token") String token, @PathVariable Long camping_id) {
        jwtService.isUsable(token);
        List<Review> result = reviewService.mostRecommendedTop3Review(camping_id);
        return responseService.getListResult(result.stream().map(ResponseReviewDto::convertToReviewDto).collect(Collectors.toList()));
    }
}
