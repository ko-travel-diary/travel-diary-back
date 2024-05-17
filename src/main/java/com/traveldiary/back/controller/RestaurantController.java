package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.response.restaurant.GetRestaurantListResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantResponseDto;
import com.traveldiary.back.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restlist")
    public ResponseEntity<? super GetRestaurantListResponseDto> getRestaurantList () {
        ResponseEntity<? super GetRestaurantListResponseDto> response = restaurantService.getRestaurantList();
        return response;
    }

    @GetMapping("/restlist/{restaurantNumber}")
    public ResponseEntity<? super GetRestaurantResponseDto> getRestaurant (
        @PathVariable ("restaurantNumber") Integer restaurantNumber
    ) {
        ResponseEntity<? super GetRestaurantResponseDto> response = restaurantService.getRestaurant(restaurantNumber);
        return response;
    }
}
