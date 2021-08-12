package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.dto.review.CreateReviewDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long review_id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="writer_id")
    private Member writer;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Float score;

    @Column(nullable = false)
    private LocalDateTime date;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="campsite_id")
    private CampSite campSite;

    @OneToMany(mappedBy = "review_id",cascade = CascadeType.ALL)
    private Set<Review_Member> participants = new HashSet<>();

    private Integer recommend;

    //연관관계 메서드
    public void setCampSite(CampSite campSite) {
        this.campSite=campSite;
        campSite.getReviewList().add(this);
    }
    public void setWriter(Member writer) {
        this.writer=writer;
        writer.getReviewList().add(this);
    }

    public static Review createReview(CreateReviewDto createReviewDto,Member writer,CampSite campSite) {
        Review review = new Review();
        review.title=createReviewDto.getTitle();
        review.contents= createReviewDto.getContent();
        review.score= createReviewDto.getScore();
        review.date=LocalDateTime.now();
        review.recommend=0;
        review.setCampSite(campSite);
        review.setWriter(writer);
        return review;
    }
    public void changeRecommend(int i){
        this.recommend+=i;
    }
}
