package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.auth.SignInRequestDto;
import com.traveldiary.back.dto.request.auth.SignUpRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.auth.SignInResponseDto;

public interface AuthService {
    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto);
    ResponseEntity<ResponseDto>signUp(SignUpRequestDto dto);
}
