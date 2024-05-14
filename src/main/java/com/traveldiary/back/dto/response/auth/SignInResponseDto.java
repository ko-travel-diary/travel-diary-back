package com.traveldiary.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

// 로그인 Response Body Dto
@Getter
public class SignInResponseDto extends ResponseDto{

    private String accessToken;
    private int expires;

    private SignInResponseDto(String accessToken) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.accessToken = accessToken;
        this.expires = 12 * 60 * 60 * 100;
    }

    public static ResponseEntity<SignInResponseDto> success (String accessToken) {
        SignInResponseDto responseBody = new SignInResponseDto(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
