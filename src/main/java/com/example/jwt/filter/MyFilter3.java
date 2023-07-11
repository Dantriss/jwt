package com.example.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("filter 3");

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String headerAuth = request.getHeader("Authorization");

        // id.pw 정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어주고 그걸 응답을 해줌
        // 요청할 때 마다 header에 Authorization에 value 값으로 토큰을 가지고 오면됨.
        // 토큰이 넘어 올 때 이 토큰이 내가 만든 토큰인지만 맞는지 검증하면 됨 (RSA, HS356)

        if (request.getMethod().equals("POST")){
            System.out.println("headerAuth : "+headerAuth);
            if (headerAuth.equals("token")){
                filterChain.doFilter(servletRequest, servletResponse);

            }else {
                PrintWriter outPrintWriter = response.getWriter();
                outPrintWriter.println("인증안됨");
            }


        }


    }
}
