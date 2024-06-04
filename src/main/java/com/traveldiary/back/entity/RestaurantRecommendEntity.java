package com.traveldiary.back.entity;

import com.traveldiary.back.entity.pk.RestRecommendPk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(RestRecommendPk.class)
public class RestaurantRecommendEntity {
    @Id
    private Integer restaurantNumber;
    @Id
    private String userId;
}
