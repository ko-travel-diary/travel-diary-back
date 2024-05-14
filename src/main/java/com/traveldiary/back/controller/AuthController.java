package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.auth.SignInRequestDto;
import com.traveldiary.back.dto.request.auth.SignUpRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.auth.SignInResponseDto;
import com.traveldiary.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
        return response;
    }
}
