package com.Hanium.CarCamping.domain.dto.campsite;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCampSiteDto {

    private String name;

    private String address;
    private String image;

    private String explanation;
    private Float score;
    private String videoLink;
    private String region;
}
