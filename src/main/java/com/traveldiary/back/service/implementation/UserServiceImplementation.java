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
import com.traveldiary.back.entity.EmailAuthNumberEntity;
import com.traveldiary.back.entity.QnaEntity;
import com.traveldiary.back.entity.RestaurantRecommendEntity;
import com.traveldiary.back.entity.ScheduleEntity;
import com.traveldiary.back.entity.TourAttractionsRecommendEntity;
import com.traveldiary.back.entity.TravelCommentEntity;
import com.traveldiary.back.entity.TravelFavoriteEntity;
import com.traveldiary.back.entity.TravelReviewEntity;
import com.traveldiary.back.entity.TravelReviewImageEntity;
import com.traveldiary.back.entity.TravelScheduleEntity;
import com.traveldiary.back.entity.TravelScheduleExpenditureEntity;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.repository.EmailAuthNumberRepository;
import com.traveldiary.back.repository.QnaRepository;
import com.traveldiary.back.repository.RestaurantRecommendRepository;
import com.traveldiary.back.repository.ScheduleRepository;
import com.traveldiary.back.repository.TourAttractionsRecommendRepository;
import com.traveldiary.back.repository.TravelCommentRepository;
import com.traveldiary.back.repository.TravelFavoriteRepository;
import com.traveldiary.back.repository.TravelReviewImageRepository;
import com.traveldiary.back.repository.TravelReviewRepository;
import com.traveldiary.back.repository.TravelScheduleExpenditureRepository;
import com.traveldiary.back.repository.TravelScheduleRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final EmailAuthNumberRepository emailAuthNumberRepository;
    private final TourAttractionsRecommendRepository tourAttractionsRecommendRepository;
    private final RestaurantRecommendRepository restaurantRecommendRepository;
    private final QnaRepository qnaRepository;

    private final TravelCommentRepository travelCommentRepository;

    private final TravelReviewRepository travelReviewRepository;
    private final TravelReviewImageRepository travelReviewImageRepository;
    private final TravelFavoriteRepository travelFavoriteRepository;
    
    private final TravelScheduleRepository travelScheduleRepository;
    private final ScheduleRepository scheduleRepository;
    private final TravelScheduleExpenditureRepository travelScheduleExpenditureRepository;

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

        EmailAuthNumberEntity emailAuthNumberEntity;

        List<TourAttractionsRecommendEntity> tourAttractionsRecommendEntities;
        List<RestaurantRecommendEntity> restaurantRecommendEntities;
        List<TravelCommentEntity> travelCommentEntities;
        List<QnaEntity> qnaEntities;

        List<TravelReviewEntity> travelReviewEntities;
        List<TravelReviewImageEntity> travelReviewImageEntities;
        List<TravelFavoriteEntity> travelFavoriteEntities;

        List<TravelScheduleEntity> travelScheduleEntities;
        List<TravelScheduleExpenditureEntity> travelScheduleExpenditureEntities;
        List<ScheduleEntity> scheduleEntities;

        Integer reviewNumber;
        Integer travelScheduleNumber;

        String email;

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

            // 관광지 좋아요 삭제
            tourAttractionsRecommendEntities = tourAttractionsRecommendRepository.findByUserId(userId);
            tourAttractionsRecommendRepository.deleteAll(tourAttractionsRecommendEntities);

            // 식당 좋아요 삭제
            restaurantRecommendEntities = restaurantRecommendRepository.findByUserId(userId);
            restaurantRecommendRepository.deleteAll(restaurantRecommendEntities);
            
            // QnA 삭제
            qnaEntities = qnaRepository.findByQnaWriterId(userId);
            qnaRepository.deleteAll(qnaEntities);
            
            // Review 및 관련 테이블 삭제
            travelFavoriteEntities = travelFavoriteRepository.findByUserId(userId);
            travelFavoriteRepository.deleteAll(travelFavoriteEntities);

            travelReviewEntities = travelReviewRepository.findByReviewWriterId(userId);
            for (TravelReviewEntity travelReviewEntity : travelReviewEntities) {
                // Review Comment 삭제
                reviewNumber = travelReviewEntity.getReviewNumber();
                travelCommentEntities = travelCommentRepository.findByCommentReviewNumber(reviewNumber);
                travelCommentRepository.deleteAll(travelCommentEntities);
                // Review Image 삭제
                travelReviewImageEntities = travelReviewImageRepository.findByTravelReviewNumber(reviewNumber);
                travelReviewImageRepository.deleteAll(travelReviewImageEntities);
            }
            travelReviewRepository.deleteAll(travelReviewEntities);

            // Schedule 삭제
            travelScheduleEntities = travelScheduleRepository.findByTravelScheduleWriterId(userId);
            for (TravelScheduleEntity travelScheduleEntity: travelScheduleEntities) {
                travelScheduleNumber = travelScheduleEntity.getTravelScheduleNumber();

                travelScheduleExpenditureEntities = travelScheduleExpenditureRepository.findByTravelScheduleNumber(travelScheduleNumber);
                travelScheduleExpenditureRepository.deleteAll(travelScheduleExpenditureEntities);

                scheduleEntities = scheduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
                scheduleRepository.deleteAll(scheduleEntities);
            }

            userRepository.delete(userEntity);

            email = userEntity.getUserEmail();
            emailAuthNumberEntity = emailAuthNumberRepository.findByEmail(email);
            emailAuthNumberRepository.delete(emailAuthNumberEntity);

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

        EmailAuthNumberEntity emailAuthNumberEntity;

        List<TourAttractionsRecommendEntity> tourAttractionsRecommendEntities;
        List<RestaurantRecommendEntity> restaurantRecommendEntities;
        List<TravelCommentEntity> travelCommentEntities;
        List<QnaEntity> qnaEntities;

        List<TravelReviewEntity> travelReviewEntities;
        List<TravelReviewImageEntity> travelReviewImageEntities;
        List<TravelFavoriteEntity> travelFavoriteEntities;

        List<TravelScheduleEntity> travelScheduleEntities;
        List<TravelScheduleExpenditureEntity> travelScheduleExpenditureEntities;
        List<ScheduleEntity> scheduleEntities;

        Integer reviewNumber;
        Integer travelScheduleNumber;

        String email;
        String deleteUserId;

        try {

            adminEntity = userRepository.findByUserId(userId);
            String userRole = adminEntity.getUserRole();
            if(userRole == "ROLE_USER") return ResponseDto.authenticationFailed();

            deleteUserId = dto.getDeleteToUserId();
            userEntity = userRepository.findByUserId(deleteUserId);
            if(userEntity == null) return ResponseDto.noExistUser();

            // 관광지 좋아요 삭제
            tourAttractionsRecommendEntities = tourAttractionsRecommendRepository.findByUserId(deleteUserId);
            tourAttractionsRecommendRepository.deleteAll(tourAttractionsRecommendEntities);

            // 식당 좋아요 삭제
            restaurantRecommendEntities = restaurantRecommendRepository.findByUserId(deleteUserId);
            restaurantRecommendRepository.deleteAll(restaurantRecommendEntities);

            // QnA 삭제
            qnaEntities = qnaRepository.findByQnaWriterId(deleteUserId);
            qnaRepository.deleteAll(qnaEntities);
            
            // Review 삭제
            travelFavoriteEntities = travelFavoriteRepository.findByUserId(deleteUserId);
            travelFavoriteRepository.deleteAll(travelFavoriteEntities);

            // Review 삭제
            travelReviewEntities = travelReviewRepository.findByReviewWriterId(userId);
            for (TravelReviewEntity travelReviewEntity : travelReviewEntities) {
                // Review Comment 삭제
                reviewNumber = travelReviewEntity.getReviewNumber();
                travelCommentEntities = travelCommentRepository.findByCommentReviewNumber(reviewNumber);
                travelCommentRepository.deleteAll(travelCommentEntities);
                // Review Image 삭제
                travelReviewImageEntities = travelReviewImageRepository.findByTravelReviewNumber(reviewNumber);
                travelReviewImageRepository.deleteAll(travelReviewImageEntities);
            }
            travelReviewRepository.deleteAll(travelReviewEntities);

            // Schedule 삭제
            travelScheduleEntities = travelScheduleRepository.findByTravelScheduleWriterId(deleteUserId);
            for (TravelScheduleEntity travelScheduleEntity: travelScheduleEntities) {
                travelScheduleNumber = travelScheduleEntity.getTravelScheduleNumber();

                travelScheduleExpenditureEntities = travelScheduleExpenditureRepository.findByTravelScheduleNumber(travelScheduleNumber);
                travelScheduleExpenditureRepository.deleteAll(travelScheduleExpenditureEntities);

                scheduleEntities = scheduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
                scheduleRepository.deleteAll(scheduleEntities);
            }

            userRepository.delete(userEntity);

            email = userEntity.getUserEmail();
            emailAuthNumberEntity = emailAuthNumberRepository.findByEmail(email);
            emailAuthNumberRepository.delete(emailAuthNumberEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}
