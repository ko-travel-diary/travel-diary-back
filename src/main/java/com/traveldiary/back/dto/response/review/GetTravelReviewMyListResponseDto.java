package com.traveldiary.back.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.ReviewBoardListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.TravelReviewViewEntity;

import lombok.Getter;

@Getter
public class GetTravelReviewMyListResponseDto extends ResponseDto {

    private List<ReviewBoardListItem> TravelReviewMyList;

    public GetTravelReviewMyListResponseDto(List<TravelReviewViewEntity> travelReviewViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.TravelReviewMyList = ReviewBoardListItem.getReviewList(travelReviewViewEntities);
    }

    public static ResponseEntity<GetTravelReviewMyListResponseDto> success(List<TravelReviewViewEntity> travelReviewViewEntities) {
        GetTravelReviewMyListResponseDto responseBody = new GetTravelReviewMyListResponseDto(travelReviewViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}