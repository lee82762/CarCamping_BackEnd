package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.entity.CampSite;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long review_id;
/*
    @Column(nullable = false)
    private User writer;
 */
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="campsite_id")
    private CampSite campSite;

    private Integer recommend;



}
