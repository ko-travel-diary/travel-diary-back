package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantListResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantResponseDto;
import com.traveldiary.back.entity.RestaurantEntity;
import com.traveldiary.back.entity.RestaurantImageEntity;
import com.traveldiary.back.repository.RestaurantImageRepository;
import com.traveldiary.back.repository.RestaurantRepository;
import com.traveldiary.back.repository.resultSet.GetRestaurantResultSet;
import com.traveldiary.back.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImplementation implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final RestaurantImageRepository restaurantImageRepository;

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
    
}