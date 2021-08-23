package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point,Long> {
}
