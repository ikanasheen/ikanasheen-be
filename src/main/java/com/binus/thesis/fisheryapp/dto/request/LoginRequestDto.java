package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LoginRequestDto{

    private String username;

    private String password;

    private String confirmPassword;

    private boolean showPassword;
}
