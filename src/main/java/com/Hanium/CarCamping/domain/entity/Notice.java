package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.dto.Notice.CreateNoticeDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private LocalDateTime date;

    static public Notice createNotice(CreateNoticeDto createNoticeDto,String name) {
        Notice notice=new Notice();
        notice.title=createNoticeDto.getTitle();
        notice.contents=createNoticeDto.getContent();
        notice.writer=name;
        notice.date=LocalDateTime.now();
        return notice;

    }
}
