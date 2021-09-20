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

public class ResponseWaitingCampSiteListDto {
    private Long campsite_id;
    private String name;
    private String address;

    public static ResponseWaitingCampSiteListDto convertResponseWaitingCampSiteDto(WaitingCampSite waitingCampSite) {
        return ResponseWaitingCampSiteListDto.builder()
                .campsite_id(waitingCampSite.getWaitingCampSite_id())
                .name(waitingCampSite.getName())
                .address(waitingCampSite.getAddress())
                .build();
    }
}
