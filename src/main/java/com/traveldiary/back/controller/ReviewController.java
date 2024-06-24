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
import com.traveldiary.back.dto.request.review.PatchTravelReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelCommentRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewBoardResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewCommentListResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewDetailResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewFavoriteStatusResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewMyListResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewSearchResponseDto;
import com.traveldiary.back.dto.response.review.PostTravelReviewResponseDto;
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

    @PostMapping("/")
    public ResponseEntity<? super PostTravelReviewResponseDto> postTravelReview(
        @RequestBody @Valid PostTravelReviewRequestDto responseBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostTravelReviewResponseDto> response = travelReviewService.postTravelReview(responseBody, userId);
        return response;
    }

    @PostMapping("/{reviewNumber}/comment")
    public ResponseEntity<ResponseDto> postTravelComment(
        @RequestBody @Valid PostTravelCommentRequestDto responseBody,
        @PathVariable("reviewNumber") Integer reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = travelCommentService.postTravelComment(responseBody, reviewNumber, userId);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetTravelReviewBoardResponseDto> getBoardList() {
        ResponseEntity<? super GetTravelReviewBoardResponseDto> response = travelReviewService.getReviewBoardList();
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewSearchList(
        @RequestParam(value = "titleAndContent", required = false) String titleAndContent,
        @RequestParam(value = "writer", required = false) String writer,
        @RequestParam(value = "writedate", required = false) String writedate
    ) {
        ResponseEntity<? super GetTravelReviewSearchResponseDto> response = travelReviewService.getSearchList(titleAndContent, writer, writedate);
        return response;
    }

    @GetMapping("/{reviewNumber}")
    public ResponseEntity<? super GetTravelReviewDetailResponseDto> getReview(
        @PathVariable("reviewNumber") Integer reviewNumber
    ) {
        ResponseEntity<? super GetTravelReviewDetailResponseDto> response = travelReviewService.getReview(reviewNumber);
        return response;
    }

    @GetMapping("/my-list")
    public ResponseEntity<? super GetTravelReviewMyListResponseDto> getReviewMyList(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetTravelReviewMyListResponseDto> response = travelReviewService.getReviewMyList(userId);
        return response;
    }

    @GetMapping("/my-search")
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewMyListSearchList(
        @RequestParam("searchWord") String searchWord,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetTravelReviewSearchResponseDto> response = travelReviewService.getReviewMyListSearchList(searchWord, userId);
        return response;
    }

    @GetMapping("/{reviewNumber}/favorite")
    public ResponseEntity<? super GetTravelReviewFavoriteStatusResponseDto> getFavoriteStatus(
        @PathVariable("reviewNumber") Integer reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetTravelReviewFavoriteStatusResponseDto> response = travelFavoriteService.getFavoriteStatus(reviewNumber, userId);
        return response;
    }

    @GetMapping("/{reviewNumber}/comment/list")
    public ResponseEntity<? super GetTravelReviewCommentListResponseDto> getReviewCommentList(
        @PathVariable("reviewNumber") Integer reviewNumber
    ) {
        ResponseEntity<? super GetTravelReviewCommentListResponseDto> response = travelCommentService.getTravelCommentList(reviewNumber);
        return response;
    }

    @PatchMapping("/{reviewNumber}")
    public ResponseEntity<ResponseDto> patchTravelReview(
        @RequestBody PatchTravelReviewRequestDto responseBody,
        @PathVariable("reviewNumber") Integer reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = travelReviewService.patchTravelReview(responseBody, reviewNumber, userId);
        return response;
    }

    @PatchMapping("/{reviewNumber}/favorite")
    public ResponseEntity<ResponseDto> patchTravelFavorite(
        @PathVariable("reviewNumber") Integer reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = travelFavoriteService.patchTravelFavorite(reviewNumber, userId);
        return response;
    }

    @PatchMapping("/{reviewNumber}/view-count")
    public ResponseEntity<ResponseDto> patchTravelView(
        @PathVariable("reviewNumber") Integer reviewNumber
    ) {
        ResponseEntity<ResponseDto> response = travelReviewService.patchTravelView(reviewNumber);
        return response;
    }

    @PatchMapping("/{reviewNumber}/comment/{commentNumber}")
    public ResponseEntity<ResponseDto> patchTravelComment(
        @RequestBody @Valid PatchTravelCommentRequestDto responseBody,
        @PathVariable("commentNumber") Integer commentNumber,
        @PathVariable("reviewNumber") Integer reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = travelCommentService.patchTravelComment(responseBody, commentNumber, reviewNumber, userId);
        return response;
    }

    @DeleteMapping("/{reviewNumber}")
    public ResponseEntity<ResponseDto> deleteTravelReview(
        @PathVariable("reviewNumber") Integer reviewNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = travelReviewService.deleteTravelReview(reviewNumber, userId);
        return response;
    }

    @DeleteMapping("/{reviewNumber}/comment/{commentNumber}")
    public ResponseEntity<ResponseDto> deleteTravelComment(
        @PathVariable("reviewNumber") Integer reviewNumber,
        @PathVariable("commentNumber") Integer commentNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = travelCommentService.deleteTravelComment(commentNumber, reviewNumber, userId);
        return response;
    }
    
}