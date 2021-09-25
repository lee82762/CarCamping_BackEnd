package com.Hanium.CarCamping.domain.dto.campsite;

import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.service.CampSite.CampsiteService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class ResponseCampSiteDto {
    private static CampsiteService campsiteService;

    private Long campsite_id;
    private String name;
    private String address;
    private String image;
    private Float score;
    private String explanation;
    private String videoLink;
    //campsite 가져오는 부분 위도,경도 추가
    private String lat;
    private String lng;
    private String facilities;
    private String registrant;


    public static ResponseCampSiteDto convertCampSiteDto(CampSite campSite,String nickname) {
        return ResponseCampSiteDto.builder()
                .campsite_id(campSite.getCampsite_id())
                .name(campSite.getName())
                .address(campSite.getAddress())
                .image(campSite.getImages())
                .score(campSite.getScore())
                .explanation(campSite.getExplanation())
                .videoLink(campSite.getVideoLink())
                .lat(campSite.getLat())
                .lng(campSite.getLng())
                .facilities(campSite.getFacilities())
                .registrant(nickname)
                .build();
    }
}
