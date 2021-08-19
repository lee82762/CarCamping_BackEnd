package com.Hanium.CarCamping.service.CampSite;

import com.Hanium.CarCamping.Exception.DuplicateCampSiteException;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampsiteService {
    private final CampSiteRepository campSiteRepository;

    @Transactional
    public Long saveCampSite(CreateCampSiteDto createCampSiteDto, Member member) {
        Optional<CampSite> byName = campSiteRepository.findByName(createCampSiteDto.getName());
        if (byName.isPresent()) {
            if (byName.get().getRegion().toString().equals(createCampSiteDto.getRegion())) {
                throw new DuplicateCampSiteException("이미 등록되어있는 차박지입니다");
            }
        }
        CampSite save = campSiteRepository.save(CampSite.createCampSite(createCampSiteDto, member));
        return save.getCampsite_id();
    }

    public CampSite findById(Long id) {
        return campSiteRepository.getById(id);
    }
    public List<CampSite> findByRegion(Region region) {
        System.out.println(region.name());
        return campSiteRepository.findByRegion(region);
    }

    public CampSite findByName(String name) {
        return campSiteRepository.findByName(name).orElseThrow(NoSuchCampSiteException::new);
    }
    public List<CampSite> getAllCampSiteList() {
        return
                campSiteRepository.findAll();
    }
}
