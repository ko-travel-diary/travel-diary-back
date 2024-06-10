package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantRecommendStatusResponseDto;

public interface RestaurantRecommendService {

    ResponseEntity<ResponseDto> patchRestRecommend(int restaurantNumber, String userId);
    ResponseEntity<? super GetRestaurantRecommendStatusResponseDto> getRestRecommendStatus (Integer restaurantNumber, String userId);
}
