package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestUpdateTransaksi {

    private String idTransaksi;

    private String idIkan;

    private BigDecimal jumlah;

    private LocalDate tanggalDibutuhkan;

    private String alamatPembeli;

    private String catatan;

    private String idUserPembeli;
}
