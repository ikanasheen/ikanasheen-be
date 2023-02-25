package com.binus.thesis.fisheryapp.dto.request;

import com.binus.thesis.fisheryapp.enums.GenderEnum;
import lombok.Data;

@Data
public class RegisterNelayanRequestDto {

    private int idRole;

    private String namaLengkap;

    private GenderEnum gender;

    private String tanggalLahir;

    private String alamat;

    private String kecamatan;

    private String kelurahanDesa;

    private String noTelepon;

    private String email;

    private String username;

    private String password;
}
