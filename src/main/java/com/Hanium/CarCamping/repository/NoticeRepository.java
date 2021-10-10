package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
