package com.traveldiary.back.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.ReviewCommentListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.TravelCommentEntity;

import lombok.Getter;

// 게시물의 댓글 리스트 보기 Response Body Dto
@Getter
public class GetTravelReviewCommentListResponseDto extends ResponseDto{

    private List<ReviewCommentListItem> reviewCommentList;

    public GetTravelReviewCommentListResponseDto(List<TravelCommentEntity> travelCommentEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewCommentList = ReviewCommentListItem.getCommentList(travelCommentEntities);
    }

    public static ResponseEntity<GetTravelReviewCommentListResponseDto> success(List<TravelCommentEntity> travelCommentEntities) {
        GetTravelReviewCommentListResponseDto responseBody = new GetTravelReviewCommentListResponseDto(travelCommentEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
