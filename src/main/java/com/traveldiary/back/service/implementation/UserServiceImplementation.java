package com.traveldiary.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.traveldiary.back.common.object.UserListItem;
import com.traveldiary.back.dto.request.user.DeleteAdminUserRequestDto;
import com.traveldiary.back.dto.request.user.DeleteUserRequestDto;
import com.traveldiary.back.dto.request.user.PatchUserInfoRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.user.GetUserInfoResponseDto;
import com.traveldiary.back.dto.response.user.GetUserListResponseDto;
import com.traveldiary.back.entity.EmailAuthNumberEntity;
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

        List<UserEntity> userEntities;

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);

            String userRole = userEntity.getUserRole();
            if(userRole == "ROLE_USER") return ResponseDto.authenticationFailed();

            userEntities = userRepository.findByUserRole("ROLE_USER");

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserListResponseDto.success(userEntities);
    }

    @Override
    public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId) {

        UserEntity userEntity;

        try {

            userEntity = userRepository.findByUserId(userId);

            String userRole = userEntity.getUserRole();
            if(userRole == "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            boolean isMatchedUserId = userRepository.existsByUserId(userId);
            if(!isMatchedUserId) return ResponseDto.authenticationFailed();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserInfoResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId) {

        UserEntity userEntity;

        try {

            userEntity = userRepository.findByUserId(userId);

            String userRole = userEntity.getUserRole();
            if(userRole == "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            boolean isMatchedUserId = userRepository.existsByUserId(userId);
            if(!isMatchedUserId) return ResponseDto.authenticationFailed();

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
