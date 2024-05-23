package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewFavoriteStatusResponseDto;

public interface TravelFavoriteService {

    ResponseEntity<ResponseDto> patchtravelFavorite(int reviewNumber, String userId);
    ResponseEntity<? super GetTravelReviewFavoriteStatusResponseDto> getFavoriteStatus (Integer reviewNumber, String userId);
}