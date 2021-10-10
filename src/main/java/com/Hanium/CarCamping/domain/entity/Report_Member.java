package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//충돌 확인
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class  Report_Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long report_member_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member_id;

    public static Report_Member createReport_Member(Review review, Member member) {
        Report_Member reportMember = new Report_Member();
        reportMember.member_id=member;
        reportMember.setReview_id(review);
        return reportMember;
    }

    public void setReview_id(Review review) {
        this.review_id=review;
    }

    @Override
    public boolean equals(Object o) {
        Report_Member m=(Report_Member) o;
        if (this.member_id.getId().equals(m.member_id.getId()) && this.review_id.getReview_id().equals(m.review_id.getReview_id())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME=31;
        int result=1;
        result= (int) (review_id.getReview_id()+member_id.getId());
        return result;
    }
}
