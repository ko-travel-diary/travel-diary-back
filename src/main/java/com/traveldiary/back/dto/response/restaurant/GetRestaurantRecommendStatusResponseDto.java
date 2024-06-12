package com.traveldiary.back.dto.response.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetRestaurantRecommendStatusResponseDto extends ResponseDto {
    
    private boolean restRecommendStatus;

    private GetRestaurantRecommendStatusResponseDto(boolean restRecommendStatus) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restRecommendStatus = restRecommendStatus;
    }

    public static ResponseEntity<GetRestaurantRecommendStatusResponseDto> success(boolean restRecommendStatus) {
        GetRestaurantRecommendStatusResponseDto responseBody = new GetRestaurantRecommendStatusResponseDto(restRecommendStatus);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}