package com.traveldiary.back.dto.request.review;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시물 작성 Request Body Dto
@Getter
@Setter
@NoArgsConstructor
public class PostTravelReviewRequestDto{

    @NotBlank
    private String reviewTitle;
    @NotBlank
    private String reviewContent;
    private List<String> travelReviewImageUrl;
    private Integer travelScheduleNumber;

}
