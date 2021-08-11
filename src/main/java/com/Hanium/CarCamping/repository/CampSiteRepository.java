package com.Hanium.CarCamping.repository;

import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.entity.CampSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CampSiteRepository extends JpaRepository<CampSite,Long> {

    @Query("select c from CampSite c where c.region=:region order by c.score DESC ")
    List<CampSite> findByRegion(@RequestParam("region") Region region);

    Optional<CampSite> findByName(String name);


}
