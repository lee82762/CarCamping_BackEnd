package com.Hanium.CarCamping.domain.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Data
@NoArgsConstructor
public class ResponseRankingDto {
    private String nickname;
    private int score;
    public static ResponseRankingDto convertToResponseRankingDto(ZSetOperations.TypedTuple typedTuple){
        ResponseRankingDto responseRankingDto=new ResponseRankingDto();
        responseRankingDto.nickname=typedTuple.getValue().toString();
        responseRankingDto.score=typedTuple.getScore().intValue();
        return responseRankingDto;
    }


}
