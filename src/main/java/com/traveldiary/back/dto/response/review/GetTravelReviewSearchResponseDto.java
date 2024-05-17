package com.traveldiary.back.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.ReviewBoardListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.TravelReviewEntity;

import lombok.Getter;

// 검색 리스트 불러오기 Response Body Dto
@Getter
public class GetTravelReviewSearchResponseDto extends ResponseDto{

    List<ReviewBoardListItem> reviewSearchList;

    public GetTravelReviewSearchResponseDto(List<TravelReviewEntity> travelReviewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewSearchList = ReviewBoardListItem.getReviewList(travelReviewEntities);
    }

    public static ResponseEntity<GetTravelReviewSearchResponseDto> success(List<TravelReviewEntity> travelReviewEntities) {
        GetTravelReviewSearchResponseDto responseBody = new GetTravelReviewSearchResponseDto(travelReviewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
