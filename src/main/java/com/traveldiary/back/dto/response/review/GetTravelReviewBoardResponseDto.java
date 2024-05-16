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

// 게시물 작성 Response Body Dto
@Getter
public class GetTravelReviewBoardResponseDto extends ResponseDto{

    private List<ReviewBoardListItem> reviewBoardList;

    public GetTravelReviewBoardResponseDto(List<TravelReviewEntity> travelReviewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewBoardList = ReviewBoardListItem.getReviewList(travelReviewEntities);
    }

    public static ResponseEntity<GetTravelReviewBoardResponseDto> success(List<TravelReviewEntity> travelReviewEntities) {
        GetTravelReviewBoardResponseDto responseBody = new GetTravelReviewBoardResponseDto(travelReviewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    
}
