package com.Hanium.CarCamping.config.security.jwt;

import com.Hanium.CarCamping.config.security.UserDetailsServiceImpl;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${spring.jwt.secret")
    private String SECRET_KEY;

    private final long EXPIRE_TIME = 100000 * 60 * 60;
    private final UserDetailsServiceImpl userDetailsService;
    private final MemberRepository memberRepository;

    //base64로 인코딩
    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createJwt(String email){
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("EMAIL", email);

        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + EXPIRE_TIME);


        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())     // 현재 시간??
                .setClaims(claimMap)                                            // payload = 내용 claim 단위로 정보를 넣을 수 있다.
                .setExpiration(expireTime)                                      //
                .signWith(SignatureAlgorithm.HS256, generateKey())
                //직렬화
                .compact();// jwt 직렬화

    }

    private byte[] generateKey() {
        try{
            return SECRET_KEY.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UserDefineException("키를 변환하는 데에 실패하였습니다.");
        }
    }

    public void saveToken(HttpServletResponse response, String token) {
        response.setHeader("TOKEN", token);
    }

    public boolean isUsable(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("token");
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.findEmailByJwt(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String findEmailByJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("EMAIL");
    }

    public Member findMemberByToken(String token){
        String email = findEmailByJwt(token);
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }


}
