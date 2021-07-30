package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.Review;
import com.Hanium.CarCamping.domain.entity.Review_Member;
import com.Hanium.CarCamping.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewMemberRepository extends JpaRepository<Review_Member, Long> {
    Optional<Review_Member> findByReview_idAndAndMember_id(Review review, Member member);
}
