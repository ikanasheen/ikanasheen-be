package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseProposalBantuan {

    private String idProposalBantuan;

    private String tanggalDiajukan;

    private String tanggalDisetujui;

    private String tanggalDitolak;

    private String statusProposal;

    private String idNelayan;

    private String idUserNelayan;

    private String namaNelayan;

    private String idBantuan;

    private String namaBantuan;

    private String jenisBantuan;

    private List<ResponseDokumen> dokumen;
}
