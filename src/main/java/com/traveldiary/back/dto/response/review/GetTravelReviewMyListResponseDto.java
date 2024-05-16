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

// 내가 쓴 전체 게시물 불러오기 Response Body Dto
@Getter
public class GetTravelReviewMyListResponseDto extends ResponseDto{

    List<ReviewBoardListItem> TravelReviewMyList;

    public GetTravelReviewMyListResponseDto(List<TravelReviewEntity> travelReviewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.TravelReviewMyList = ReviewBoardListItem.getReviewList(travelReviewEntities);
    }

    public static ResponseEntity<GetTravelReviewMyListResponseDto> success(List<TravelReviewEntity> travelReviewEntities) {
        GetTravelReviewMyListResponseDto responseBody = new GetTravelReviewMyListResponseDto(travelReviewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
