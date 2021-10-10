package com.Hanium.CarCamping.domain.dto.Notice;

import com.Hanium.CarCamping.domain.entity.Notice;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseOneNoticeDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime date;

    public static ResponseOneNoticeDto convertToNoticeDto(Notice notice) {
        return ResponseOneNoticeDto.builder()
                .id(notice.getId())
                .writer(notice.getWriter())
                .title(notice.getTitle())
                .content(notice.getContents())
                .date(notice.getDate())
                .build();
    }
}
