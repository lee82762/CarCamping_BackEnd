package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.Review;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id order by r.date DESC")
    Slice<Review> findByCampSiteDateDESC(CampSite campSite);

    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id order by r.date ASC")
    Slice<Review> findByCampSiteDateASC(CampSite campSite);


    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id order by r.score DESC")
    Slice<Review> findByCampSiteDESC(@Param("campsite_id")Long campSite_id);

    @Query("select r from Review r where r.campSite.campsite_id=:campsite_id order by r.score ASC")
    Slice<Review> findByCampSiteASC(@Param("campsite_id")Long campSite_id);

}
