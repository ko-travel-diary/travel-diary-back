package com.traveldiary.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.user.DeleteAdminUserRequestDto;
import com.traveldiary.back.dto.request.user.DeleteUserRequestDto;
import com.traveldiary.back.dto.request.user.PostUserNickNameRequestDto;
import com.traveldiary.back.dto.request.user.PatchUserInfoRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.user.GetSearchUserListResponseDto;
import com.traveldiary.back.dto.response.user.GetUserInfoResponseDto;
import com.traveldiary.back.dto.response.user.GetUserListResponseDto;
import com.traveldiary.back.dto.response.user.PostUserNickNameResponseDto;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.repository.EmailAuthNumberRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final EmailAuthNumberRepository emailAuthNumberRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super GetUserListResponseDto> getUserList(String userId) {

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);

            String userRole = userEntity.getUserRole();
            if(userRole == "ROLE_USER") return ResponseDto.authenticationFailed();

            List<UserEntity> userEntities = userRepository.findByUserRoleOrderByJoinDate("ROLE_USER");

            return GetUserListResponseDto.success(userEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    @Override
    public ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList(String userId, String searchWord) {

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);

            String userRole = userEntity.getUserRole();
            if(userRole == "ROLE_USER") return ResponseDto.authenticationFailed();

            List<UserEntity> userEntities = userRepository.findByUserRoleAndUserIdContainingOrderByJoinDate("ROLE_USER", searchWord);

            return GetSearchUserListResponseDto.success(userEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    @Override
    public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId) {

        UserEntity userEntity;

        try {

            userEntity = userRepository.findByUserId(userId);

            boolean isMatchedUserId = userRepository.existsByUserId(userId);
            if(!isMatchedUserId) return ResponseDto.authenticationFailed();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserInfoResponseDto.success(userEntity);
    }

    
    @Override
    public ResponseEntity<? super PostUserNickNameResponseDto> getUserNickName(PostUserNickNameRequestDto dto) {

        String nickName;

        try {

            String userId = dto.getWriterId();

            UserEntity userEntity = userRepository.findByUserId(userId);

            String userRole = userEntity.getUserRole();
            if(userRole == "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            nickName = userEntity.getNickName();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostUserNickNameResponseDto.success(nickName);
    }


    @Override
    public ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId) {

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);

            String userRole = userEntity.getUserRole();
            if(userRole == "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            boolean isMatchedUserId = userRepository.existsByUserId(userId);
            if(!isMatchedUserId) return ResponseDto.authenticationFailed();

            String nickName = dto.getNickName();
            boolean existNickName = userRepository.existsByNickName(nickName);
            if(existNickName) return ResponseDto.duplicatedNickName();

            userEntity.update(dto);

            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteUser(DeleteUserRequestDto dto, String userId) {

        UserEntity userEntity;

        try {

            userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) ResponseDto.noExistUser();

            boolean isMatchedUserId = userRepository.existsByUserId(userId);
            if(!isMatchedUserId) return ResponseDto.authenticationFailed();

            String userPassword = dto.getUserPassword();
            String userRole = userEntity.getUserRole();
            if(userRole == "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            String encodedPassword = userEntity.getUserPassword();
            boolean isMatchedUserPassword = passwordEncoder.matches(userPassword, encodedPassword);
            if(!isMatchedUserPassword) return ResponseDto.authenticationFailed();

            String userEmail = userEntity.getUserEmail();

            userRepository.delete(userEntity);
            emailAuthNumberRepository.deleteByEmail(userEmail);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAdminUser(DeleteAdminUserRequestDto dto, String userId) {


        UserEntity adminEntity;
        UserEntity userEntity;

        try {
            adminEntity = userRepository.findByUserId(userId);
            String userRole = adminEntity.getUserRole();
            if(userRole == "ROLE_USER") return ResponseDto.authenticationFailed();

            String deleteAdminUserId = dto.getDeleteToUserId();
            userEntity = userRepository.findByUserId(deleteAdminUserId);
            if(userEntity == null) return ResponseDto.noExistUser();

            String userEmail = userEntity.getUserEmail();

            userRepository.delete(userEntity);
            emailAuthNumberRepository.deleteByEmail(userEmail);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}
