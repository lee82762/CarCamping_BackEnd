package com.Hanium.CarCamping.service.Point;

import com.Hanium.CarCamping.domain.entity.Point;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.domain.entity.member.Role;
import com.Hanium.CarCamping.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    private final RedisTemplate redisTemplate;

    @Transactional
    public void create(Member member,String content, int score) {
        Point point = Point.createPoint(member, content, score);
        member.setPoint(member.getPoint()+score);
        if (member.getRole() != Role.ADMIN) {
            redisTemplate.opsForZSet().add("ranking", member.getNickname(), member.getPoint());
        }
        pointRepository.save(point);
    }

    public List<Point> getAllPointList(Long member_id) {
        return pointRepository.findByMemberID(member_id);
    }

    public List<Point> getAllPointListDesc(Long member_id) {
        return pointRepository.findByMemberIDDesc(member_id);
    }


}
