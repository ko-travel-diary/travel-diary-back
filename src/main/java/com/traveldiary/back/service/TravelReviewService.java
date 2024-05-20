package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.review.PatchTravelReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewBoardResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewDetailResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewMyListResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewSearchResponseDto;

public interface TravelReviewService {

    ResponseEntity<? super GetTravelReviewBoardResponseDto> getReviewBoardList();
    ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewSearchList(String searchWord);
    ResponseEntity<? super GetTravelReviewDetailResponseDto> getReview(Integer reviewNumber);
    ResponseEntity<? super GetTravelReviewMyListResponseDto> getReviewMyList(String userId);
    ResponseEntity<ResponseDto> postTravelReview(PostTravelReviewRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchTravelReview(PatchTravelReviewRequestDto dto, int reviewNumber, String userId);
    ResponseEntity<ResponseDto> patchTravelView(Integer reviewNumber);
    ResponseEntity<ResponseDto> deleteTravelReview(Integer reviewNumber, String userId);
    
}

