package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campsite_id")
    private Long campsite_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;


    @OneToMany(mappedBy ="campSite")
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false)
    private Float score;

    @OneToOne
    @JoinColumn(name = "registrant_id")
    private Member registrant;
/*
    @Column(nullable = false)
    private User registrant;

 */


}
