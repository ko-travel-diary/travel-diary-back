// package com.traveldiary.back.service.implementation;

// import java.util.Map;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
// import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
// import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.stereotype.Service;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.traveldiary.back.common.object.CustomOAuth2User;
// import com.traveldiary.back.entity.EmailAuthNumberEntity;
// import com.traveldiary.back.entity.UserEntity;
// import com.traveldiary.back.repository.EmailAuthNumberRepository;
// import com.traveldiary.back.repository.UserRepository;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class OAuth2UserServiceImplementation extends DefaultOAuth2UserService {

//     private final UserRepository userRepository;
//     private final EmailAuthNumberRepository emailAuthNumberRepository;
//     private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//     @Override
//     public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

//         OAuth2User oAuth2User = super.loadUser(userRequest);
//         String oauthClientName = userRequest.getClientRegistration().getClientName().toUpperCase();

//         try {
//             System.out.println(oauthClientName);
//             System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
//         } catch (Exception exception) {
//             exception.printStackTrace();
//         }

//         String id = getId(oAuth2User, oauthClientName);
//         // KAKAO_3458620284
//         // NAVER_TenZAyBCRV
//         String userId = oauthClientName + "_" + id.substring(0, 10);

//         boolean isExistsUser = userRepository.existsByUserId(userId);
//         if (!isExistsUser) {
//             // 3458620284@kakao.com
//             // TenZAyBCRVRgzrgUSOWIz1BXptn2OyKWO2EwqkEv5HQ@naver.com
//             String userEmail = id + "@" + oauthClientName.toLowerCase() + ".com";
//             String password = passwordEncoder.encode(id);

//             EmailAuthNumberEntity emailAuthNumberEntity = new EmailAuthNumberEntity(userEmail, "0000");
//             emailAuthNumberRepository.save(emailAuthNumberEntity);

//             UserEntity userEntity = new UserEntity(userId, password, userEmail, nickName, profileImage, "ROLE_USER",
//                     oauthClientName);

//             userRepository.save(userEntity);
//         }

//         return new CustomOAuth2User(userId, oAuth2User.getAttributes());
//     }

//     private String getId(OAuth2User oAuth2User, String oauthClientName) {

//         String id = null;
//         String nickName = null;
//         String profileImage = null;

//         if (oauthClientName.equals("KAKAO")) {
//             Long longId = (Long) oAuth2User.getAttributes().get("id");
//             id = longId.toString();
//         }
//         if (oauthClientName.equals("NAVER")) {
//             Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
//             id = response.get("id");
//         }
//         if (oauthClientName.equals("GOOGLE")) {
//             Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("name");
//             id = response.get("id");
//         }
//         return id;
//     }

// }