package com.Hanium.CarCamping.domain.entity.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

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
    private com.Hanium.CarCamping.domain.entity.member.Role role;


    @Builder
    public Member(final String email,
                  final String password,
                  final String nickname,
                  final Integer point,
                  final com.Hanium.CarCamping.domain.entity.member.Role role
                  ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.point=point;
        this.role=role;

    }
}
