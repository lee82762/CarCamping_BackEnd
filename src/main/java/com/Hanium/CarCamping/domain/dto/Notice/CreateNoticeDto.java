package com.Hanium.CarCamping.domain.dto.Notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNoticeDto {

    private String title;
    private String content;
    private String writer;
}
