package com.traveldiary.back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserInfoResponseDto extends ResponseDto{
    private String profileImage;
    private String userId;
    private String userEmail;

    private GetUserInfoResponseDto (UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.profileImage = userEntity.getProfileImage();
        this.userId = userEntity.getUserId();
        this.userEmail = userEntity.getUserEmail();
    }

    public static ResponseEntity<GetUserInfoResponseDto> success (UserEntity userEntity) {
        GetUserInfoResponseDto responseBody = new GetUserInfoResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
