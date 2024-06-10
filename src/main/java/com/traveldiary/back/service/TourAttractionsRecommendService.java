package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsRecommendResponseDto;

public interface TourAttractionsRecommendService {

    ResponseEntity<ResponseDto> patchTourRecommend(int tourAttractionNumber, String userId);
    ResponseEntity<? super GetTourAttractionsRecommendResponseDto> getTourRecommendStatus (Integer tourAttractionNumber, String userId);
}
