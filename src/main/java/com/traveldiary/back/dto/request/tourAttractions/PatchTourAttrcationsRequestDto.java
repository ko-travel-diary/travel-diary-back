package com.traveldiary.back.dto.request.tourAttractions;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchTourAttrcationsRequestDto {
    private List<String> tourAttractionsImageUrl;
    private String tourAttractionsName;
    private String tourAttractionsLocation;
    private String tourAttractionsTelNumber;
    private String tourAttractionsHours;
    private String tourAttractionsOutline;
    private String tourAttractionsLat;
    private String tourAttractionsLng;
}
