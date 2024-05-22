package com.traveldiary.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.traveldiary.back.dto.request.auth.SignUpRequestDto;
import com.traveldiary.back.dto.request.user.PatchUserInfoRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String userId;
    private String userPassword;
    private String userEmail;
    private String nickName;
    private String profileImage;
    private String joinPath;
    private String userRole;
    private String joinDate;

    public UserEntity(SignUpRequestDto dto) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(now);

        this.userId = dto.getUserId();
        this.userPassword = dto.getUserPassword();
        this.userEmail = dto.getUserEmail();
        this.nickName = dto.getNickName();
        this.profileImage = dto.getProfileImage();
        this.userRole = "ROLE_USER";
        this.joinPath = "HOME";
        this.joinDate = dateTime;
    }

    public void update (PatchUserInfoRequestDto dto){
        this.nickName = dto.getNickName();
        this.profileImage = dto.getProfileImage();
    }
}
