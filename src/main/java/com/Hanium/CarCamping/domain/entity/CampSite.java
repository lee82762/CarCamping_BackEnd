package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.service.S3Service.S3Uploader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrant_id")
    private Member registrant;

    @Enumerated(value = EnumType.STRING)
    private Region region;

    private String explanation;

    private String images;

    private String videoLink;


    //컬럼 추가
    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private  String lng;

    private String facilities;



    public static CampSite convertToCampSite(WaitingCampSite waitingCampSite) {
        CampSite campSite = new CampSite();
        campSite.name= waitingCampSite.getName();
        campSite.address= waitingCampSite.getAddress();
        campSite.score= 0f;
        campSite.region=waitingCampSite.getRegion();
        campSite.explanation= waitingCampSite.getExplanation();
        campSite.videoLink= waitingCampSite.getVideoLink();
        campSite.images=waitingCampSite.getImages();
        campSite.lat= waitingCampSite.getLat();
        campSite.lng= waitingCampSite.getLng();
        campSite.registrant=waitingCampSite.getRegistrant();
        campSite.facilities= waitingCampSite.getFacilities();
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

    public void change(ChangeCampSite changeCampSite) {
        this.name = changeCampSite.getName();
        this.explanation= changeCampSite.getExplanation();
        this.images= changeCampSite.getImages();
        this.videoLink= changeCampSite.getVideoLink();
        this.facilities= changeCampSite.getFacilities();
    }
}
