package com.Hanium.CarCamping.domain.dto.campsite;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindCampSiteDto {
    private String word;
    private String region;
}
