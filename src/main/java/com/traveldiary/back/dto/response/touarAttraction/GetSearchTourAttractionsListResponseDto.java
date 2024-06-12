package com.traveldiary.back.dto.response.touarAttraction;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.TourAttractionsListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.repository.resultSet.GetTourAttractionsResultSet;

import lombok.Getter;

@Getter
public class GetSearchTourAttractionsListResponseDto extends ResponseDto {

    private List<TourAttractionsListItem> tourAttractionsListItem;

    private GetSearchTourAttractionsListResponseDto(List<GetTourAttractionsResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.tourAttractionsListItem = TourAttractionsListItem.getTourAttractionsList(resultSets);
    };

    public static ResponseEntity<GetSearchTourAttractionsListResponseDto> success(List<GetTourAttractionsResultSet> resultSets) {
        GetSearchTourAttractionsListResponseDto responseBody = new GetSearchTourAttractionsListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}