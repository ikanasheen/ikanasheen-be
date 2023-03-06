package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequestDto {

    private String username;

    private String password;

    private String status;

    private String confirmPassword;

    private boolean showPassword;
}
