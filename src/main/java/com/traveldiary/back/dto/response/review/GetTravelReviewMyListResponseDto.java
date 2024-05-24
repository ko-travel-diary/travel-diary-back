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

// 내가 쓴 전체 게시물 불러오기 Response Body Dto
@Getter
public class GetTravelReviewMyListResponseDto extends ResponseDto{

    private List<ReviewBoardListItem> TravelReviewMyList;

    public GetTravelReviewMyListResponseDto(List<GetTravelReviewResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.TravelReviewMyList = ReviewBoardListItem.getReviewList(resultSets);
    }

    public static ResponseEntity<GetTravelReviewMyListResponseDto> success(List<GetTravelReviewResultSet> resultSets) {
        GetTravelReviewMyListResponseDto responseBody = new GetTravelReviewMyListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
