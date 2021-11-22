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
    @ExceptionHandler(alreadyReportReviewException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result alreadyReportReviewException() {
        return responseService.getFailResult(-1009, "이미 신고한 리뷰입니다.");
    }
    @ExceptionHandler(NoSuchChangeRequestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoSuchChangeRequestException() {
        return responseService.getFailResult(-1010, "존재하지 않는 변경 요청입니다.");
    }




    //인증관련
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result AccessDeniedException() {
        return responseService.getFailResult(-1009, "해당 작업에 권한이 없습니다.");
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result AuthenticationEntryPointException() {
        return responseService.getFailResult(-1010, "인증정보가 유효하지 않습니다.");
    }


    //로그인 및 로그인 관련
    @ExceptionHandler(DuplicatedEmailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result DuplicatedEmailException() {
        return responseService.getFailResult(-1011, "이미 존재하는 이메일입니다.");
    }
    @ExceptionHandler(DuplicatedNickNameException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result DuplicatedNickNameException() {
        return responseService.getFailResult(-1012, "이미 존재하는 닉네임입니다.");
    }
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result MemberNotFoundException() {
        return responseService.getFailResult(-1013, "찾을 수 없는 회원입니다.");
    }
    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result WrongPasswordException() {
        return responseService.getFailResult(-1014, "패스워드가 틀렸습니다.");
    }
    @ExceptionHandler(UserDefineException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result UserDefineException() {
        return responseService.getFailResult(-1015, "키를 변환하는데 실패했습니다.");
    }

}
