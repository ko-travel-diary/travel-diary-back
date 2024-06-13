package com.traveldiary.back.dto.response.restaurant;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.RestaurantEntity;

import lombok.Getter;

@Getter
public class GetRestaurantResponseDto extends ResponseDto {

    private List<String> restaurantImageUrl;
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantTelNumber;
    private String restaurantHours;
    private String restaurantOutline;
    private String restaurantMainMenu;
    private String restaurantServiceMenu;
    private Double restaurantLat;
    private Double restaurantLng;
    private Integer restaurantRecommendCount;

    private GetRestaurantResponseDto(RestaurantEntity restaurantEntity, List<String> restaurantImageUrl) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restaurantImageUrl = restaurantImageUrl;
        this.restaurantName = restaurantEntity.getRestaurantName();
        this.restaurantLocation = restaurantEntity.getRestaurantLocation();
        this.restaurantTelNumber = restaurantEntity.getRestaurantTelNumber();
        this.restaurantHours = restaurantEntity.getRestaurantHours();
        this.restaurantOutline = restaurantEntity.getRestaurantOutline();
        this.restaurantMainMenu = restaurantEntity.getRestaurantMainMenu();
        this.restaurantServiceMenu = restaurantEntity.getRestaurantServiceMenu();
        this.restaurantLat = restaurantEntity.getRestaurantLat();
        this.restaurantLng = restaurantEntity.getRestaurantLng();
        this.restaurantRecommendCount = restaurantEntity.getRestaurantRecommendCount();
    }

    public static ResponseEntity<GetRestaurantResponseDto> success(RestaurantEntity restaurantEntity, List<String> restaurantImageUrl) {
        GetRestaurantResponseDto responseBody = new GetRestaurantResponseDto(restaurantEntity, restaurantImageUrl);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}