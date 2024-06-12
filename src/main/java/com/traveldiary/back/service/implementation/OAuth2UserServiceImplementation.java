package com.traveldiary.back.service.implementation;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.traveldiary.back.common.object.CustomOAuth2User;
import com.traveldiary.back.entity.EmailAuthNumberEntity;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.repository.EmailAuthNumberRepository;
import com.traveldiary.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplementation extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final EmailAuthNumberRepository emailAuthNumberRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String oauthClientName = userRequest.getClientRegistration().getClientName().toUpperCase();

        String id = getId(oAuth2User, oauthClientName);
        String oauthNickName = getNickName(oAuth2User, oauthClientName);
        String profileImage = getProfileImage(oAuth2User, oauthClientName);

        int userIdEndIndex = id.length() > 10 ? 10 : id.length();
        String userId = oauthClientName + "_" + id.substring(0, userIdEndIndex);
        int nickNameEndIndex = oauthNickName.length() > 10 ? 10 : oauthNickName.length();
        String nickName = oauthClientName + "_" + oauthNickName.substring(0, nickNameEndIndex);

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(now);

        String joinDate = dateTime;

        boolean isExistsUser = userRepository.existsByUserId(userId);
        if (!isExistsUser) {
            String userEmail = id + "@" + oauthClientName.toLowerCase() + ".com";
            String password = passwordEncoder.encode(id);

            EmailAuthNumberEntity emailAuthNumberEntity = new EmailAuthNumberEntity(userEmail, "0000");
            emailAuthNumberRepository.save(emailAuthNumberEntity);

            UserEntity userEntity = new UserEntity(userId, password, userEmail, nickName, profileImage, oauthClientName, "ROLE_USER", joinDate);

            userRepository.save(userEntity);

        }

        return new CustomOAuth2User(userId, oAuth2User.getAttributes());

    }

    private String getId(OAuth2User oAuth2User, String oauthClientName) {

        String id = "";

        if (oauthClientName.equals("KAKAO")) {
            Long longId = (Long) oAuth2User.getAttributes().get("id");
            id = longId.toString();
        }
        if (oauthClientName.equals("NAVER")) {
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
            id = response.get("id");
        }
        if (oauthClientName.equals("GOOGLE")) {
            String StringId = (String) oAuth2User.getAttributes().get("sub");
            id = StringId;
        }

        return id;

    }

    private String getNickName(OAuth2User oAuth2User, String oauthClientName) {

        String nickName = "";

        if (oauthClientName.equals("KAKAO")) {
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("properties");
            nickName = response.get("nickname");
        }
        if (oauthClientName.equals("NAVER")) {
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
            nickName = response.get("nickname");
        }
        if (oauthClientName.equals("GOOGLE")) {
            String StringNickName = (String) oAuth2User.getAttributes().get("name");
            nickName = StringNickName;
        }

        return nickName;

    }

    private String getProfileImage(OAuth2User oAuth2User, String oauthClientName) {

        String profileImage = "";

        if (oauthClientName.equals("KAKAO")) {
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("properties");
            profileImage = response.get("profile_image");
        }
        if (oauthClientName.equals("NAVER")) {
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
            profileImage = response.get("profile_image");
        }
        if (oauthClientName.equals("GOOGLE")) {
            String StringImage = (String) oAuth2User.getAttributes().get("picture");
            profileImage = StringImage;
        }

        return profileImage;
        
    }

}