package com.Hanium.CarCamping.service.Point;

import com.Hanium.CarCamping.domain.entity.Point;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    @Transactional
    public void create(Member member,String content, int score) {
        Point point = Point.createPoint(member, content, score);
        member.setPoint(member.getPoint()+score);
        System.out.println(member.getPoint());
        pointRepository.save(point);
    }

    public List<Point> getAllPointList(Long member_id) {
        return pointRepository.findByMemberID(member_id);
    }


}
