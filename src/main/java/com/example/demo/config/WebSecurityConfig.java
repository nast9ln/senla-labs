//package com.example.demo.config;
//
//import com.example.demo.security.handling.CustomAccessDeniedHandler;
//import com.example.demo.security.handling.CustomAuthenticationEntryPoint;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private final CustomAccessDeniedHandler customAccessDeniedHandler;
//    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
//
//    private static final String ADMIN_ENDPOINT = "/admin/**";
//    private static final String LOGIN_ENDPOINT = "/auth/**";
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(customAuthenticationEntryPoint)
//                .accessDeniedHandler(customAccessDeniedHandler)
//                .and()
//                .authorizeRequests()
//                .antMatchers(LOGIN_ENDPOINT).permitAll()
//                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
//                .anyRequest().authenticated();
//    }
//}
//
