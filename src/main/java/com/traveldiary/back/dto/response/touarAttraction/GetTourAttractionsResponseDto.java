package com.traveldiary.back.dto.response.touarAttraction;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.TourAttractionsEntity;

import lombok.Getter;

@Getter
public class GetTourAttractionsResponseDto extends ResponseDto{
    private List<String> tourAttractionsImageUrl;
    private String tourAttractionsName;
    private String tourAttractionsLocation;
    private String tourAttractionsTelNumber;
    private String tourAttractionsHours;
    private String tourAttractionsOutline;
    private double tourAttractionsLat;
    private double tourAttractionsLng;

    private GetTourAttractionsResponseDto (TourAttractionsEntity tourAttractionsEntity, List<String> tourAttractionsImageUrl){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.tourAttractionsImageUrl = tourAttractionsImageUrl;
        this.tourAttractionsName = tourAttractionsEntity.getTourAttractionsName();
        this.tourAttractionsLocation = tourAttractionsEntity.getTourAttractionsLocation();
        this.tourAttractionsTelNumber = tourAttractionsEntity.getTourAttractionsTelNumber();
        this.tourAttractionsHours = tourAttractionsEntity.getTourAttractionsHours();
        this.tourAttractionsOutline = tourAttractionsEntity.getTourAttractionsOutline();
        this.tourAttractionsLat = tourAttractionsEntity.getTourAttractionsLat();
        this.tourAttractionsLng = tourAttractionsEntity.getTourAttractionsLng();
    }

    public static ResponseEntity<GetTourAttractionsResponseDto> success (TourAttractionsEntity tourAttractionsEntity, List<String> tourAttractionsImageUrl) {
        GetTourAttractionsResponseDto responseBody = new GetTourAttractionsResponseDto(tourAttractionsEntity, tourAttractionsImageUrl);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}