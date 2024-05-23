package com.traveldiary.back.dto.response.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class PostTravelReviewResponseDto extends ResponseDto{
    private Integer reviewNumber;

    public PostTravelReviewResponseDto(Integer reviewNumber) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewNumber = reviewNumber;
    }

    public static ResponseEntity<PostTravelReviewResponseDto> success(Integer reviewNumber) {
        PostTravelReviewResponseDto responseBody = new PostTravelReviewResponseDto(reviewNumber);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
