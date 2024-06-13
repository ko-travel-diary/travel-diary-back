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
public class GetTravelReviewBoardResponseDto extends ResponseDto {

    private List<ReviewBoardListItem> reviewBoardList;

    public GetTravelReviewBoardResponseDto(List<TravelReviewViewEntity> travelReviewViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewBoardList = ReviewBoardListItem.getReviewList(travelReviewViewEntities);
    }

    public static ResponseEntity<GetTravelReviewBoardResponseDto> success(List<TravelReviewViewEntity> travelReviewViewEntities) {
        GetTravelReviewBoardResponseDto responseBody = new GetTravelReviewBoardResponseDto(travelReviewViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}