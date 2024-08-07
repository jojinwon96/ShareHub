package com.jinwon.server.service;

import org.springframework.http.ResponseEntity;

import com.jinwon.server.dto.request.auth.SignUpRequestDto;
import com.jinwon.server.dto.response.auth.SignUpResponseDto;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    
} 