package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class UserListItem {

    private String userId;
    private String userEmail;
    private String joinDate;
    private String nickName;

    private UserListItem(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.userEmail = userEntity.getUserEmail();
        this.joinDate = userEntity.getJoinDate();
        this.nickName = userEntity.getNickName();
    }

    public static List<UserListItem> getUserList(List<UserEntity> entities) {

        List<UserListItem> userList = new ArrayList<>();

        for (UserEntity userEntity : entities) {
            UserListItem userListItem = new UserListItem(userEntity);
            userList.add(userListItem);
        }

        return userList;

    }

}
