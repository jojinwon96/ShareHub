package com.jinwon.server.service;

import org.springframework.http.ResponseEntity;

import com.jinwon.server.dto.request.auth.SignInRequestDto;
import com.jinwon.server.dto.request.auth.SignUpRequestDto;
import com.jinwon.server.dto.response.auth.SignUpResponseDto;
import com.jinwon.server.dto.response.auth.SignInResponseDto;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    
} 