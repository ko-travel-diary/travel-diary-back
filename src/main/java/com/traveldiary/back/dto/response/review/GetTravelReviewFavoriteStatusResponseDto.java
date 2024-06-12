package com.traveldiary.back.dto.response.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetTravelReviewFavoriteStatusResponseDto extends ResponseDto {

    private boolean favoriteStatus;

    private GetTravelReviewFavoriteStatusResponseDto(boolean favoriteStatus) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteStatus = favoriteStatus;
    }

    public static ResponseEntity<GetTravelReviewFavoriteStatusResponseDto> success (boolean favoriteStatus) {
        GetTravelReviewFavoriteStatusResponseDto responseBody = new GetTravelReviewFavoriteStatusResponseDto(favoriteStatus);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}