package com.Hanium.CarCamping.service.Review_Member;

import com.Hanium.CarCamping.Exception.AlreadyParticipateException;
import com.Hanium.CarCamping.Exception.CannotRecommendMyReviewException;
import com.Hanium.CarCamping.Exception.NoSuchMemberException;
import com.Hanium.CarCamping.Exception.NoSuchReviewException;
import com.Hanium.CarCamping.domain.entity.Review;
import com.Hanium.CarCamping.domain.entity.Review_Member;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.domain.entity.member.Role;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.repository.ReviewMemberRepository;
import com.Hanium.CarCamping.repository.ReviewRepository;
import com.Hanium.CarCamping.service.Point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewMemberService {
    private final PointService pointService;
    private final ReviewMemberRepository reviewMemberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RedisTemplate redisTemplate;

public Long createReviewMember(Long review_id, String email, int i) {
    Member member = memberRepository.findByEmail(email).orElseThrow(NoSuchMemberException::new);
    Review review = reviewRepository.findById(review_id).orElseThrow(NoSuchReviewException::new);
    Review_Member review_member = Review_Member.createReview_Member(review, member, i);

    if (review.getWriter().getId().equals(member.getId())) {
        throw new CannotRecommendMyReviewException("자신의 리뷰는 추천할 수 없습니다");
    }
    if (review.getParticipants().contains(review_member)) {
        throw new AlreadyParticipateException("이미 평가한 리뷰입니다");
    }

    Review_Member save = reviewMemberRepository.save(review_member);
    review.getParticipants().add(save);
    pointService.create(member,"리뷰 평가 참여",2);
    return save.getReview_member_id();

}

    public List<Review_Member> findByReviewAndMember(Review review,Member member) {
        return reviewMemberRepository.findByReview_idAndMember_id(review.getReview_id(), member.getId());
    }
    public List<Review_Member> getAll() {
        return reviewMemberRepository.findAll();
    }

}
