package com.traveldiary.back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class PostUserNickNameResponseDto extends ResponseDto{
    private String nickName;

    private PostUserNickNameResponseDto (String nickName) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.nickName = nickName;
    }

    public static ResponseEntity<PostUserNickNameResponseDto> success (String nickName){
        PostUserNickNameResponseDto responseBody = new PostUserNickNameResponseDto(nickName);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
