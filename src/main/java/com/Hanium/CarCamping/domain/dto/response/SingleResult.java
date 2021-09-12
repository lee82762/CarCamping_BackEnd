package com.Hanium.CarCamping.domain.dto.response;

import lombok.Data;

@Data
public class SingleResult<T> extends Result {
    private T data;
}