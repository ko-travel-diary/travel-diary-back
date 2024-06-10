package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.tourAttractions.PatchTourAttrcationsRequestDto;
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
    public ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList (
        @RequestParam(name="lat", required=false) Double lat,
        @RequestParam(name="lng", required=false) Double lng
    ){
        ResponseEntity<? super GetTourAttractionsListResponseDto> response = tourAttractionsService.getTourAttractionsList(lat, lng);
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

    @DeleteMapping("/tourlist/control/{tourattractionsNumber}")
    public ResponseEntity<ResponseDto> deleteTourAttractions (
        @PathVariable ("tourattractionsNumber") Integer tourattractionsNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = tourAttractionsService.deleteTourAttractions(tourattractionsNumber, userId);
        return response;
    }

    @PatchMapping("/tourlist/control/{tourattractionsNumber}")
    public ResponseEntity<ResponseDto> patchTourAttrcation (
        @RequestBody @Valid PatchTourAttrcationsRequestDto requestBody,
        @PathVariable("tourattractionsNumber") Integer tourattractionsNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = tourAttractionsService.patchTourAttractions(requestBody, tourattractionsNumber, userId);
        return response;
    }

    @PutMapping("/tourlist/control/{tourattractionsNumber}")
    public ResponseEntity<ResponseDto> putTourAttractionImage (
        @PathVariable("tourattrationsNumber") Integer tourattractionsNumber,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = tourAttractionsService.deleteTourAttrcationsImage(tourattractionsNumber, userId);
        return response;
    };
}
