package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "restaurant_review_image")
@Table(name = "restaurant_review_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReviewImageEntity {
    private Integer restaurantImageNumber;
    private Integer restaurantReviewNumber;
    private String restaurantReviewImageUrl;
}
