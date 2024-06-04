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
public class TourRecommendPk implements Serializable{
    @Column(name = "tour_attractions_number")
    private Integer tourAttractionsNumber;
    @Column(name = "user_id")
    private String userId;
}
