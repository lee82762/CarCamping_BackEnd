package com.Hanium.CarCamping.domain.dto.campsite;

import com.Hanium.CarCamping.domain.entity.ChangeCampSite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseChangeCampSiteDto {
    private Long changeCampSite_id;
    private Long campsite_id;
    private String name;
    private String explanation;
    private String images;
    private String videoLink;
    private String facilities;

    public static ResponseChangeCampSiteDto convertToDto(ChangeCampSite changeCampSite) {
        return ResponseChangeCampSiteDto.builder()
                .changeCampSite_id(changeCampSite.getChangeCampSite_id())
                .campsite_id(changeCampSite.getCampsite_id())
                .name(changeCampSite.getName())
                .explanation(changeCampSite.getExplanation())
                .images(changeCampSite.getImages())
                .videoLink(changeCampSite.getVideoLink())
                .facilities(changeCampSite.getFacilities())
                .build();
    }
}
