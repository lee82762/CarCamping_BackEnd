package com.Hanium.CarCamping.controller.ChangeCampSite;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.campsite.ChangeCampSiteDto;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.service.CampSite.CampsiteService;
import com.Hanium.CarCamping.service.ChangeCampSite.ChangeCampSiteService;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChangeCampSiteController {
    private final ChangeCampSiteService changeCampSiteService;
    private final CampsiteService campsiteService;
    private final JwtService jwtService;
    private final ResponseService responseService;

    @PostMapping("/change/campsite")
    public Result createChange(@RequestHeader("token") String token, @RequestBody ChangeCampSiteDto changeCampSiteDto) {
        jwtService.isUsable(token);
        campsiteService.createChange(jwtService.findEmailByJwt(token), changeCampSiteDto);
        return responseService.getSuccessResult();
    }

    @PatchMapping("/change/campsite/{changeCampsite_id}")
    public Result applyChange(@RequestHeader("token") String token,@PathVariable Long changeCampsite_id) {
        jwtService.isUsable(token);
        campsiteService.applyChange(changeCampsite_id);
        return responseService.getSuccessResult();
    }
    @DeleteMapping("/change/campsite/{changeCampsite_id}")
    public Result deleteChange(@RequestHeader("token") String token,@PathVariable Long changeCampsite_id) {
        jwtService.isUsable(token);
        campsiteService.rejectChange(changeCampsite_id);
        return responseService.getSuccessResult();
    }
    @GetMapping("/change/campsite/list")
    public Result getChangeList(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        return responseService.getListResult(changeCampSiteService.getChangeList());
    }
    @GetMapping("/change/campsite/{changeCampsite_id}")
    public Result getChangeCampSite(@RequestHeader("token") String token,@PathVariable Long changeCampsite_id) {
        return responseService.getSingleResult(changeCampSiteService.getChangCampSite(changeCampsite_id));
    }

}
