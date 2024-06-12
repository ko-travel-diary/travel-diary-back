package com.traveldiary.back.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostTravelCommentRequestDto {

    @NotBlank
    private String commentContent;

    private Integer commentParentsNumber;
}