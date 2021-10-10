package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeCampSite {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ChangeCampSite_id;

    private Long campsite_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;


    @Enumerated(value = EnumType.STRING)
    private Region region;

    private String explanation;

    private String images;

    private String videoLink;


    //컬럼 추가
    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private  String lng;

    private String facilities;

}
