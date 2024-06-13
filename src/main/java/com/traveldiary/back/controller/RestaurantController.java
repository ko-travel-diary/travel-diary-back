package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.restaurant.PatchRestaurantRequestDto;
import com.traveldiary.back.dto.request.restaurant.PostRestaurantRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantListResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantRecommendStatusResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetSearchRestaurantListResponseDto;
import com.traveldiary.back.service.RestaurantRecommendService;
import com.traveldiary.back.service.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRecommendService restaurantRecommendService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> postRestaurant(
        @RequestBody @Valid PostRestaurantRequestDto responseBody,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = restaurantService.postRestaurant(responseBody, userId);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetRestaurantListResponseDto> getRestaurantList(
        @RequestParam(name="lat", required=false) Double lat,
        @RequestParam(name="lng", required=false) Double lng
    ) {
        ResponseEntity<? super GetRestaurantListResponseDto> response = restaurantService.getRestaurantList(lat, lng);
        return response;    
    }

    @GetMapping("/search")
    public ResponseEntity<? super GetSearchRestaurantListResponseDto> getSearchRestaurantList(
        @RequestParam ("searchWord") String searchWord
    ){
        ResponseEntity<? super GetSearchRestaurantListResponseDto> response = restaurantService.getSearchRestaurantList(searchWord);
        return response;
    }

    @GetMapping("/{restaurantNumber}")
    public ResponseEntity<? super GetRestaurantResponseDto> getRestaurant(
        @PathVariable ("restaurantNumber") Integer restaurantNumber
    ) {
        ResponseEntity<? super GetRestaurantResponseDto> response = restaurantService.getRestaurant(restaurantNumber);
        return response;
    }

    @GetMapping("/{restaurantNumber}/recommend")
    public ResponseEntity<? super GetRestaurantRecommendStatusResponseDto> getRecommendStatus(
        @PathVariable("restaurantNumber") int restaurantNumber,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetRestaurantRecommendStatusResponseDto> response = restaurantRecommendService.getRestRecommendStatus(restaurantNumber, userId);
        return response;
    }

    @PatchMapping("/{restaurantNumber}")
    public ResponseEntity<ResponseDto> patchRestaurant(
        @RequestBody @Valid PatchRestaurantRequestDto requestBody,
        @PathVariable ("restaurantNumber") Integer restaurantNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = restaurantService.patchRestaurant(requestBody, restaurantNumber, userId);
        return response;
    }

    @PatchMapping("/{restaurantNumber}/recommend")
    public ResponseEntity<ResponseDto> patchRestRecommend(
        @PathVariable("restaurantNumber") int restaurantNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = restaurantRecommendService.patchRestRecommend(restaurantNumber, userId);
        return response;
    }

    @DeleteMapping("/{restaurantNumber}")
    public ResponseEntity<ResponseDto> deleteRestaurant(
        @PathVariable ("restaurantNumber") Integer restaurantNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = restaurantService.deleteRestaurant(restaurantNumber, userId);
        return response;
    }

}
