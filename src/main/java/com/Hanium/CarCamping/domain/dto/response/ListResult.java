package com.Hanium.CarCamping.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListResult<T> extends Result {
    private List<T> data;
    private boolean hasNext;
}