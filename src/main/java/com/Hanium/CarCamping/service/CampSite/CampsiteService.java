package com.Hanium.CarCamping.service.CampSite;

import com.Hanium.CarCamping.Exception.NoSuchCampSiteException;
import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.repository.CampSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampsiteService {
    private final CampSiteRepository campSiteRepository;

    public CampSite findById(Long id) {
        CampSite result = campSiteRepository.getById(id);
        return result;
    }
    public List<CampSite> findByRegion(Region region) {
        return campSiteRepository.findByRegion(region);
    }

    public CampSite findByName(String name) {
        return campSiteRepository.findByName(name).orElseThrow(NoSuchCampSiteException::new);
    }
}
