package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.restaurant.PostRestaurantRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantListResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetSearchRestaurantListResponseDto;
import com.traveldiary.back.entity.RestaurantEntity;
import com.traveldiary.back.entity.RestaurantImageEntity;
import com.traveldiary.back.entity.TourAttractionsImageEntity;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.repository.RestaurantImageRepository;
import com.traveldiary.back.repository.RestaurantRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.repository.resultSet.GetRestaurantResultSet;
import com.traveldiary.back.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImplementation implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final RestaurantImageRepository restaurantImageRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetRestaurantListResponseDto> getRestaurantList() {
        List<GetRestaurantResultSet> resultSets = null;

        try {
            resultSets = restaurantRepository.getRestaurantList();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    return GetRestaurantListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetSearchRestaurantListResponseDto> getSearchRestaurantList(String searchWord) {
        List<GetRestaurantResultSet> resultSets = null;

        try {
            resultSets = restaurantRepository.getSearchRestaurantList(searchWord);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    return GetSearchRestaurantListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRestaurantResponseDto> getRestaurant(Integer restaurantNumber) {

        RestaurantEntity restaurantEntity;
        List<String> restaurantImageUrl;

        try{

            restaurantEntity = restaurantRepository.findByRestaurantNumber(restaurantNumber);
            if(restaurantEntity == null) return ResponseDto.noExistData();

            restaurantImageUrl = new ArrayList<>();
            List<RestaurantImageEntity> restaurantImageEntities = restaurantImageRepository.findByRestaurantNumber(restaurantNumber);
            for(RestaurantImageEntity entity: restaurantImageEntities){
                String image = entity.getRestaurantImageUrl();
                restaurantImageUrl.add(image);
            }

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetRestaurantResponseDto.success(restaurantEntity, restaurantImageUrl);
    }

    @Override
    public ResponseEntity<ResponseDto> postRestaurant(PostRestaurantRequestDto dto, String userId) {
        try{

            UserEntity userEntity = userRepository.findByUserId(userId);
            String userRole = userEntity.getUserRole();
            System.out.println(userRole);
            if(userRole == "ROLE_USER") return ResponseDto.authenticationFailed();

            String restaurantName = dto.getRestaurantName();
            boolean existsed = restaurantRepository.existsByRestaurantName(restaurantName);
            if(existsed) return ResponseDto.duplicatedRestaurant();

            RestaurantEntity restaurantEntity = new RestaurantEntity(dto);
            restaurantRepository.save(restaurantEntity);

            int restaurantNumber = restaurantEntity.getRestaurantNumber();

            List<String> images = dto.getRestaurantImageUrl();
            for(String image : images){
                RestaurantImageEntity imageEntity = new RestaurantImageEntity(restaurantNumber, image);
                restaurantImageRepository.save(imageEntity);
            }
            
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteRestaurant(Integer restaurantNumber, String userId) {

        UserEntity userEntity;
        RestaurantEntity restaurantEntity;
        List<RestaurantImageEntity> restaurantImageEntities;

        try {

            userEntity = userRepository.findByUserId(userId);
            String role = userEntity.getUserRole();
            if (role == "ROLE_USER") return ResponseDto.authorizationFailed();

            restaurantImageEntities = restaurantImageRepository.findByRestaurantNumber(restaurantNumber);
            restaurantImageRepository.deleteAll(restaurantImageEntities);

            restaurantEntity = restaurantRepository.findByRestaurantNumber(restaurantNumber);
            restaurantRepository.delete(restaurantEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}