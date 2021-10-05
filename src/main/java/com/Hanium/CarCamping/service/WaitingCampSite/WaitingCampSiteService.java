package com.Hanium.CarCamping.service.WaitingCampSite;

import com.Hanium.CarCamping.Exception.NoSuchWaitingCampSiteException;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.dto.waitingcampsite.ResponseWaitingCampSiteDto;
import com.Hanium.CarCamping.domain.dto.waitingcampsite.ResponseWaitingCampSiteListDto;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.WaitingCampSite;
import com.Hanium.CarCamping.repository.CampSiteRepository;
import com.Hanium.CarCamping.repository.WaitingCampSiteRepository;
import com.Hanium.CarCamping.service.Point.PointService;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WaitingCampSiteService {
    private final WaitingCampSiteRepository waitingCampSiteRepository;
    private final CampSiteRepository campSiteRepository;
    private final PointService pointService;
    private final RedisTemplate redisTemplate;

    @Transactional
    public void convertToCampSite(Long waitingCampSite_id) {
        WaitingCampSite waitingCampSite = waitingCampSiteRepository.findById(waitingCampSite_id).orElseThrow(NoSuchWaitingCampSiteException::new);
        CampSite campSite = CampSite.convertToCampSite(waitingCampSite);
        campSiteRepository.save(campSite);
        waitingCampSiteRepository.delete(waitingCampSite);
        pointService.create(campSite.getRegistrant(),"차박지 등록",100);
    }
    @Transactional
    public void deleteWaitingCampSite(Long waitingCampSite_id) {
        WaitingCampSite waitingCampSite = waitingCampSiteRepository.findById(waitingCampSite_id).orElseThrow(NoSuchWaitingCampSiteException::new);
        waitingCampSiteRepository.delete(waitingCampSite);
    }
    public List<ResponseWaitingCampSiteListDto> getWaitingCampSiteList() {
        List<WaitingCampSite> all = waitingCampSiteRepository.findAll();
        return all.stream().map(ResponseWaitingCampSiteListDto::convertResponseWaitingCampSiteDto).collect(Collectors.toList());
    }
    public ResponseWaitingCampSiteDto getWaitingCampSite(Long waitingCampSite_id) {
        WaitingCampSite waitingCampSite = waitingCampSiteRepository.findById(waitingCampSite_id).orElseThrow(NoSuchWaitingCampSiteException::new);
        return ResponseWaitingCampSiteDto.convertToWaitingCampSiteDto(waitingCampSite);
    }

}
