package com.Hanium.CarCamping.domain.dto.campsite;

import com.Hanium.CarCamping.domain.entity.CampSite;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseCoordinateDto {
    private Long campsite_id;
    private float lat;
    private float lng;

    public static ResponseCoordinateDto convertToCoordinateDto(CampSite campSite) {
        ResponseCoordinateDto responseCoordinateDto=new ResponseCoordinateDto();
        responseCoordinateDto.campsite_id= campSite.getCampsite_id();
        responseCoordinateDto.lat=Float.valueOf(campSite.getLat());
        responseCoordinateDto.lng=Float.valueOf(campSite.getLat());
        return responseCoordinateDto;


    }
}
