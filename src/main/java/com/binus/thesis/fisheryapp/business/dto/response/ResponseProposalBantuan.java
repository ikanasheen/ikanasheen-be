package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

@Data
public class ResponseProposalBantuan {

    private String idProposalBantuan;

    private String tanggalDiajukan;

    private String tanggalDisetujui;

    private String tanggalDitolak;

    private String file;

    private String statusProposal;

    private String idNelayan;

    private String idUserNelayan;

    private String namaNelayan;

    private String idBantuan;

    private String namaBantuan;

    private String jenisBantuan;
}
