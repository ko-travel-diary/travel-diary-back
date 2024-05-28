package com.traveldiary.back.dto.request.restaurant;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRestaurantRequestDto {
    private List<String> restaurantImageUrl;
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantTelNumber;
    private String restaurantHours;
    private String restaurantOutline;
    private String restaurantMainMenu;
    private String restaurantServiceMenu;
    private double restaurantLat;
    private double restaurantLng;
}