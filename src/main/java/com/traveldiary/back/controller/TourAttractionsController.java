package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsListResponseDto;
import com.traveldiary.back.service.TourAttractionsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/tourattractions")
@RequiredArgsConstructor
public class TourAttractionsController {
    
    private final TourAttractionsService tourAttractionsService;

    @GetMapping("/tourlist")
    public ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList (){
        ResponseEntity<? super GetTourAttractionsListResponseDto> response = tourAttractionsService.getTourAttractionsList();
        return response;
    }
}
