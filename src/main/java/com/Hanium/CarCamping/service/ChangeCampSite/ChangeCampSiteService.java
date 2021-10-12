package com.Hanium.CarCamping.service.ChangeCampSite;

import com.Hanium.CarCamping.Exception.NoSuchChangeRequestException;
import com.Hanium.CarCamping.domain.dto.campsite.ResponseChangeCampSiteDto;
import com.Hanium.CarCamping.domain.dto.campsite.ResponseChangeCampSiteListDto;
import com.Hanium.CarCamping.domain.entity.ChangeCampSite;
import com.Hanium.CarCamping.repository.ChangeCampSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ChangeCampSiteService {
    private final ChangeCampSiteRepository changeCampSiteRepository;

    public List<ResponseChangeCampSiteListDto> getChangeList() {
        List<ChangeCampSite> result = changeCampSiteRepository.findAll();
        return result.stream().map(ResponseChangeCampSiteListDto::convertToListDto).collect(Collectors.toList());
    }
    public ResponseChangeCampSiteDto getChangCampSite(Long changeCampSite_id) {
        ChangeCampSite changeCampSite = changeCampSiteRepository.findById(changeCampSite_id).orElseThrow(NoSuchChangeRequestException::new);
        return ResponseChangeCampSiteDto.convertToDto(changeCampSite);
    }
}
