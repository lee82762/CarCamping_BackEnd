package com.Hanium.CarCamping.domain.entity.member;

import com.Hanium.CarCamping.domain.entity.Review;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String email;


    private String password;


    private String nickname;


    @ColumnDefault("0")
    private Integer point;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    //수정

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

/*    @OneToMany(mappedBy = "member_id",cascade = CascadeType.ALL)
    private Set<Review_Member> review_members = new HashSet<>();*/

/*
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Review review_id;


 */


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
        this.point=point;
        this.role=role;

    }
}