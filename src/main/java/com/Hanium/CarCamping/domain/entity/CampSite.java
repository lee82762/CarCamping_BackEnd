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


    @OneToMany(mappedBy = "campSite", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false)
    private Float score;


    @ManyToOne
    @JoinColumn(name = "registrant_id")
    private Member registrant;

    @Enumerated(value = EnumType.STRING)
    private Region region;

    private String explanation;

    private String image;

    private String videoLink;


    public static CampSite createCampSite(CreateCampSiteDto createCampSiteDto,Member member) {

        CampSite campSite = new CampSite();
        campSite.name= createCampSiteDto.getName();
        campSite.address= createCampSiteDto.getAddress();
        campSite.score= createCampSiteDto.getScore();
        campSite.region=Region.valueOf(createCampSiteDto.getRegion());
        campSite.explanation= createCampSiteDto.getExplanation();
        campSite.image= createCampSiteDto.getImage();
        campSite.videoLink= createCampSiteDto.getVideoLink();
        campSite.registrant=member;
        return campSite;

    }
}
