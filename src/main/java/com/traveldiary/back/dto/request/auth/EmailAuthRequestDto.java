package com.traveldiary.back.dto.request.auth;

import com.traveldiary.back.common.util.EmaliRegexpUtil;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailAuthRequestDto {

    final String regexp = EmaliRegexpUtil.regexp;

    @NotNull
    @Pattern(regexp = regexp)
    private String userEmail;
    
}

