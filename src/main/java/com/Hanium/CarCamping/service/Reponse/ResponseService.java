package com.Hanium.CarCamping.service.Reponse;

import com.Hanium.CarCamping.domain.dto.response.ListResult;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.dto.response.SingleResult;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setData(list);
        result.setHasNext(false);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> list, boolean hasNext) {
        ListResult<T> result = new ListResult<>();
        result.setData(list);
        result.setHasNext(hasNext);
        setSuccessResult(result);
        return result;
    }
    public Result getSuccessResult() {
        Result result = new Result();
        setSuccessResult(result);
        return result;
    }

    public Result getSuccessResultHeader(String jwt){
        Result result=new Result();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("token",jwt);
        return result;
    }
    public Result getFailResult(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    private <T> void setSuccessResult(Result result) {
        result.setSuccess(true);
        result.setCode(0);
        result.setMsg("success");
    }
}
