package com.jinwon.server.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jinwon.server.filter.JwtAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configurable
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter; // JWT 인증 필터

  @Bean
  protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors(cors -> cors
            .configurationSource(corsConfigrationSource()) // CORS 설정 적용
        )
        .csrf(CsrfConfigurer::disable) // CSRF 보호 비활성화
        .httpBasic(HttpBasicConfigurer::disable) // HTTP 기본 인증 비활성화
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안 함 (무상태 세션)
        )
        .authorizeHttpRequests(request -> request
            .requestMatchers("/", "/api/auth/**", "/api/search", "/file/**").permitAll() // 특정 URL 패턴에 대한 접근 허용
            .requestMatchers(HttpMethod.GET, "/api/board", "/api/user").permitAll() // GET 요청에 대한 접근 허용
            .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
        )
        .exceptionHandling(exceptionHandling -> exceptionHandling
            .authenticationEntryPoint(new FailedAuthenticationEntryPoint()) // 인증 실패 처리기 설정
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT 필터를 기본 인증 필터 전에 추가

    return httpSecurity.build(); // 보안 필터 체인 반환
  }

  @Bean
  protected CorsConfigurationSource corsConfigrationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("*"); // 모든 출처 허용
    configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
    configuration.addExposedHeader("*"); // 모든 헤더를 응답에서 노출

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 CORS 설정 등록
    return source;
  }
}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {

    // 인증 실패 시 JSON 형식의 응답을 작성
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401 Unauthorized 상태 코드 설정
    response.getWriter().write("{ \"code\": \"AF\", \"message\": \"Authorization Failed.\" }");
  }
}
