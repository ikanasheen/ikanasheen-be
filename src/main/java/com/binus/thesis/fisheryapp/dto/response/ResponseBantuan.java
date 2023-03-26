package com.binus.thesis.fisheryapp.dto.response;

import lombok.Data;

@Data
public class ResponseBantuan {

    private String idBantuan;

    private String namaBantuan;

    private String jenisBantuan;

    private String kuota;

    private String formatProposal;

    private String statusBantuan;
}
