package com.traveldiary.back.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시물 댓글 수정 Request Body Dto
@Getter
@Setter
@NoArgsConstructor
public class PatchTravelCommentRequestDto {
    
    @NotBlank
    private String commentContent;
}
