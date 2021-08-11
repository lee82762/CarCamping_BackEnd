package com.Hanium.CarCamping.domain.dto.campsite;

import com.Hanium.CarCamping.domain.entity.CampSite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseCampSiteDto {
    private Long campsite_id;
    private String name;
    private String address;
    private String image;
    private Float score;

    public static ResponseCampSiteDto convertCampSiteDto(CampSite campSite) {
        return ResponseCampSiteDto.builder()
                .campsite_id(campSite.getCampsite_id())
                .name(campSite.getName())
                .address(campSite.getAddress())
                .image(campSite.getImage())
                .score(campSite.getScore()).build();
    }
}
