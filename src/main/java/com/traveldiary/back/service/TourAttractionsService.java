package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.tourAttractions.PostTourAttractionsRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetSearchTourAttractionsListResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsListResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsResponseDto;

public interface TourAttractionsService {
    ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList ();
    ResponseEntity<? super GetSearchTourAttractionsListResponseDto> getSearchTourAttractionsList (String searchWord);
    ResponseEntity<? super GetTourAttractionsResponseDto> getTourAttractions (Integer tourAttractionsNumber);
    ResponseEntity<ResponseDto> postTourAttractions (PostTourAttractionsRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteTourAttractions (Integer tourAttractionsNumber, String userId);
}
