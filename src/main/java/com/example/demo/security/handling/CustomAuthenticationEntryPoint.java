//package com.example.demo.security.handling;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
//@Component
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//                         AuthenticationException e) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, String> errorDetails = new HashMap<>();
//        errorDetails.put("details", String.valueOf(e.getClass()));
//        errorDetails.put("message", "JWT has expired");
//        errorDetails.put("timestamp", DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss", Locale.ROOT)
//                .format(LocalDateTime.now()));
//        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//        httpServletResponse.setContentType("application/json");
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.getWriter().write(mapper.writeValueAsString(errorDetails));
//    }
//}