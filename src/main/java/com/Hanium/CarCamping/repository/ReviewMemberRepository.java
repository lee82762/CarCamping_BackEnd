package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.Review_Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface
ReviewMemberRepository extends JpaRepository<Review_Member, Long> {
    @Query("select t from Review_Member t join fetch t.member_id m join fetch t.review_id r where t.review_id.review_id=:review and t.member_id.id=:member")

    List<Review_Member> findByReview_idAndMember_id(@Param("review") Long review, @Param("member") Long member);


}
