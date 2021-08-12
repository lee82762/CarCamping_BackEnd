package com.Hanium.CarCamping.domain.dto.campsite;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCampSiteDto {

    private String name;

    private String address;
    private String image;

    private String explanation;
    private Float score;
    private String videoLink;
    private String region;
}
