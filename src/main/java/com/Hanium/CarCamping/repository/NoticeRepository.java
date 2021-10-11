package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,Long> {

    @Query("select n from Notice n order by n.date DESC ")
    List<Notice> findByDate();
}
