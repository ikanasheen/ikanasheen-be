package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

@Data
public class ResponseBantuan {

    private String idBantuan;

    private String namaBantuan;

    private String jenisBantuan;

    private String kuota;

    private String kuotaTersisa;

    private String statusBantuan;

    private ResponseDokumen dokumen;
}
