package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.user.DeleteAdminUserRequestDto;
import com.traveldiary.back.dto.request.user.DeleteUserRequestDto;
import com.traveldiary.back.dto.request.user.PatchUserInfoRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.user.GetUserInfoResponseDto;
import com.traveldiary.back.dto.response.user.GetUserListResponseDto;

public interface UserService {
    ResponseEntity<? super GetUserListResponseDto> getUserList (String userId);
    ResponseEntity<? super GetUserInfoResponseDto> getUserInfo (String userId);
    ResponseEntity<ResponseDto> patchUserInfo (PatchUserInfoRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteUser (DeleteUserRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteAdminUser (DeleteAdminUserRequestDto dto, String userId);
}
