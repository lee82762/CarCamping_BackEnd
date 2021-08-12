package com.Hanium.CarCamping.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReviewDto {
    private String title;
    private String content;
    private Float score;
}
