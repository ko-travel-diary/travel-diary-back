package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsListResponseDto;

public interface TourAttractionsService {
    ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList ();
}
