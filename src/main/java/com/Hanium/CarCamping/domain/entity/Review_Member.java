package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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
}
