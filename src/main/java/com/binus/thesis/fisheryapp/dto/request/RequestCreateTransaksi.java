package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestCreateTransaksi {

    private String idIkan;

    private BigDecimal jumlah;

    private LocalDate tanggalDibutuhkan;

    private String alamatPembeli;

    private String catatan;

    private String idUserPembeli;

    private String opsiPengiriman;
}
