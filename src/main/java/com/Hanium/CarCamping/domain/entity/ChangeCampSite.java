package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.dto.campsite.ChangeCampSiteDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeCampSite {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ChangeCampSite_id;

    private Long campsite_id;

    private String name;

    private String address;

    private String explanation;

    private String images;

    private String videoLink;

    private String facilities;

    public static ChangeCampSite createChangeCampSite(ChangeCampSiteDto changeCampSiteDto) {
        ChangeCampSite changeCampSite=new ChangeCampSite();
        changeCampSite.campsite_id=changeCampSiteDto.getCampsite_id();
        changeCampSite.name=changeCampSiteDto.getName();
        changeCampSite.address= changeCampSite.getAddress();
        changeCampSite.explanation=changeCampSiteDto.getExplanation();
        changeCampSite.images=changeCampSiteDto.getImages();
        changeCampSite.videoLink=changeCampSiteDto.getVideoLink();
        changeCampSite.facilities=changeCampSiteDto.getFacilities();
        return changeCampSite;
    }
}
