package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.traveldiary.back.dto.request.auth.EmailAuthRequestDto;
import com.traveldiary.back.dto.request.auth.IdCheckRequestDto;
import com.traveldiary.back.dto.request.auth.NickNameCheckRequestDto;
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
    public ResponseEntity<? super SignInResponseDto> signIn (
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idcheck (
        @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/nickname-check")
    public ResponseEntity<ResponseDto> nickNameCheck (
        @RequestBody @Valid NickNameCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.nickNameCheck(requestBody);
        return response;
    }

    @PostMapping("/email-auth")
    public ResponseEntity<ResponseDto> emailAuth (
        @RequestBody @Valid EmailAuthRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.eamilAuth(requestBody);
        return response;
    }

    @PostMapping("/email-auth-check")
    public ResponseEntity<ResponseDto> emailAuthCheck (
        @RequestBody @Valid EmailAuthCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.eamilAuthCheck(requestBody);
        return response;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp (
        @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
        return response;
    }
}
