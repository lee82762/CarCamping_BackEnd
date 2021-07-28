package com.Hanium.CarCamping.domain.entity;

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
    @OneToMany(mappedBy ="campSite")
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false)
    private Float score;
/*
    @Column(nullable = false)
    private User registrant;

 */


}
