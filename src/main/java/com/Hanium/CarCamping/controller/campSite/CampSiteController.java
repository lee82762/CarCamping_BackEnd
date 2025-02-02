package com.Hanium.CarCamping.controller.campSite;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.dto.campsite.*;
import com.Hanium.CarCamping.domain.dto.member.checkDto;
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
        CampSite campSite = campsiteService.findByName(name);
        String nickName = campsiteService.findNickName(name);
        System.out.println(nickName);
        return responseService.getSingleResult(ResponseCampSiteDto.convertCampSiteDto(campSite,nickName));
    }

    @GetMapping("/camping/{location}/grade")
    public Result getLocationCampSiteListByGrade(@RequestHeader("token") String token, @PathVariable String location) {
        jwtService.isUsable(token);
        List<CampSite> byRegion = campsiteService.getCampSiteByRegionAndScoreDESC(Region.valueOf(location));
        return responseService.getListResult(byRegion.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }

    @GetMapping("/camping/{campsite_id}")
    public Result getSingleCampSite(@RequestHeader("token") String token, @PathVariable Long campsite_id) {
        jwtService.isUsable(token);
        CampSite result = campsiteService.findById(campsite_id);
        String nickName = campsiteService.findNickName(result.getName());
        return responseService.getSingleResult(ResponseCampSiteDto.convertCampSiteDto(result,nickName));
    }

    @DeleteMapping("/camping/delete/{campsite_id}")
    public Result deleteCampSite(@RequestHeader("token") String token, @PathVariable Long campsite_id) {
        jwtService.isUsable(token);
        campsiteService.deleteCampSite(jwtService.findEmailByJwt(token), campsite_id);
        return responseService.getSuccessResult();
    }

    @GetMapping("camping/all/dateasc")
    public Result allCampSiteAsc(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        List<CampSite> allCampSiteList = campsiteService.getAllCampSiteList();
        return responseService.getListResult(allCampSiteList.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }
    @GetMapping("camping/all/datedesc")
    public Result allCampSiteDesc(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        List<CampSite> allCampSiteList = campsiteService.getAllCampSiteListDesc();
        return responseService.getListResult(allCampSiteList.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }

    @GetMapping("camping/all/scoreasc")
    public Result allCampSiteScoreAsc(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        List<CampSite> allCampSiteList = campsiteService.getAllCampSiteListScoreAsc();
        return responseService.getListResult(allCampSiteList.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }


    @GetMapping("camping/all/scoredesc")
    public Result allCampSiteScoreDesc(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        List<CampSite> allCampSiteList = campsiteService.getAllCampSiteListScoreDesc();
        return responseService.getListResult(allCampSiteList.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }



    @PostMapping("camping/map")
    public Result allCampSiteCoordinate(@RequestHeader("token") String token,@RequestBody RegionDto regionDto) {
        System.out.println(regionDto.getRegion());
        jwtService.isUsable(token);
        List<CampSite> allCampSiteList = campsiteService.getCampSiteByRegion(Region.valueOf(regionDto.getRegion()));
        return responseService.getListResult(allCampSiteList.stream().map(ResponseCoordinateDto::convertToCoordinateDto).collect(Collectors.toList()));
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

 /*   @GetMapping("/camping/all")
    public Result getCampSiteListAll(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        List<CampSite> allRegion = campsiteService.getAllCampSiteList();
        return responseService.getListResult(allRegion.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }*/
    @PostMapping("/camping/search")
    public Result searchCampSite(@RequestHeader("token") String token, @RequestBody checkDto checkdto) {
        jwtService.isUsable(token);
        List<CampSite> result = campsiteService.getCampSiteBySearchWord(checkdto.getCheck());
        return responseService.getListResult(result.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
    }
    @PostMapping("/camping/search/region")
    public Result searchCampSiteByRegionAndWord(@RequestHeader("token") String token,@RequestBody FindCampSiteDto findCampSiteDto) {
        jwtService.isUsable(token);
        List<CampSite> result = campsiteService.getCampSiteBySearchWordAndRegion(findCampSiteDto.getWord(),Region.valueOf(findCampSiteDto.getRegion()));
        return responseService.getListResult(result.stream().map(ResponseCampSiteListDto::convertResponseCampSiteDto).collect(Collectors.toList()));
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
