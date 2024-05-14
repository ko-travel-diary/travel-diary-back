package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.review.PatchTravelFavoriteReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;

public interface TravelFavoriteService {

    ResponseEntity<ResponseDto> travelFavorite(PatchTravelFavoriteReviewRequestDto dto, int reviewNumber, String userId);
}