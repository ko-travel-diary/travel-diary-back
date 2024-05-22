package com.traveldiary.back.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUserNickNameRequestDto {
    @NotNull
    private String writerId;
}
