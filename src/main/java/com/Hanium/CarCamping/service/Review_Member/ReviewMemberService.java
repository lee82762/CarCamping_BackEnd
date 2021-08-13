package com.Hanium.CarCamping.service.Review_Member;

import com.Hanium.CarCamping.Exception.AlreadyParticipateException;
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

        if (reviewMemberRepository.findByReview_idAndMember_id(review.getReview_id(), member.getId()).size()==0) {
            reviewMemberRepository.save(Review_Member.createReview_Member(review, member,i));
        } else {
            throw new AlreadyParticipateException();
        }
    }

    public List<Review_Member> findByReviewAndMember(Review review,Member member) {
        return reviewMemberRepository.findByReview_idAndMember_id(review.getReview_id(), member.getId());
    }





}
