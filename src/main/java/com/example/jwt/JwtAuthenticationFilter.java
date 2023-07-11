package com.example.jwt;

import com.example.auth.PrincipalDetails;
import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//스프링시큐리티에 UsernamePasswordAuthenticationFilter 가 있어서 로그인 요청(/login)을 해서
// 아이디, 패스워드를 전송하면(post) UsernamePasswordAuthenticationFilter 가 동작
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("로그인 시도중");
            /*BufferedReader br = request.getReader();
            String input = null;
            while ((input=br.readLine()) != null){
                System.out.println("attemptAuthentication input : "+input);
            }*/

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            System.out.println("user test : "+ user);

            // PrincipalDetailsService 의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication 리턴
            // db에 있는 username과 password가 일치한다.

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            //로그인이 되었다는 뜻
            System.out.println("principalDetails test(login check) : "+principalDetails.getUser());

            // authentication 객체가 리턴되면서 session 영역에 저장됨
            // 권한처리를 위해서 세션에 저장하는
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 1. username, password 받아서
        // 2. 정상인지 로그인 시도를 해봄 authenticationManager로 로그인을 시도를 하면
        // PrincipalDetailsService가 호출되어 loadUserByUsername 함수가 실행
        // 3. PrincipalDetails를 세션에 담고 ->  jwt토큰으로 인증해서 로그인하는건데 세션에 저장을 왜함 ? -> 안하면 권한관리를 할 수가 없음
        // 4. jwt 토큰을 만들어서 응답을 해주면 됨
        return null;
    }
}
