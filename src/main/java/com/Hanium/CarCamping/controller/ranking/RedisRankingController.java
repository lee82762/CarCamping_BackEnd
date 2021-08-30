package com.Hanium.CarCamping.controller.ranking;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.member.ResponseRankingDto;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RedisRankingController {
    private final RedisTemplate redisTemplate;
    private final ResponseService responseService;
    private final JwtService jwtService;

    @GetMapping("/ranking")
    public Result getRankingList() {
        String key = "ranking";
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, 10);
        List<ResponseRankingDto> collect = typedTuples.stream().map(ResponseRankingDto::convertToResponseRankingDto).collect(Collectors.toList());
        return responseService.getListResult(collect);
    }
    @GetMapping("/myRank")
    public Result getMyRank(@RequestHeader("token") String token) {
        Long ranking = redisTemplate.opsForZSet().reverseRank("ranking", jwtService.findMemberByToken(token).getNickname());
        return responseService.getSingleResult(ranking+1);
    }

}
