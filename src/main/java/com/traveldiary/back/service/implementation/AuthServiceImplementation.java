package com.traveldiary.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.auth.SignInRequestDto;
import com.traveldiary.back.dto.request.auth.SignUpRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.auth.SignInResponseDto;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.provider.JwtProvider;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService{

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String accessToken = null;

        try{

            String userId = dto.getUserId();
            String userPassword = dto.getUserPassword();

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.signInFailed();

            String encodedPassword = userEntity.getUserPassword();
            boolean isMatched = passwordEncoder.matches(userPassword, encodedPassword);
            if(!isMatched) return ResponseDto.signInFailed();

            accessToken = jwtProvider.create(userId);
            if(accessToken == null) return ResponseDto.TokenCreationFailed();

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(accessToken);
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        try{

            String userId = dto.getUserId();
            String userPassword = dto.getUserPassword();
            String userEmail = dto.getUserEmail();
            String authNumber = dto.getAuthNumber();

            boolean existedUserId = userRepository.existsByUserId(userId);
            if(existedUserId) return ResponseDto.duplicatedId();

            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            if(existedEmail) return ResponseDto.duplicatedEmail();

            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, authNumber);
            if(!isMatched) return ResponseDto.authenticationFailed();

            String encodedPassword = passwordEncoder.encode(userPassword);
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
    
}
