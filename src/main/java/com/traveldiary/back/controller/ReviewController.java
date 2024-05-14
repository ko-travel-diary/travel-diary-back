package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.review.PatchTravelFavoriteReviewRequestDto;
import com.traveldiary.back.dto.request.review.PatchTravelReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.service.TravelFavoriteService;
import com.traveldiary.back.service.TravelReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final TravelReviewService travelReviewService;
    private final TravelFavoriteService travelFavoriteService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> postTravelReview(
        @RequestBody @Valid PostTravelReviewRequestDto responseBody,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelReviewService.postTravelReview(responseBody, userId);
    return response;
    }

    @PatchMapping("/{reviewNumber}")
    public ResponseEntity<ResponseDto> patchTravelReview(
        @RequestBody PatchTravelReviewRequestDto responseBody,
        @PathVariable("reviewNumber") int reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelReviewService.patchTravelReview(responseBody, reviewNumber, userId);
    return response;
    }

    @DeleteMapping("/{reviewNumber}")
    public ResponseEntity<ResponseDto> deleteTravelReview(
        @PathVariable("reviewNumber") int reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelReviewService.deleteTravelReview(reviewNumber, userId);
    return response;
    }

    @PatchMapping("/{reviewNumber}/favorite")
    public ResponseEntity<ResponseDto> travelFavorite(
        @RequestBody PatchTravelFavoriteReviewRequestDto responseBody,
        @PathVariable("reviewNumber") int reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelFavoriteService.travelFavorite(responseBody, reviewNumber, userId);
    return response;
    }
}

