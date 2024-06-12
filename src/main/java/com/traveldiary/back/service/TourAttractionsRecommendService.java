package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsRecommendResponseDto;

public interface TourAttractionsRecommendService {

    ResponseEntity<? super GetTourAttractionsRecommendResponseDto> getTourRecommendStatus(Integer tourAttractionNumber, String userId);

    ResponseEntity<ResponseDto> patchTourRecommend(Integer tourAttractionNumber, String userId);

}
