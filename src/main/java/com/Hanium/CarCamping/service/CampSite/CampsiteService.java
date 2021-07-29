package com.Hanium.CarCamping.service.CampSite;

import com.Hanium.CarCamping.Exception.NoSuchCampSiteException;
import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.CampSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampsiteService {
    private final CampSiteRepository campSiteRepository;

    @Transactional
    public void saveCampSite(CreateCampSiteDto createCampSiteDto, Member member) {
        campSiteRepository.save(CampSite.createCampSite(createCampSiteDto,member));
    }

    public CampSite findById(Long id) {
        return campSiteRepository.getById(id);
    }
    public List<CampSite> findByRegion(Region region) {
        return campSiteRepository.findByRegion(region);
    }

    public CampSite findByName(String name) {
        return campSiteRepository.findByName(name).orElseThrow(NoSuchCampSiteException::new);
    }
}
