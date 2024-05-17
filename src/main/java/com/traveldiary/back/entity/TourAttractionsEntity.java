package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tourAttractions")
@Table(name = "tour_attractions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourAttractionsEntity {
    @Id
    private Integer tourAttractionsNumber;
    @NotBlank
    private String tourAttractionsName;
    @NotBlank
    private String tourAttractionsOutline;
    @NotBlank
    private String tourAttractionsLocation;
    @NotBlank
    private String tourAttractionsTelNumber;
    @NotBlank
    private String tourAttractionsHours;
    @NotBlank
    private Integer tourAttractionsRecommendCount;
    @NotBlank
    private Double tourAttractionsLat;
    @NotBlank
    private Double tourAttractionsLng;
}
