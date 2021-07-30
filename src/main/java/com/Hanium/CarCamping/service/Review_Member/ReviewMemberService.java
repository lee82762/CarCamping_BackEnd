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

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewMemberService {
    private final ReviewService reviewService;
    private final ReviewMemberRepository reviewMemberRepository;

    public void createReviewMember(Review review, Member member) {
        Optional<Review_Member> result = reviewMemberRepository.findByReview_idAndAndMember_id(review, member);
        if (result.isEmpty()) {
            reviewMemberRepository.save(Review_Member.createReview_Member(review, member));
        } else {
            throw new AlreadyParticipateException();
        }
    }


}
