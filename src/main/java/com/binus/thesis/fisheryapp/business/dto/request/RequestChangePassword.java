package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

@Data
public class RequestChangePassword {

    private String idUser;

    private String oldPassword;

    private String password;

    private String confirmPassword;

    private boolean showPassword;
}
