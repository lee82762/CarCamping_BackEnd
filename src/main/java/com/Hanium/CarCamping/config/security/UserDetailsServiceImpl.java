package com.Hanium.CarCamping.config.security;

import com.Hanium.CarCamping.config.security.jwt.MemberNotFoundException;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
//    @Cacheable(value = CacheKey.MEMBER, key = "#email", unless = "#result==null")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        return new User(member.getEmail(), member.getPassword(), makeGrantedAuthority(member.getRole().getValue()));
    }

    private List<? extends GrantedAuthority> makeGrantedAuthority(String role) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + role));
        return list;
    }

}
