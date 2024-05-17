package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.restaurant.GetRestaurantListResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantResponseDto;

public interface RestaurantService {
    ResponseEntity<? super GetRestaurantListResponseDto> getRestaurantList ();
    ResponseEntity<? super GetRestaurantResponseDto> getRestaurant (Integer restaurantNumber);
}
