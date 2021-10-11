package com.Hanium.CarCamping.controller.notice;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.Notice.CreateNoticeDto;
import com.Hanium.CarCamping.domain.dto.Notice.ResponseNoticeDto;
import com.Hanium.CarCamping.domain.dto.Notice.ResponseOneNoticeDto;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.Notice;
import com.Hanium.CarCamping.service.Notice.NoticeService;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoticeController {
    private final NoticeService noticeService;
    private final JwtService jwtService;
    private final ResponseService responseService;

    @PostMapping("/notice/register")
    public Result registerNotice(@RequestBody CreateNoticeDto createNoticeDto, @RequestHeader("token") String token) {
        jwtService.isUsable(token);
        String name=jwtService.findMemberByToken(token).getNickname();
        noticeService.createNotice(createNoticeDto,name);
        return responseService.getSuccessResult();
    }

    @GetMapping("/notice/all")
    public Result getNoticeList(@RequestHeader("token") String token) {
        jwtService.isUsable(token);
        List<Notice> result = noticeService.getAllNotice();
        return responseService.getListResult(result.stream().map(ResponseNoticeDto::convertToNoticeDto).collect(Collectors.toList()));
    }

    @GetMapping("/notice/{id}")
    public Result getSingleNotice(@RequestHeader("token") String token, @PathVariable Long id) {
        jwtService.isUsable(token);
        Notice notice = noticeService.findById(id);
        return responseService.getSingleResult(ResponseOneNoticeDto.convertToNoticeDto(notice));
    }
}
