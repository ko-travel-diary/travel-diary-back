package com.traveldiary.back.dto.request.restaurant;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRestaurantRequestDto {

    @NotNull
    private String restaurantName;

    @NotNull
    private String restaurantLocation;

    @NotNull
    private String restaurantMainMenu;

    @NotNull
    private Double restaurantLat;
    
    @NotNull
    private Double restaurantLng;
    
    private String restaurantTelNumber;
    private String restaurantHours;
    private String restaurantServiceMenu;
    private String restaurantOutline;
    private List<String> restaurantImageUrl;

}