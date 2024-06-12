package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantRecommendStatusResponseDto;

public interface RestaurantRecommendService {

    ResponseEntity<? super GetRestaurantRecommendStatusResponseDto> getRestRecommendStatus(Integer restaurantNumber, String userId);

    ResponseEntity<ResponseDto> patchRestRecommend(Integer restaurantNumber, String userId);

}
