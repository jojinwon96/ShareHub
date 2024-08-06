package com.jinwon.server.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jinwon.server.provider.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider; // JWT 검증을 담당하는 JwtProvider 클래스 인스턴스

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      // HTTP 요청에서 JWT 토큰을 추출
      String token = parseBearerToken(request);

      // JWT 토큰이 없으면 다음 필터로 진행
      if (token == null) {
        filterChain.doFilter(request, response);
        return;
      }

      // JWT 토큰을 검증하고, 검증에 성공하면 이메일을 반환
      String email = jwtProvider.validate(token);
      if (email == null) {
        // JWT 토큰 검증에 실패한 경우, 필터 체인을 계속 진행
        filterChain.doFilter(request, response);
        return;
      }

      // 인증 정보를 생성
      AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null,
          AuthorityUtils.NO_AUTHORITIES);

      // 요청에 대한 추가 정보를 설정
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      // 새로운 SecurityContext를 생성하고 인증 정보를 설정
      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(authenticationToken);

      // 생성된 SecurityContext를 SecurityContextHolder에 설정
      SecurityContextHolder.setContext(securityContext);

    } catch (Exception e) {
      e.printStackTrace();
    }

    // 필터 체인을 계속 진행하여 다음 필터나 서블릿으로 요청을 전달
    filterChain.doFilter(request, response);
  }

  /**
   * HTTP 요청에서 Bearer 토큰을 추출합니다.
   * 
   * @param request HTTP 요청 객체
   * @return 추출된 JWT 토큰, 없으면 null
   */
  private String parseBearerToken(HttpServletRequest request) {
    // Authorization 헤더에서 Bearer 토큰 추출
    String authorization = request.getHeader("Authorization");

    // Authorization 헤더가 비어있거나 null인 경우
    if (!StringUtils.hasText(authorization)) {
      return null;
    }

    // 헤더가 Bearer로 시작하지 않는 경우
    if (!authorization.startsWith("Bearer ")) {
      return null;
    }

    // Bearer 문자열을 제거하고 토큰만 추출
    return authorization.substring(7);
  }
}
