package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tourAttractionsRecommend")
@Table(name = "tour_attractions_recommend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourAttractionsRecommendEntity {
    @Id
    private Integer tourAttractionsNumber;
    private String userId;
}
