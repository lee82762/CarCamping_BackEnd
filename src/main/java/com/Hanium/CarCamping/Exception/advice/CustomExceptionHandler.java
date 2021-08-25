package com.Hanium.CarCamping.Exception.advice;

import com.Hanium.CarCamping.Exception.*;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler {
    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result defaultException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return responseService.getFailResult(-1000, "오류가 발생하였습니다.");
    }

    @ExceptionHandler(NoSuchMemberException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoSuchMemberException() {
        return responseService.getFailResult(-1001, "존재하지 않는 회원입니다.");
    }

    @ExceptionHandler(NoSuchReviewException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoSuchReviewException() {
        return responseService.getFailResult(-1002, "존재하지 않는 리뷰입니다.");
    }

    @ExceptionHandler(NoSuchCampSiteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoSuchCampSiteException() {
        return responseService.getFailResult(-1003, "존재하지 않는 차박지입니다.");
    }
    @ExceptionHandler(DuplicateCampSiteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result DuplicateCampSiteException() {
        return responseService.getFailResult(-1004, "이미 존재하는 차박지입니다.");
    }
    @ExceptionHandler(NotCampSiteRegisterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NotCampSiteRegisterException() {
        return responseService.getFailResult(-1005, "차박지 등록자가 아닙니다.");
    }
    @ExceptionHandler(NotReviewWriterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NotReviewWriterException() {
        return responseService.getFailResult(-1006, "리뷰 등록자가 아닙니다.");
    }
    @ExceptionHandler(CannotRecommendMyReviewException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result CannotRecommendMyReviewException() {
        return responseService.getFailResult(-1007, "내 리뷰는 평가할 수 없습니다.");
    }
    @ExceptionHandler(AlreadyParticipateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result AlreadyParticipateException() {
        return responseService.getFailResult(-1008, "이미 평가한 리뷰입니다.");
    }


}
