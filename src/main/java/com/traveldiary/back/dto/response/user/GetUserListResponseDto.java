package com.traveldiary.back.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.UserListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserListResponseDto extends ResponseDto{
    private List<UserListItem> userListItems;

    private GetUserListResponseDto (List<UserEntity> qnaEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userListItems = UserListItem.getUserList(qnaEntities);
    }

    public static ResponseEntity<GetUserListResponseDto> success (List<UserEntity> userEntities) {
        GetUserListResponseDto responseBody = new GetUserListResponseDto(userEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}

