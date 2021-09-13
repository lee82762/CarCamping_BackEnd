package com.Hanium.CarCamping.controller.campSite;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.dto.campsite.*;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.CampSite;
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
    public Result registerCampSite(@RequestHeader("token") String token, @RequestBody CreateCampSiteDto createCampSiteDto)  {
        campsiteService.saveCampSite(createCampSiteDto,jwtService.findEmailByJwt(token));
        return responseService.getSuccessResult();
    }

    @GetMapping("/camping")
    public Result getByName(@RequestHeader("token") String token, @RequestParam String name) {
        jwtService.isUsable(token);
        return responseService.getSingleResult(ResponseCampSiteDto.convertCampSiteDto(campsiteService.findByName(name)));
    }

    @GetMapping("/camping/{location}/grade")
    public Result getLocationCampSiteListByGrade(@RequestHeader("token") String token, @PathVariable String location) {
        jwtService.isUsable(token);
        List<CampSite> byRegion = campsiteService.getCampSiteByRegionAndScoreDESC(Region.valueOf(location));
        return responseService.getListResult(byRegion.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }

    @GetMapping("/camping/{location}/gradeasc")
    public Result getLocationCampSiteListByGradeAsc(@RequestHeader("token") String token, @PathVariable String location) {
        jwtService.isUsable(token);
        List<CampSite> byRegion = campsiteService.getCampSiteByRegionAndScoreASC(Region.valueOf(location));
        return responseService.getListResult(byRegion.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }

    @GetMapping("/camping/{location}/dateasc")
    public Result getLocationCampSiteListByDateAsc(@RequestHeader("token") String token, @PathVariable String location) {
        jwtService.isUsable(token);
        List<CampSite> byRegion = campsiteService.getCampSiteByRegionAndDateASC(Region.valueOf(location));
        return responseService.getListResult(byRegion.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }

    @GetMapping("/camping/{location}/datedesc")
    public Result getLocationCampSiteListByDateDesc(@RequestHeader("token") String token, @PathVariable String location) {
        jwtService.isUsable(token);
        List<CampSite> byRegion = campsiteService.getCampSiteByRegionAndDateDESC(Region.valueOf(location));
        return responseService.getListResult(byRegion.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }

    @GetMapping("/camping/{campsite_id}")
    public Result getSingleCampSite(@RequestHeader("token") String token, @PathVariable Long campsite_id) {
        jwtService.isUsable(token);
        CampSite result = campsiteService.findById(campsite_id);
        return responseService.getSingleResult(ResponseCampSiteDto.convertCampSiteDto(result));
    }

    @DeleteMapping("/camping/delete/{campsite_id}")
    public Result deleteCampSite(@RequestHeader("token") String token, @PathVariable Long campsite_id) {
        jwtService.isUsable(token);
        campsiteService.deleteCampSite(jwtService.findEmailByJwt(token), campsite_id);
        return responseService.getSuccessResult();
    }

    @GetMapping("camping/all")
    public Result allCampSite(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        List<CampSite> allCampSiteList = campsiteService.getAllCampSiteList();
        return responseService.getListResult(allCampSiteList.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }
    @PostMapping("camping/map")
    public Result allCampSiteCoordinate(@RequestHeader("token") String token,@RequestBody RegionDto regionDto) {
        System.out.println(regionDto.getRegion());
        jwtService.isUsable(token);
        List<CampSite> allCampSiteList = campsiteService.getCampSiteByRegion(Region.valueOf(regionDto.getRegion()));
        return responseService.getListResult(allCampSiteList.stream().map(ResponseCoordinateDto::convertToCoordinateDto).collect(Collectors.toList()));
    }

 /*   @GetMapping("camping/location")
    public  Result campLocation(){
        String location = "서울특별시 서경로 15길 37";
        Double result[]=campsiteService.getGeoDataByAddress(location);
        System.out.println("위도="+result[0]);
        System.out.println("경도="+result[1]);
        return responseService.getSuccessResult();
    }*/
}