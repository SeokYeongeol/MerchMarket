package com.example.merchmarket.domain.admin.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AdminSignUpRequest {

    @NotNull(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    @NotNull(message = "닉네임을 입력해주세요.")
    private String name;

    @NotNull(message = "비밀번호를 입력해주세요.")
    private String password;
}
