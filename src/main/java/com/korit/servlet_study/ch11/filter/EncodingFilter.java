package com.korit.servlet_study.ch11.filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter("/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 요청 데이터 인코딩
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 응답 데이터 이코딩
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 응답 데이터 타입
        servletResponse.setContentType("application/json");
        // 다음필터 또는 서블릿
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
