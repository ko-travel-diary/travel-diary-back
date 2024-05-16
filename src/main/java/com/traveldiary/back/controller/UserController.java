package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.response.user.GetUserInfoResponseDto;
import com.traveldiary.back.dto.response.user.GetUserListResponseDto;
import com.traveldiary.back.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @GetMapping("/userlist")
    public ResponseEntity<? super GetUserListResponseDto> getUserList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetUserListResponseDto> response = userService.getUserList(userId);
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetUserInfoResponseDto> response = userService.getUserInfo(userId);
        return response;
    }
}
