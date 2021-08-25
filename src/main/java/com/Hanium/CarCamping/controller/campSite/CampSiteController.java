package com.Hanium.CarCamping.controller.campSite;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.dto.campsite.ResponseCampSiteDto;
import com.Hanium.CarCamping.domain.dto.campsite.ResponseCampSiteListDto;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.service.CampSite.CampsiteService;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CampSiteController {
    private final CampsiteService campsiteService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    @PostMapping("/camping/register")
    public Result registerCampSite(@RequestParam("token") String token, @RequestBody CreateCampSiteDto createCampSiteDto) {
        Member memberByToken = jwtService.findMemberByToken(token);
        campsiteService.saveCampSite(createCampSiteDto, memberByToken);
        return responseService.getSuccessResult();
    }

    @GetMapping("/camping")
    public Result getByName(@RequestParam("token") String token, @RequestParam String name) {
        jwtService.isUsable(token);
        return responseService.getSingleResult(ResponseCampSiteDto.convertCampSiteDto(campsiteService.findByName(name)));
    }

    @GetMapping("/camping/{location}/grade")
    public Result getLocationCampSiteListByGrade(@RequestParam("token") String token, @PathVariable String location) {
        jwtService.isUsable(token);
        List<CampSite> byRegion = campsiteService.getCampSiteByRegionAndScoreDESC(Region.valueOf(location));
        return responseService.getListResult(byRegion.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }

    @GetMapping("/camping/{campsite_id}")
    public Result getSingleCampSite(@RequestParam("token") String token, @PathVariable Long campsite_id) {
        jwtService.isUsable(token);
        CampSite result = campsiteService.findById(campsite_id);
        return responseService.getSingleResult(ResponseCampSiteDto.convertCampSiteDto(result));
    }

    @DeleteMapping("/camping/delete/{campsite_id}")
    public Result deleteCampSite(@RequestParam("token") String token, @PathVariable Long campsite_id) {
        jwtService.isUsable(token);
        Member memberByToken = jwtService.findMemberByToken(token);
        campsiteService.deleteCampSite(memberByToken, campsite_id);
        return responseService.getSuccessResult();
    }

    @GetMapping("camping/all")
    public Result allCampSite(@RequestParam("token") String token) {
        jwtService.isUsable(token);
        List<CampSite> allCampSiteList = campsiteService.getAllCampSiteList();
        return responseService.getListResult(allCampSiteList.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }
}
