package com.Hanium.CarCamping.domain.entity.member;

import com.Hanium.CarCamping.domain.entity.Point;
import com.Hanium.CarCamping.domain.entity.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "point")
    @ColumnDefault("0")
    private Integer point;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "writer",orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();
    @OneToMany(mappedBy="owner",orphanRemoval = true)
    private List<Point> pointList=new ArrayList<>();
    private String profile;
    @Builder
    public Member(final String email,
                  final String password,
                  final String nickname,
                  final Integer point,
                  final Role role
    ) {

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.point = point;
        this.role = role;

    }
}