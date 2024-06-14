package com.traveldiary.back.dto.request.auth;

import com.traveldiary.back.common.util.EmaliRegexpUtil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto{

    final String regexp = EmaliRegexpUtil.regexp;

    @NotBlank
    private String userId;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
    private String userPassword;

    @NotNull
    @Pattern(regexp = regexp)
    private String userEmail;

    @NotBlank
    private String nickName;
    
    @NotBlank
    private String authNumber;
    
    private String profileImage;
    
}