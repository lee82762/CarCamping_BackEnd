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
public class WaitingCampSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campsite_id")
    private Long waitingCampSite_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrant_id")
    private Member registrant;

    @Enumerated(value = EnumType.STRING)
    private Region region;

    private String explanation;

    private String images;

    private String videoLink;
    @Column(nullable = false)
    private float score;
    //컬럼 추가
    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private  String lng;

    private String facilities;



    public static WaitingCampSite createCampSite(CreateCampSiteDto createCampSiteDto, Member member, String[] geodata) {
        WaitingCampSite campSite = new WaitingCampSite();
        campSite.name= createCampSiteDto.getName();
        campSite.address= createCampSiteDto.getAddress();
        campSite.score= 0f;
        campSite.region=Region.valueOf(createCampSiteDto.getRegion());
        campSite.explanation= createCampSiteDto.getExplanation();
        campSite.videoLink= createCampSiteDto.getVideoLink();
        campSite.images=createCampSiteDto.getImages();
        campSite.lat=geodata[0];
        campSite.lng=geodata[1];
        campSite.registrant=member;
        campSite.facilities= createCampSiteDto.getFacilities();
        return campSite;

    }
}
