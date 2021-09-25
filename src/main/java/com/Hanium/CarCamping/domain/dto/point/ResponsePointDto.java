package com.Hanium.CarCamping.domain.dto.point;

import com.Hanium.CarCamping.domain.entity.Point;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponsePointDto {
    private Long point_id;
    private String contents;
    private LocalDateTime date;
    private int score;
    private int scoresum;

    public static ResponsePointDto convertToPointDto(Point point){
        return ResponsePointDto.builder()
                .point_id(point.getId())
                .contents(point.getContents())
                .date(point.getDatetime())
                .score(point.getScore())
                .scoresum(0)
                .build();
    }
}
