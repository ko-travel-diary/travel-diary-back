package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "RestaurantViewEntity")
@Table(name = "restaurant_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantViewEntity {

    @Id
    private Integer restaurantNumber;

    private String image;
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantOutline;
    private String restaurantHours;
    private String restaurantTelNumber;
    private String restaurantMainMenu;
    private String restaurantServiceMenu;
    private Double restaurantLat;
    private Double restaurantLng;
    private Integer restaurantRecommendCount;

}