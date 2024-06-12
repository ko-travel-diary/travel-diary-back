package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.review.PatchTravelReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewBoardResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewDetailResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewMyListResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewSearchResponseDto;
import com.traveldiary.back.dto.response.review.PostTravelReviewResponseDto;

public interface TravelReviewService {

    ResponseEntity<? super PostTravelReviewResponseDto> postTravelReview(PostTravelReviewRequestDto dto, String userId);
    
    ResponseEntity<? super GetTravelReviewBoardResponseDto> getReviewBoardList();
    ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewTitleAndContentSearchList(String searchWord);
    ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewWriterSearchList(String searchWord);
    ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewWriteDateSearchList(String searchWord);
    ResponseEntity<? super GetTravelReviewDetailResponseDto> getReview(Integer reviewNumber);
    ResponseEntity<? super GetTravelReviewMyListResponseDto> getReviewMyList(String userId);
    ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewMyListSearchList(String userId, String searchWord);

    ResponseEntity<ResponseDto> patchTravelReview(PatchTravelReviewRequestDto dto, Integer reviewNumber, String userId);
    ResponseEntity<ResponseDto> patchTravelView(Integer reviewNumber);

    ResponseEntity<ResponseDto> deleteTravelReview(Integer reviewNumber, String userId);
}

