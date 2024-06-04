package com.traveldiary.back.entity.pk;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestRecommendPk implements Serializable {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "restaurant_number")
    private Integer restaurantNumber;
}
