package com.Hanium.CarCamping.domain.entity;

<<<<<<< HEAD
import com.Hanium.CarCamping.domain.dto.review.CreateReviewDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
=======
import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> d2162bc... security 로그인/회원가입/회원 수정
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long review_id;
<<<<<<< HEAD


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="writer_id")
    private Member writer;


=======
    /*
        @Column(nullable = false)
        private User writer;
     */
>>>>>>> d2162bc... security 로그인/회원가입/회원 수정
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
<<<<<<< HEAD
    private Float score;

    @Column(nullable = false)
=======
>>>>>>> d2162bc... security 로그인/회원가입/회원 수정
    private LocalDateTime date;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="campsite_id")
    private CampSite campSite;

<<<<<<< HEAD
    @OneToMany(mappedBy = "review_id",cascade = CascadeType.ALL)
    private Set<Member> participants = new HashSet<>();

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
    public void upRecommend() {
        this.recommend+=1;
    }
    public void downRecommend() {
        this.recommend-=1;
    }
}
=======

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    private Integer recommend;


}
>>>>>>> d2162bc... security 로그인/회원가입/회원 수정
