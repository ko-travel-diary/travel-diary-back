package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travelReviewImage")
@Table(name = "travel_review_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelReviewImageEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer travelReviewImageNumber;

    @NotNull
    private Integer travelReviewNumber;

    private String travelReviewImageUrl;

    public TravelReviewImageEntity(Integer travelReviewNumber, String travelReviewImageUrl) {
        this.travelReviewNumber = travelReviewNumber;
        this.travelReviewImageUrl = travelReviewImageUrl;
    }

}