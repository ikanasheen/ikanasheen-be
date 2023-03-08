package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {

    private String idUser;

    private String oldPassword;

    private String password;

    private String confirmPassword;

    private boolean showPassword;
}
