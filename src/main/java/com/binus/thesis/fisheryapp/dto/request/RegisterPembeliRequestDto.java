package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

@Data
public class RegisterPembeliRequestDto {

    private int idRole;

    private String namaLengkap;

    private String alamat;

    private String noTelepon;

    private String email;

    private String username;

    private String password;

    private String confirmPassword;
}
