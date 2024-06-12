package com.traveldiary.back.dto.request.qna;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetQnaRequestDto {
    
    @NotBlank
    private Integer qnaNumber;

}