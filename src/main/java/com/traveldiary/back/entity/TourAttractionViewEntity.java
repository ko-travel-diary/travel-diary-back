package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TourAttractionViewEntity")
@Table(name = "tour_attractions_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourAttractionViewEntity {

    @Id
    private Integer tourAttractionsNumber;

    private String image;
    private String tourAttractionsName;
    private String tourAttractionsLocation;
    private String tourAttractionsOutline;
    private String tourAttractionsHours;
    private String tourAttractionsTelNumber;
    private Double tourAttractionsLat;
    private Double tourAttractionsLng;
    private Integer tourAttractionsRecommendCount;

}