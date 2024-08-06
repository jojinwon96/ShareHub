package com.jinwon.server.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

  @Value("${secret-key}")
  private String secretKey;// JWT 서명을 위한 비밀 키

  /**
   * JWT 토큰을 생성
   * 
   * @param email 사용자 이메일, JWT의 페이로드에 포함
   * @return 생성된 JWT 토큰 문자열
   */
  public String create(String email) {

    // JWT 만료일을 현재 시간으로부터 1시간 후로 설정
    Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    // JWT 생성
    String jwt = Jwts.builder()
        .signWith(key, SignatureAlgorithm.HS256) // 서명 알고리즘 및 비밀 키 설정
        .setSubject(email) // 페이로드에 이메일 포함
        .setIssuedAt(new Date()) // JWT 생성 일자 설정
        .setExpiration(expiredDate) // JWT 만료 일자 설정
        .compact(); // JWT 토큰을 문자열로 압축

    return jwt;
  }

  /**
   * JWT 토큰을 검증
   * 
   * @param jwt 검증할 JWT 토큰
   * @return JWT의 페이로드에서 추출한 사용자 이메일, 검증 실패 시 null
   */
  public String validate(String jwt) {
    Claims claims = null;
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    try {
      // JWT 토큰을 파싱하고 서명 검증
      claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(jwt) // JWT 토큰 파싱
          .getBody(); // 페이로드 추출
    } catch (Exception e) {
      // JWT 검증 중 예외 발생 시 스택 트레이스 출력
      e.printStackTrace();
      return null;
    }

    // 페이로드에서 사용자 이메일 반환
    return claims.getSubject();
  }
}
