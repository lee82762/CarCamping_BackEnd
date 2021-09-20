package com.Hanium.CarCamping.domain.dto.waitingcampsite;

import com.Hanium.CarCamping.domain.dto.campsite.ResponseCampSiteDto;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.WaitingCampSite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResponseWaitingCampSiteDto {
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

    public static ResponseWaitingCampSiteDto convertToWaitingCampSiteDto(WaitingCampSite waitingCampSite) {
        return ResponseWaitingCampSiteDto.builder()
                .campsite_id(waitingCampSite.getWaitingCampSite_id())
                .name(waitingCampSite.getName())
                .address(waitingCampSite.getAddress())
                .image(waitingCampSite.getImages())
                .score(waitingCampSite.getScore())
                .explanation(waitingCampSite.getExplanation())
                .videoLink(waitingCampSite.getVideoLink())
                .lat(waitingCampSite.getLat())
                .lng(waitingCampSite.getLng())
                .facilities(waitingCampSite.getFacilities())
                .build();
    }
}
