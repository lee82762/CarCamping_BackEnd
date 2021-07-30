package com.Hanium.CarCamping.domain.entity;

<<<<<<< HEAD
import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
=======
import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> d2162bc... security 로그인/회원가입/회원 수정

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
@Builder
=======
@Setter
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> d2162bc... security 로그인/회원가입/회원 수정
public class CampSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campsite_id")
    private Long campsite_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;


<<<<<<< HEAD
    @OneToMany(mappedBy = "campSite", cascade = CascadeType.ALL)
=======
    @OneToMany(mappedBy ="campSite")
>>>>>>> d2162bc... security 로그인/회원가입/회원 수정
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false)
    private Float score;

<<<<<<< HEAD

    @OneToOne
    @JoinColumn(name = "registrant_id")
    private Member registrant;

    @Enumerated(value = EnumType.STRING)
    private Region region;

    private String explanation;

    private String image;

    private String videoLink;


    public static CampSite createCampSite(CreateCampSiteDto createCampSiteDto,Member member) {
        return CampSite.builder().name(createCampSiteDto.getName())
                .address(createCampSiteDto.getAddress())
                .score(createCampSiteDto.getScore())
                .explanation(createCampSiteDto.getExplanation())
                .videoLink(createCampSiteDto.getVideoLink())
                .image(createCampSiteDto.getImage())
                .region(Region.valueOf(createCampSiteDto.getRegion()))
                .registrant(member)
                .build();
    }
=======
    @OneToOne
    @JoinColumn(name = "registrant_id")
    private Member registrant;
/*
    @Column(nullable = false)
    private User registrant;

 */


>>>>>>> d2162bc... security 로그인/회원가입/회원 수정
}
