package com.traveldiary.back.dto.request.tourAttractions;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostTourAttractionsRequestDto {

    @NotNull
    private String tourAttractionsName;

    @NotNull
    private String tourAttractionsLocation;

    @NotNull
    private Double tourAttractionsLat;

    @NotNull
    private Double tourAttractionsLng;

    private String tourAttractionsTelNumber;
    private String tourAttractionsHours;
    private String tourAttractionsOutline;
    private List<String> tourAttractionsImageUrl;

}