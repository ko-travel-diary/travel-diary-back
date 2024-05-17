package com.traveldiary.back.dto.response.restaurant;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.RestaurantListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.repository.resultSet.GetRestaurantResultSet;

import lombok.Getter;

@Getter
public class GetRestaurantListResponseDto extends ResponseDto{
    
    private List<RestaurantListItem> restaurantListItems;

    private GetRestaurantListResponseDto (List<GetRestaurantResultSet> resultSets){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restaurantListItems = RestaurantListItem.getRestaurantList(resultSets);
    }

    public static ResponseEntity<GetRestaurantListResponseDto> success (List<GetRestaurantResultSet> resultSets) {
        GetRestaurantListResponseDto responseBody = new GetRestaurantListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}