package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "restaurantImage")
@Table(name = "restaurant_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantImageNumber;
    @NotNull
    private Integer restaurantNumber;
    @NotNull
    private String restaurantImageUrl;

    public RestaurantImageEntity (Integer restaurantNumber, String restaurantImageUrl){
        this.restaurantNumber = restaurantNumber;
        this.restaurantImageUrl = restaurantImageUrl;
    }
}
