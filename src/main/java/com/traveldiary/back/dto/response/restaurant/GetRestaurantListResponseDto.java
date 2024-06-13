package com.traveldiary.back.dto.response.restaurant;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.RestaurantListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.RestaurantViewEntity;

import lombok.Getter;

@Getter
public class GetRestaurantListResponseDto extends ResponseDto {
    
    private List<RestaurantListItem> restaurantListItem;

    private GetRestaurantListResponseDto(List<RestaurantViewEntity> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restaurantListItem = RestaurantListItem.getRestaurantList(resultSets);
    }

    public static ResponseEntity<GetRestaurantListResponseDto> success(List<RestaurantViewEntity> resultSets) {
        GetRestaurantListResponseDto responseBody = new GetRestaurantListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
