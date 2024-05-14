package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "restaurant_recommend")
@Table(name = "restaurant_recommend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRecommendEntity {
    private Integer restaurantNumber;
    private String userId;
}
