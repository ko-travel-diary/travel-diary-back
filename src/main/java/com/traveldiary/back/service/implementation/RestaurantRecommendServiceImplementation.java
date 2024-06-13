package com.traveldiary.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantRecommendStatusResponseDto;
import com.traveldiary.back.entity.RestaurantEntity;
import com.traveldiary.back.entity.RestaurantRecommendEntity;
import com.traveldiary.back.repository.RestaurantRecommendRepository;
import com.traveldiary.back.repository.RestaurantRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.RestaurantRecommendService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantRecommendServiceImplementation implements RestaurantRecommendService{

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantRecommendRepository restaurantRecommendRepository;

    @Override
    public ResponseEntity<? super GetRestaurantRecommendStatusResponseDto> getRestRecommendStatus(Integer restaurantNumber, String userId) {

        boolean existsRecommend = false;

        try{

            if(restaurantNumber == null) return ResponseDto.noExistBoard();
            if(userId == null) return ResponseDto.authenticationFailed();

            existsRecommend = restaurantRecommendRepository.existsByUserIdAndRestaurantNumber(userId, restaurantNumber);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRestaurantRecommendStatusResponseDto.success(existsRecommend);

    }

    @Override
    public ResponseEntity<ResponseDto> patchRestRecommend(Integer restaurantNumber, String userId) {

        try{

            RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantNumber(restaurantNumber);
            if(restaurantEntity == null) return ResponseDto.noExistData();

            boolean existId = userRepository.existsByUserId(userId);
            if(!existId) return ResponseDto.noExistUser();

            boolean isRecommendedId = restaurantRecommendRepository.existsByUserIdAndRestaurantNumber(userId, restaurantNumber);

            RestaurantRecommendEntity restaurantRecommendEntity = new RestaurantRecommendEntity(restaurantNumber, userId);

            if(isRecommendedId){
                restaurantRecommendRepository.delete(restaurantRecommendEntity);
                restaurantEntity.decreaseRecommendCount();
            }
            else{
                restaurantRecommendRepository.save(restaurantRecommendEntity);
                restaurantEntity.increaseRecommendCount();
            }

            restaurantRepository.save(restaurantEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

}
