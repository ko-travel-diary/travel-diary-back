package com.traveldiary.back.dto.response.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetRestaurantRecommendStatusResponseDto extends ResponseDto{
    
    private boolean restRecommendeStatus;

    private GetRestaurantRecommendStatusResponseDto (boolean restRecommendeStatus){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restRecommendeStatus = restRecommendeStatus;
    }

    public static ResponseEntity<GetRestaurantRecommendStatusResponseDto> success (boolean restRecommendeStatus) {
        GetRestaurantRecommendStatusResponseDto responseBody = new GetRestaurantRecommendStatusResponseDto(restRecommendeStatus);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
