package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "restaurantRecommend")
@Table(name = "restaurant_recommend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRecommendEntity {
    @Id
    private Integer restaurantNumber;
    private String userId;
}
