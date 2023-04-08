package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

@Data
public class ResponseUserProfile {

    private String idUser;

    private String username;

    private String password;

    private String noTelepon;

    private String email;

    private String status;

    private String namaLengkap;

    private String alamat;

    private String kecamatan;

    private String kelurahanDesa;
}
