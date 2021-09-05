package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor

public class CampSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campsite_id")
    private Long campsite_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;



    @OneToMany(mappedBy = "campSite",orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false)
    private float score;
    private float scoreSum;
    private float reviewNum;

    @ManyToOne
    @JoinColumn(name = "registrant_id")
    private Member registrant;

    @Enumerated(value = EnumType.STRING)
    private Region region;

    private String explanation;

    private String image;

    private String videoLink;


    //컬럼 추가
    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private  String lng;

    private String facilities;



    public static CampSite createCampSite(CreateCampSiteDto createCampSiteDto,Member member,String[] geodata) {

        CampSite campSite = new CampSite();
        campSite.name= createCampSiteDto.getName();
        campSite.address= createCampSiteDto.getAddress();
        campSite.score= 0f;
        campSite.region=Region.valueOf(createCampSiteDto.getRegion());
        campSite.explanation= createCampSiteDto.getExplanation();
        campSite.image= createCampSiteDto.getImage();
        campSite.videoLink= createCampSiteDto.getVideoLink();
        campSite.lat=geodata[0];
        campSite.lng=geodata[1];
        campSite.registrant=member;
        campSite.facilities= createCampSiteDto.getFacilities();
        return campSite;

    }

    public void changeScore(float reviewScore,int i) {
        this.scoreSum+=reviewScore;
        this.reviewNum+=i;
        if (reviewNum == 0) {
            this.score = 0f;
        } else {
            this.score=this.scoreSum/this.reviewNum;
        }
    }
}
