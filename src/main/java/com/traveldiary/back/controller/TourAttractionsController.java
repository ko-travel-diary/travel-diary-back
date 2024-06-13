package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.tourAttractions.PatchTourAttrcationsRequestDto;
import com.traveldiary.back.dto.request.tourAttractions.PostTourAttractionsRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetSearchTourAttractionsListResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsListResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsRecommendResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsResponseDto;
import com.traveldiary.back.service.TourAttractionsRecommendService;
import com.traveldiary.back.service.TourAttractionsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/tour-attractions")
@RequiredArgsConstructor
public class TourAttractionsController {
    
    private final TourAttractionsService tourAttractionsService;
    private final TourAttractionsRecommendService tourAttractionsRecommendService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> postTourAttractions(
        @RequestBody @Valid PostTourAttractionsRequestDto responseBody,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = tourAttractionsService.postTourAttractions(responseBody, userId);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList(
        @RequestParam(name="lat", required=false) Double lat,
        @RequestParam(name="lng", required=false) Double lng
    ){
        ResponseEntity<? super GetTourAttractionsListResponseDto> response = tourAttractionsService.getTourAttractionsList(lat, lng);
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<? super GetSearchTourAttractionsListResponseDto> getSearchTourAttractionsList(
        @RequestParam ("searchWord") String searchWord
    ){
        ResponseEntity<? super GetSearchTourAttractionsListResponseDto> response = tourAttractionsService.getSearchTourAttractionsList(searchWord);
        return response;
    }

    @GetMapping("/{tourattractionsNumber}")
    public ResponseEntity<? super GetTourAttractionsResponseDto> getTourAttractions(
        @PathVariable ("tourattractionsNumber") Integer tourattractionsNumber
    ){
        ResponseEntity<? super GetTourAttractionsResponseDto> response = tourAttractionsService.getTourAttractions(tourattractionsNumber);
        return response;
    }

    @GetMapping("/{tourattractionsNumber}/recommend")
    public ResponseEntity<? super GetTourAttractionsRecommendResponseDto> getRecoomendStatus(
        @PathVariable("tourattractionsNumber") int tourattractionsNumber,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetTourAttractionsRecommendResponseDto> response = tourAttractionsRecommendService.getTourRecommendStatus(tourattractionsNumber, userId);
        return response;
    }

    @PatchMapping("/{tourattractionsNumber}")
    public ResponseEntity<ResponseDto> patchTourAttrcation(
        @RequestBody @Valid PatchTourAttrcationsRequestDto requestBody,
        @PathVariable("tourattractionsNumber") Integer tourattractionsNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = tourAttractionsService.patchTourAttractions(requestBody, tourattractionsNumber, userId);
        return response;
    }

    @PatchMapping("/{tourattractionsNumber}/recommend")
    public ResponseEntity<ResponseDto> patchRestRecommend(
        @PathVariable("tourattractionsNumber") int tourattractionsNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = tourAttractionsRecommendService.patchTourRecommend(tourattractionsNumber, userId);
        return response;
    }

    @DeleteMapping("/{tourattractionsNumber}")
    public ResponseEntity<ResponseDto> deleteTourAttractions(
        @PathVariable ("tourattractionsNumber") Integer tourattractionsNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = tourAttractionsService.deleteTourAttractions(tourattractionsNumber, userId);
        return response;
    }

}
