package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.tourAttractions.PostTourAttractionsRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetSearchTourAttractionsListResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsListResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsResponseDto;
import com.traveldiary.back.service.TourAttractionsService;

import jakarta.validation.Valid;
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

    @GetMapping("/tourlist/search")
    public ResponseEntity<? super GetSearchTourAttractionsListResponseDto> getSearchTourAttractionsList (
        @RequestParam ("searchWord") String searchWord
    ){
        ResponseEntity<? super GetSearchTourAttractionsListResponseDto> response = tourAttractionsService.getSearchTourAttractionsList(searchWord);
        return response;
    }

    @GetMapping("/tourlist/{tourattractionsNumber}")
    public ResponseEntity<? super GetTourAttractionsResponseDto> getTourAttractions (
        @PathVariable ("tourattractionsNumber") Integer tourattractionsNumber
    ){
        ResponseEntity<? super GetTourAttractionsResponseDto> response = tourAttractionsService.getTourAttractions(tourattractionsNumber);
        return response;
    }

    @PostMapping("/addTourAttractions")
    public ResponseEntity<ResponseDto> postTourAttractions (
        @RequestBody @Valid PostTourAttractionsRequestDto responseBody,
        @AuthenticationPrincipal String userId
        ){
            ResponseEntity<ResponseDto> response = tourAttractionsService.postTourAttractions(responseBody, userId);
            return response;
        }
}
