package com.binus.thesis.fisheryapp.dto.request;

import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Pembeli;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestCreateTransaksi {

    private String idIkan;

    private BigDecimal jumlah;

    private LocalDate tanggalDibutuhkan;

    private String alamat;

    private String catatan;

    private String idUserPembeli;
}
