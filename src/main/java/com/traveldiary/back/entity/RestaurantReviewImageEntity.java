package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "restaurantReviewImage")
@Table(name = "restaurant_review_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReviewImageEntity {
    @Id
    private Integer restaurantImageNumber;
    private Integer restaurantReviewNumber;
    private String restaurantReviewImageUrl;
}
