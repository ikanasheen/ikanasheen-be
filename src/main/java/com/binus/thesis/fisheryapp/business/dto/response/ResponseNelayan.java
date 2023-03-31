package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseNelayan {

    private String idNelayan;

    private String namaLengkap;

    private String gender;

    private LocalDate tanggalLahir;

    private String alamat;

    private String kecamatan;

    private String kelurahanDesa;

    private String noTelepon;

    private String email;

    private ResponseUser user;
}
