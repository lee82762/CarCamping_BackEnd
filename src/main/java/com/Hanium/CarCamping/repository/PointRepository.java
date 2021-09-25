package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointRepository extends JpaRepository<Point,Long> {

    @Query("select p from Point p join fetch p.owner m where p.owner.id=:member_id")
    List<Point> findByMemberID(Long member_id);


    @Query("select p from Point p join fetch p.owner m where p.owner.id=:member_id order by p.id DESC ")
    List<Point> findByMemberIDDesc(Long member_id);
}
