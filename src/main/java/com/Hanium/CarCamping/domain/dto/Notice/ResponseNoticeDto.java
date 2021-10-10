package com.Hanium.CarCamping.domain.dto.Notice;

import com.Hanium.CarCamping.domain.entity.Notice;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseNoticeDto {
    private Long id;
    private String content;
    private String writer;
    private String title;
    private LocalDateTime date;

    public static ResponseNoticeDto convertToNoticeDto(Notice notice) {
        return ResponseNoticeDto.builder()
                .id(notice.getId())
                .writer(notice.getWriter())
                .title(notice.getTitle())
                .content(notice.getContents())
                .date(notice.getDate())
                .build();
    }
}
