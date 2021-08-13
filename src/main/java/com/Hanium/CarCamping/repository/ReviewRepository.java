package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {


    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id order by r.date DESC")
    List<Review> findByCampSiteDateDESC(@Param("campsite_id")Long campSite_id);

    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id order by r.date ASC")
    List<Review> findByCampSiteDateASC(@Param("campsite_id")Long campSite_id);


    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id order by r.score DESC")
    List<Review> findByCampSiteDESC(@Param("campsite_id")Long campSite_id);

    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id order by r.score ASC")
    List<Review> findByCampSiteASC(@Param("campsite_id")Long campSite_id);

    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id")
    List<Review> findReviewByCampSite(@Param("campsite_id")Long campSite_id);

}
