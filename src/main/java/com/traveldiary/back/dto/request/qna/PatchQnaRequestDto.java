package com.traveldiary.back.dto.request.qna;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchQnaRequestDto {
    
    @NotBlank
    private String qnaTitle;

    @NotBlank
    private String qnaContent;

}
