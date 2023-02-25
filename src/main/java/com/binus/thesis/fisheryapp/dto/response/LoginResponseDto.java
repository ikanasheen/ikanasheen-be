package com.binus.thesis.fisheryapp.dto.response;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginResponseDto {

    @NotEmpty(message = "cannot be null")
    private String username;

    @NotEmpty(message = "cannot be null")
    private String password;
}
