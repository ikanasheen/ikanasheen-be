package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

@Data
public class RequestRegisterNelayan {

    private int idRole;

    private String namaLengkap;

    private String gender;

    private String tanggalLahir;

    private String alamat;

    private String kecamatanId;

    private String kecamatan;

    private String kelurahanDesa;

    private String noTelepon;

    private String email;

    private String username;

    private String password;

    private String confirmPassword;

    private boolean showPassword;
}
