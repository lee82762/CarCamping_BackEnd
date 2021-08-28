package com.Hanium.CarCamping.service.Member;

import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.domain.entity.member.Role;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.service.member.MemberCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Arrays;
import java.util.Set;
@SpringBootTest
class MemberCreateServiceTest {

    /*
    @Autowired
    MemberCreateService memberCreateService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void 사용자_등록() throws Exception {

        Member member= build();
        System.out.println(member.getEmail());
        System.out.println(member.getPassword());



    }
    public static Member build(){
        return Member.builder()
                .email("test1@naver.com1")
                .password("1234")
                .nickname("테스트")
                .point(1)
                .role(Role.USER)
                .build();
    }

    @Test
    public void 레디스_테스트() throws Exception {
        String key = "value";

        ZSetOperations<String, Integer> stringStringZSetOperations = redisTemplate.opsForZSet();

        stringStringZSetOperations.add(key, 3, 1);
        stringStringZSetOperations.add(key, 5, 5);
        stringStringZSetOperations.add(key, 1, 10);
        stringStringZSetOperations.add(key, 2, 15);
        stringStringZSetOperations.add(key, 6, 20);

        Set<Integer> range = stringStringZSetOperations.range(key, 0, 5);

        System.out.println("range = " + Arrays.toString(range.toArray()));

        Long size = stringStringZSetOperations.size(key);

        System.out.println("size = " + size);

        Set<Integer> scoreRange = stringStringZSetOperations.rangeByScore(key, 0, 13);

        System.out.println("scoreRange = " + Arrays.toString(scoreRange.toArray()));

    }

     */

}