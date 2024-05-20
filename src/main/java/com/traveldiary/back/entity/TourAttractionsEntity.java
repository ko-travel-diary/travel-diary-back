package com.traveldiary.back.entity;

import com.traveldiary.back.dto.request.tourAttractions.PostTourAttractionsRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @NotNull
    private Integer tourAttractionsRecommendCount;
    @NotBlank
    private String tourAttractionsLat;
    @NotBlank
    private String tourAttractionsLng;

    public TourAttractionsEntity (PostTourAttractionsRequestDto dto){
        this.tourAttractionsName = dto.getTourAttractionsName();
        this.tourAttractionsOutline = dto.getTourAttractionsOutline();
        this.tourAttractionsLocation = dto.getTourAttractionsLocation();
        this.tourAttractionsTelNumber = dto.getTourAttractionsTelNumber();
        this.tourAttractionsHours = dto.getTourAttractionsHours();
        this.tourAttractionsLat = dto.getTourAttractionsLat();
        this.tourAttractionsLng = dto.getTourAttractionsLng();
        this.tourAttractionsRecommendCount = 0;
    }
}
