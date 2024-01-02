//package com.example.demo.security;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.io.IOException;
//import java.util.Objects;
//
//@AllArgsConstructor
//public class JwtTokenFilter extends GenericFilter {
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
//
//        if (Objects.nonNull(token) && jwtTokenProvider.validateToken(token)) {
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//
//            if (authentication != null) {
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//    }
