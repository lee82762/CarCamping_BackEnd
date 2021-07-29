package com.Hanium.CarCamping.service.CampSite;

import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.repository.CampSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampsiteService {
    private final CampSiteRepository campSiteRepository;

    public CampSite findById(Long id) {
        CampSite result = campSiteRepository.getById(id);
        return result;
    }
}
