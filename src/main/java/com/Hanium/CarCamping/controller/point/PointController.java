package com.Hanium.CarCamping.controller.point;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.point.ResponsePointDto;
import com.Hanium.CarCamping.domain.dto.response.ListResult;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.Point;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.service.Point.PointService;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PointController {

    private final PointService pointService;
    private final JwtService jwtService;
    private final ResponseService responseService;

    @GetMapping("/point")
    public Result getByID(@RequestHeader("token") String token){
        jwtService.isUsable(token);
        Member member=jwtService.findMemberByToken(token);
        List<Point> result= pointService.getAllPointList(member.getId());
        ListResult<ResponsePointDto> list=responseService.getListResult(result.stream().map(ResponsePointDto::convertToPointDto).collect(Collectors.toList()));
        int answer=0;
        for(int i=0; i<result.size(); i++) {
            answer += result.get(i).getScore();
            list.getData().get(i).setScoresum(answer);
        }
        return list;
    }

    @GetMapping("/pointDesc")
    public Result getByIDDesc(@RequestHeader("token") String token){
        jwtService.isUsable(token);
        Member member=jwtService.findMemberByToken(token);
        List<Point> result= pointService.getAllPointListDesc(member.getId());
        ListResult<ResponsePointDto> list=responseService.getListResult(result.stream().map(ResponsePointDto::convertToPointDto).collect(Collectors.toList()));
        int answer=0;
        for(int i=result.size()-1; i>=0; i--) {
            answer += result.get(i).getScore();
            list.getData().get(i).setScoresum(answer);
        }
        return list;
    }
}
