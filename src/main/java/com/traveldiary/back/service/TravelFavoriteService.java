package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewFavoriteStatusResponseDto;

public interface TravelFavoriteService {

    ResponseEntity<? super GetTravelReviewFavoriteStatusResponseDto> getFavoriteStatus (Integer reviewNumber, String userId);

    ResponseEntity<ResponseDto> patchTravelFavorite(Integer reviewNumber, String userId);

}