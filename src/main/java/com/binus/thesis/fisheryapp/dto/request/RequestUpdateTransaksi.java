package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestUpdateTransaksi {

    private String idTransaksi;

    private String idIkan;

    private int jumlah;

    private LocalDate tanggalDibutuhkan;

    private String alamat;

    private String catatan;

    private String idUserPembeli;
}
