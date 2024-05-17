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

import com.traveldiary.back.dto.request.review.PatchTravelCommentRequestDto;
import com.traveldiary.back.dto.request.review.PatchTravelFavoriteReviewRequestDto;
import com.traveldiary.back.dto.request.review.PatchTravelReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelCommentRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewBoardResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewCommentListResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewDetailResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewMyListResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewSearchResponseDto;
import com.traveldiary.back.service.TravelCommentService;
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
    private final TravelCommentService travelCommentService;

    @GetMapping("/list")
    public ResponseEntity<? super GetTravelReviewBoardResponseDto> getBoardList() {
        ResponseEntity<? super GetTravelReviewBoardResponseDto> response = travelReviewService.getReviewBoardList();
        return response;
    }

    @GetMapping("/list/search")
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewSearchList(
        @RequestParam("word") String word
    ) {
        ResponseEntity<? super GetTravelReviewSearchResponseDto> response = travelReviewService.getReviewSearchList(word);
        return response;
    }

    @GetMapping("/{reviewNumber}")
    public ResponseEntity<? super GetTravelReviewDetailResponseDto> getReview(
        @PathVariable("reviewNumber") int reviewNumber
    ) {
    ResponseEntity<? super GetTravelReviewDetailResponseDto> response = travelReviewService.getReview(reviewNumber);
    return response;
    }

    @GetMapping("/post/list")
    public ResponseEntity<? super GetTravelReviewMyListResponseDto> getReviewMyList(
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<? super GetTravelReviewMyListResponseDto> response = travelReviewService.getReviewMyList(userId);
    return response;
    }

    @GetMapping("/{reviewNumber}/comment/list")
    public ResponseEntity<? super GetTravelReviewCommentListResponseDto> getReviewCommentList(
        @PathVariable("reviewNumber") int reviewNumber
    ) {
    ResponseEntity<? super GetTravelReviewCommentListResponseDto> response = travelCommentService.getTravelCommentList(reviewNumber);
    return response;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> postTravelReview(
        @RequestBody @Valid PostTravelReviewRequestDto responseBody,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelReviewService.postTravelReview(responseBody, userId);
    return response;
    }

    @PostMapping("/{reviewNumber}/comment")
    public ResponseEntity<ResponseDto> postTravelComment(
        @RequestBody @Valid PostTravelCommentRequestDto responseBody,
        @PathVariable("reviewNumber") int reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelCommentService.postTravelComment(responseBody, reviewNumber, userId);
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

    @PatchMapping("/{reviewNumber}/favorite")
    public ResponseEntity<ResponseDto> patchtravelFavorite(
        @RequestBody PatchTravelFavoriteReviewRequestDto responseBody,
        @PathVariable("reviewNumber") int reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelFavoriteService.patchtravelFavorite(responseBody, reviewNumber, userId);
    return response;
    }

    @PatchMapping("/{reviewNumber}/view-count")
    public ResponseEntity<ResponseDto> patchtravelView(
        @PathVariable("reviewNumber") int reviewNumber
    ) {
    ResponseEntity<ResponseDto> response = travelReviewService.patchTravelView(reviewNumber);
    return response;
    }

    @PatchMapping("/{reviewNumber}/comment/{commentNumber}")
    public ResponseEntity<ResponseDto> patchTravelComment(
        @RequestBody @Valid PatchTravelCommentRequestDto responseBody,
        @PathVariable("commentNumber") int commentNumber,
        @PathVariable("reviewNumber") int reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelCommentService.patchTravelComment(responseBody, commentNumber, reviewNumber, userId);
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

    @DeleteMapping("/{reviewNumber}/comment/{commentNumber}")
    public ResponseEntity<ResponseDto> deleteTravelComment(
        @PathVariable("reviewNumber") int reviewNumber,
        @PathVariable("commentNumber") int commentNumber,
        @AuthenticationPrincipal String userId
    ) {
    ResponseEntity<ResponseDto> response = travelCommentService.deleteTravelComment(commentNumber, reviewNumber, userId);
    return response;
    }
}

