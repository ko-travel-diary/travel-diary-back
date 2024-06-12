package com.traveldiary.back.dto.response.touarAttraction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetTourAttractionsRecommendResponseDto extends ResponseDto {

    private boolean tourRecommendStatus;

    private GetTourAttractionsRecommendResponseDto(boolean tourRecommendStatus) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.tourRecommendStatus = tourRecommendStatus;
    }

    public static ResponseEntity<GetTourAttractionsRecommendResponseDto> success(boolean tourRecommendStatus) {
        GetTourAttractionsRecommendResponseDto responseBody = new GetTourAttractionsRecommendResponseDto(tourRecommendStatus);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}