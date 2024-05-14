package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travel_review_image")
@Table(name = "travel_review_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelReviewImageEntity {
    @Id
    private Integer travelReivewImageNumber;
    private Integer travelReivewNumber;
    private String travelReivewImageUrl;
}