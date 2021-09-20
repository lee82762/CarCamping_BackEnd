package com.Hanium.CarCamping.controller.waitingCampSite;

import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import com.Hanium.CarCamping.service.WaitingCampSite.WaitingCampSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/waitingCampSite")
public class WaitingCampSiteController {
    private final WaitingCampSiteService waitingCampSiteService;
    private final ResponseService responseService;

    @GetMapping("/approval/{waitingCampSite_id}")
    public Result approveCampSite(@PathVariable Long waitingCampSite_id) {
        waitingCampSiteService.convertToCampSite(waitingCampSite_id);
        return responseService.getSuccessResult();
    }

    @GetMapping("/disapproval/{waitingCampSite_id}")
    public Result disapproveCampSite(@PathVariable Long waitingCampSite_id) {
        waitingCampSiteService.deleteWaitingCampSite(waitingCampSite_id);
        return responseService.getSuccessResult();
    }

    @GetMapping("/list")
    public Result getWaitingCampSiteList() {
        return responseService.getListResult(waitingCampSiteService.getWaitingCampSiteList());
    }
    @GetMapping("/{waitingCampSite_id}")
    public Result getWaitingCampSite(@PathVariable Long waitingCampSite_id) {
        return responseService.getSingleResult(waitingCampSiteService.getWaitingCampSite(waitingCampSite_id));
    }
}
