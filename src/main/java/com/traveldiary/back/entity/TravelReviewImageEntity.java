package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer travelReivewImageNumber;
    private Integer travelReivewNumber;
    private String travelReivewImageUrl;
}
