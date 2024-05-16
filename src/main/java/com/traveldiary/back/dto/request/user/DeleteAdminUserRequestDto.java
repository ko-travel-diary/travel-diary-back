package com.traveldiary.back.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteAdminUserRequestDto {
    @NotBlank
    private String deleteToUserId;
}
