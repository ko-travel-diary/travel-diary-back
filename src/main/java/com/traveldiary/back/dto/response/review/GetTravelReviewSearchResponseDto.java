package com.traveldiary.back.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.ReviewBoardListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.repository.resultSet.GetTravelReviewResultSet;

import lombok.Getter;

// 검색 리스트 불러오기 Response Body Dto
@Getter
public class GetTravelReviewSearchResponseDto extends ResponseDto{

    List<ReviewBoardListItem> reviewSearchList;

    public GetTravelReviewSearchResponseDto(List<GetTravelReviewResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewSearchList = ReviewBoardListItem.getReviewList(resultSets);
    }

    public static ResponseEntity<GetTravelReviewSearchResponseDto> success(List<GetTravelReviewResultSet> resultSets) {
        GetTravelReviewSearchResponseDto responseBody = new GetTravelReviewSearchResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
