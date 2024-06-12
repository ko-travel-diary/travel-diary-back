package com.traveldiary.back.dto.request.review;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchTravelReviewRequestDto {
    
    @NotBlank
    private String reviewTitle;

    @NotBlank
    private String reviewContent;

    private Integer travelScheduleNumber;
    private List<String> travelReviewImageUrl;
}