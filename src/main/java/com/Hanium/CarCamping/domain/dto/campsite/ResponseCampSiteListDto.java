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
public class ResponseCampSiteListDto {
    private Long campsite_id;
    private String name;
    private String address;
    private Float Score;

    public static ResponseCampSiteDto convertResponseCampSiteDto(CampSite campsite) {
        return ResponseCampSiteDto.builder()
                .campsite_id(campsite.getCampsite_id())
                .name(campsite.getName())
                .address(campsite.getAddress())
                .score(campsite.getScore()).build();
    }
}
