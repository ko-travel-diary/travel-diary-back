package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.user.DeleteAdminUserRequestDto;
import com.traveldiary.back.dto.request.user.DeleteUserRequestDto;
import com.traveldiary.back.dto.request.user.PostUserNickNameRequestDto;
import com.traveldiary.back.dto.request.user.PatchUserInfoRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.user.GetSearchUserListResponseDto;
import com.traveldiary.back.dto.response.user.GetUserInfoResponseDto;
import com.traveldiary.back.dto.response.user.GetUserListResponseDto;
import com.traveldiary.back.dto.response.user.PostUserNickNameResponseDto;

public interface UserService {

    ResponseEntity<? super PostUserNickNameResponseDto> postUserNickName(PostUserNickNameRequestDto dto);

    ResponseEntity<? super GetUserListResponseDto> getUserList(String userId);
    ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId);
    ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList(String userId, String searchWord);

    ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId);
    
    ResponseEntity<ResponseDto> deleteUser(DeleteUserRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteAdminUser(DeleteAdminUserRequestDto dto, String userId);

}
