package com.traveldiary.back.entity;

import com.traveldiary.back.entity.pk.TourRecommendPk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(TourRecommendPk.class)
public class TourAttractionsRecommendEntity {
    @Id
    private Integer tourAttractionsNumber;
    @Id
    private String userId;
}
