package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer restaurantImageNumber;
    private Integer restaurantNumber;
    private String restaurantImageUrl;
}
