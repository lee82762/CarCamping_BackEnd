package com.Hanium.CarCamping.domain.entity;

import com.Hanium.CarCamping.domain.entity.member.Member;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Point {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;
    private String contents;
    private int score;
    private LocalDateTime datetime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member owner;

    static public Point createPoint(Member member,String contents,int score) {
        Point point=new Point();
        point.contents=contents;
        point.datetime=LocalDateTime.now();
        point.score=score;
        point.owner=member;
        return point;
    }

}
