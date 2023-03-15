package com.binus.thesis.fisheryapp.dto.response;

import com.binus.thesis.fisheryapp.model.Ikan;
import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Pembeli;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ResponseTransaksi {

    private String idTransaksi;

    private String idIkan;

    private String namaIkan;

    private String ukuran;

    private BigDecimal jumlah;

    private BigDecimal hargaAwal;

    private BigDecimal hargaNego;

    private BigDecimal hargaAkumulasiNego;

    private BigDecimal hargaAkhir;

    private LocalDate tanggalDibutuhkan;

    private String alamatPembeli;

    private String catatan;

    private String status;

    private String namaNelayan;

    private String namaPembeli;

    private LocalDate tanggalDiproses;

    private LocalDate tanggalSelesai;
}
