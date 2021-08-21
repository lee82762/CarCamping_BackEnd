package com.Hanium.CarCamping.service.Review_Member;

import com.Hanium.CarCamping.Exception.AlreadyParticipateException;
import com.Hanium.CarCamping.Exception.CannotRecommendMyReviewException;
import com.Hanium.CarCamping.domain.entity.Review;
import com.Hanium.CarCamping.domain.entity.Review_Member;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.ReviewMemberRepository;
import com.Hanium.CarCamping.service.Review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewMemberService {
    private final ReviewService reviewService;
    private final ReviewMemberRepository reviewMemberRepository;


public void createReviewMember(Review review, Member member, int i) {

        if (reviewMemberRepository.findByReview_idAndMember_id(review.getReview_id(), member.getId()).size()!=0) {
            throw new AlreadyParticipateException("이미 평가한 리뷰입니다");
        }
        if (review.getWriter().getId().equals(member.getId())) {
            throw new CannotRecommendMyReviewException("자신의 리뷰는 추천할 수 없습니다");
        }
        reviewMemberRepository.save(Review_Member.createReview_Member(review, member,i));

}

    public List<Review_Member> findByReviewAndMember(Review review,Member member) {
        return reviewMemberRepository.findByReview_idAndMember_id(review.getReview_id(), member.getId());
    }

    public Long create(Review review, Member member, int i) {
        Review_Member review_member = Review_Member.createReview_Member(review, member, i);

        if (review.getWriter().getId().equals(member.getId())) {
            throw new CannotRecommendMyReviewException("자신의 리뷰는 추천할 수 없습니다");
        }
        if (review.getParticipants().contains(review_member)) {
            throw new AlreadyParticipateException("이미 평가한 리뷰입니다");
        }
        Review_Member save = reviewMemberRepository.save(review_member);
        review.getParticipants().add(save);
        return save.getReview_member_id();

    }



}
