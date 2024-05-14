package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.review.PatchTravelReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;

public interface TravelReviewService {

    ResponseEntity<ResponseDto> postTravelReview(PostTravelReviewRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchTravelReview(PatchTravelReviewRequestDto dto, int reviewNumber, String userId);
    ResponseEntity<ResponseDto> deleteTravelReview(int reviewNumber, String userId);

}

