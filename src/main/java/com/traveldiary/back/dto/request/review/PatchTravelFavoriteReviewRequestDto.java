package com.traveldiary.back.dto.request.review;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchTravelFavoriteReviewRequestDto {
    @NotNull
    private int reviewNumber;
    @NotNull
    private String userId;
    
}
