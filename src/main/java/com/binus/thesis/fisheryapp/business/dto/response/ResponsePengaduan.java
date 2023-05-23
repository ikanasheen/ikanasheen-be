package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ResponsePengaduan {

    private String idPengaduan;

    private String idNelayan;

    private String noTelepon;

    private String email;

    private String aduan;

    private String penanganan;

    private String status;

    private String tanggalPengaduan;

    private String tanggalPenanganan;

    private ResponseNelayan nelayan;
}
