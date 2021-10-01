package com.Hanium.CarCamping.controller.ranking;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.member.ResponseRankingDto;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.service.Ranking.RankingService;
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
    private final ResponseService responseService;
    private final RankingService rankingService;

    @GetMapping("/ranking")
    public Result getRankingList() {
        return responseService.getListResult(rankingService.getRankingList());
    }
    @GetMapping("/myRank")
    public Result getMyRank(@RequestHeader("token") String token) {
        return responseService.getSingleResult(rankingService.getMyRank(token));
    }

}
