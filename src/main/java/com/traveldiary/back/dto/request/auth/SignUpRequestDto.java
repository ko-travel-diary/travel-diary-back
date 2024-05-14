package com.traveldiary.back.dto.request.auth;

// 회원가입 Request Body Dto
@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto{
    @NotBlank
    private String userId;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
    private String userPassword;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]*@([-.]?[a-zA-Z0-9])*\\.[a-zA-Z]{2,4}$")
    private String userEmail;
    @NotBlank
    private String authNumber;
}