package com.traveldiary.back.dto.request.review;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시물 좋아요 Request Body Dto
@Getter
@Setter
@NoArgsConstructor
public class PatchTravelFavoriteReviewRequestDto {

    @NotNull
    private Integer reviewNumber;
    @NotNull
    private String userId;
    
}
