package com.Hanium.CarCamping.domain.dto.campsite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCampSiteDto {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private String image;
    @NotBlank
    private String explanation;
    private String facilities;
    private String videoLink;
    private String region;

}
