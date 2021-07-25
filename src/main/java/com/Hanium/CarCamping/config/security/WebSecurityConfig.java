package com.Hanium.CarCamping.config.security;


import com.Hanium.CarCamping.config.security.filter.AccessDeniedHandlerCustom;
import com.Hanium.CarCamping.config.security.filter.AuthenticationEntryPointCustom;
import com.Hanium.CarCamping.config.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandlerCustom accessDeniedHandlerCustom;
    private final AuthenticationEntryPointCustom authenticationEntryPointCustom;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsServiceImpl userDetailsService;

    private static final String[] AUTH_ARR = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "favicon.ico"
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .httpBasic().disable()
                .csrf().ignoringAntMatchers("/h2-console/**").disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()

                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers("/damoim/chat/**").hasRole("USER")
                .anyRequest().permitAll()//.hasRole("USER")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandlerCustom)
                .and()
                 //인증실패시
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPointCustom)
                .and()
                //jwt 인증 필터
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                 //로그아웃
       /*         .logout().logoutUrl("/logout").permitAll()
                .deleteCookies("JSESSIONID")
                .deleteCookies(HEADER_NAME)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(AUTH_ARR);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}