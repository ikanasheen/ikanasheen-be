package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

@Data
public class RequestRegisterPembeli {

    private int idRole;

    private String namaLengkap;

    private String alamat;

    private String noTelepon;

    private String email;

    private String username;

    private String password;

    private String confirmPassword;

    private boolean showPassword;
}
