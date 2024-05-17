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
public class GetTourAttractionsListResponseDto extends ResponseDto {

    private List<TourAttractionsListItem> tourAttractionsListItem;

    private GetTourAttractionsListResponseDto (List<GetTourAttractionsResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.tourAttractionsListItem = TourAttractionsListItem.getTourAttractionsList(resultSets);
    };

    public static ResponseEntity<GetTourAttractionsListResponseDto> success(List<GetTourAttractionsResultSet> resultSets) {
        GetTourAttractionsListResponseDto responseBody = new GetTourAttractionsListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
