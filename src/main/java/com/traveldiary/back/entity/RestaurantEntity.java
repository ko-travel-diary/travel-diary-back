package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
    private Integer restaurantNumber;
    @NotBlank
    private String restaurantName;
    @NotBlank
    private String restaurantOutline;
    @NotBlank
    private String restaurantLocation;
    @NotBlank
    private String restaurantTelNumber;
    @NotBlank
    private String restaurantHours;
    @NotBlank
    private String restaurantMainMenu;
    private String restaurantServiceMenu;
    @NotBlank
    private Integer restaurantRecommendCount;
    @NotBlank
    private Double restaurantLat;
    @NotBlank
    private Double restaurantLng;
}
