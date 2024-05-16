package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.user.GetUserInfoResponseDto;
import com.traveldiary.back.dto.response.user.GetUserListResponseDto;

public interface UserService {
    ResponseEntity<? super GetUserListResponseDto> getUserList (String userId);
    ResponseEntity<? super GetUserInfoResponseDto> getUserInfo (String userId);
}
