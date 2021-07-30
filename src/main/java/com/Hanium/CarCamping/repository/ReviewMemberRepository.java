package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.Review_Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMemberRepository extends JpaRepository<Review_Member,Long> {
}
