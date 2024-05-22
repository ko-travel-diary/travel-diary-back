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
public class GetSearchUserListResponseDto extends ResponseDto{
    private List<UserListItem> searchUserListItem;

    private GetSearchUserListResponseDto(List<UserEntity> userEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchUserListItem = UserListItem.getUserList(userEntities);
    }

    public static ResponseEntity<GetSearchUserListResponseDto> success(List<UserEntity> userEntities) {
        GetSearchUserListResponseDto responseBody = new GetSearchUserListResponseDto(userEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
