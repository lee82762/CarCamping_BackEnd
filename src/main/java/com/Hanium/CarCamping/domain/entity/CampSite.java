package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class CampSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campsite_id")
    private Long campsite_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @OneToMany(mappedBy ="campSite",cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false)
    private Float score;

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name="registrant_id")
    private Member registrant;



}
