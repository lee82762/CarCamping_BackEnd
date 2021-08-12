package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review_Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_member_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member_id;

    public static Review_Member createReview_Member(Review review, Member member,int i) {
        Review_Member review_member = new Review_Member();
        review_member.setReview_id(review);
        review_member.member_id=member;
        review.changeRecommend(i);
        return review_member;
    }

    public void setReview_id(Review review) {
        this.review_id=review;
        review.getParticipants().add(this);
    }

}
