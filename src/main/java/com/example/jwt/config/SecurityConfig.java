package com.example.jwt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .addFilter(corsConfig.corsFilter())  //모든 요청이 해당 필터를 거치게 됨, @CrossOrigin(인증이 필요 없을때), 시큐리티 필터에 등록 (인증이 필요할때 addFilter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)     //세션 만드는 방식 안씀
            .and()
                .formLogin().disable()  //form 로그인 방식 안씀
                .httpBasic().disable()  //기본적인 Http 로그인 방식 안씀
                .authorizeRequests()

                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')or  hasRole('ROLE_MANAGER')")

                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")

                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")

                .anyRequest().permitAll();

        return http.build();

    }
}
