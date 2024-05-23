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
public class GetSearchRestaurantListResponseDto extends ResponseDto{
        private List<RestaurantListItem> restaurantListItem;

    private GetSearchRestaurantListResponseDto (List<GetRestaurantResultSet> resultSets){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restaurantListItem = RestaurantListItem.getRestaurantList(resultSets);
    }

    public static ResponseEntity<GetSearchRestaurantListResponseDto> success (List<GetRestaurantResultSet> resultSets) {
        GetSearchRestaurantListResponseDto responseBody = new GetSearchRestaurantListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
