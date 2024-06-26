package com.traveldiary.back.entity;

import com.traveldiary.back.dto.request.restaurant.PatchRestaurantRequestDto;
import com.traveldiary.back.dto.request.restaurant.PostRestaurantRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "restaurant")
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantNumber;

    @NotBlank
    private String restaurantName;

    @NotBlank
    private String restaurantLocation;
    
    @NotBlank
    private String restaurantMainMenu;

    @NotNull
    private Integer restaurantRecommendCount;

    @NotNull
    private Double restaurantLat;

    @NotNull
    private Double restaurantLng;

    private String restaurantTelNumber;
    private String restaurantHours;
    private String restaurantServiceMenu;
    private String restaurantOutline;
    
    public RestaurantEntity(PostRestaurantRequestDto dto) {
        this.restaurantName = dto.getRestaurantName();
        this.restaurantOutline = dto.getRestaurantOutline();
        this.restaurantLocation = dto.getRestaurantLocation();
        this.restaurantTelNumber = dto.getRestaurantTelNumber();
        this.restaurantHours = dto.getRestaurantHours();
        this.restaurantMainMenu = dto.getRestaurantMainMenu();
        this.restaurantServiceMenu = dto.getRestaurantServiceMenu();
        this.restaurantRecommendCount = 0;
        this.restaurantLat = dto.getRestaurantLat();
        this.restaurantLng = dto.getRestaurantLng();
    }

    public void update(PatchRestaurantRequestDto dto) {
        this.restaurantName = dto.getRestaurantName();
        this.restaurantOutline = dto.getRestaurantOutline();
        this.restaurantLocation = dto.getRestaurantLocation();
        this.restaurantTelNumber = dto.getRestaurantTelNumber();
        this.restaurantHours = dto.getRestaurantHours();
        this.restaurantMainMenu = dto.getRestaurantMainMenu();
        this.restaurantServiceMenu = dto.getRestaurantServiceMenu();
        this.restaurantLat = dto.getRestaurantLat();
        this.restaurantLng = dto.getRestaurantLng();
    }

    public void increaseRecommendCount() {
        this.restaurantRecommendCount++;
    }

    public void decreaseRecommendCount() {
        this.restaurantRecommendCount--;
    }
    
}