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
public class ResponseChangeCampSiteListDto {
    private Long changeCampSite_id;
    private Long campSite_id;
    private String name;
    private String address;

    public static ResponseChangeCampSiteListDto convertToListDto(ChangeCampSite changeCampSite) {
        return ResponseChangeCampSiteListDto.builder()
                .changeCampSite_id(changeCampSite.getChangeCampSite_id())
                .campSite_id(changeCampSite.getCampsite_id())
                .name(changeCampSite.getName())
                .address(changeCampSite.getAddress())
                .build();
    }
}
