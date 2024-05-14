package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tour_attractions_review_image")
@Table(name = "tour_attractions_review_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourAttractionsReviewImageEntity {
    @Id
    private Integer tourAttractionsImageNumber;
    private Integer tourAttractionsNumber;
    private String tourAttractionsReviewImageUrl;
}
