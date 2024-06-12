package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.review.PatchTravelCommentRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelCommentRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewCommentListResponseDto;

public interface TravelCommentService {
    
    ResponseEntity<ResponseDto> postTravelComment(PostTravelCommentRequestDto dto, Integer reviewNumber, String userId);
    
    ResponseEntity<? super GetTravelReviewCommentListResponseDto> getTravelCommentList(Integer reviewNumber);

    ResponseEntity<ResponseDto> patchTravelComment(PatchTravelCommentRequestDto dto, Integer commentNumber, Integer reviewNumber, String userId);

    ResponseEntity<ResponseDto> deleteTravelComment(Integer commentNumber, Integer reviewNumber, String userId);
    
}
