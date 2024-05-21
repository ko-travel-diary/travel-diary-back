package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.user.DeleteAdminUserRequestDto;
import com.traveldiary.back.dto.request.user.DeleteUserRequestDto;
import com.traveldiary.back.dto.request.user.PatchUserInfoRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.user.GetUserInfoResponseDto;
import com.traveldiary.back.dto.response.user.GetUserListResponseDto;
import com.traveldiary.back.service.UserService;

import jakarta.validation.Valid;
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

    @PatchMapping("/edit")
    public ResponseEntity<ResponseDto> patchUserInfo (
        @RequestBody @Valid PatchUserInfoRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = userService.patchUserInfo(requestBody, userId);
        return response;
    }

    @PutMapping("/cancle-account")
    public ResponseEntity<ResponseDto> deleteUser (
        @RequestBody @Valid DeleteUserRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = userService.deleteUser(requestBody, userId);
        return response;
    }

    @PutMapping("/userlist/cancle-account")
    public ResponseEntity<ResponseDto> deleteAdminUser (
        @RequestBody @Valid DeleteAdminUserRequestDto requestBody,
        @AuthenticationPrincipal String deleteToUserId
    ) {
        ResponseEntity<ResponseDto> response = userService.deleteAdminUser(requestBody, deleteToUserId);
        return response;
    }
}
