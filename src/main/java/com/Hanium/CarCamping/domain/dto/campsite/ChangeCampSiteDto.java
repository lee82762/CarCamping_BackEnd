package com.Hanium.CarCamping.domain.dto.campsite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCampSiteDto {
    private String name;
    private String explanation;
    private String images;
    private String videoLink;

}
