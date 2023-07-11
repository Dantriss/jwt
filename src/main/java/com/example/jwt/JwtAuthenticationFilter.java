package com.example.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//스프링시큐리티에 UsernamePasswordAuthenticationFilter 가 있어서 로그인 요청(/login)을 해서
// 아이디, 패스워드를 전송하면(post) UsernamePasswordAuthenticationFilter 가 동작
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager1;


    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("로그인 시도중");
        // 1. username, password 받아서
        // 2. 정상인지 로그인 시도를 해봄 authenticationManager로 로그인을 시도를 하면
        // PrincipalDetailsService가 호출되어 loadUserByUsername 함수가 실행
        // 3. PrincipalDetails를 세션에 담고 ->  jwt토큰으로 인증해서 로그인하는건데 세션에 저장을 왜함 ? -> 안하면 권한관리를 할 수가 없음
        // 4. jwt 토큰을 만들어서 응답을 해주면 됨
        return super.attemptAuthentication(request, response);
    }
}
