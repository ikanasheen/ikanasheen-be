package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseTransaksi {

    private String idTransaksi;

    private String idIkan;

    private String namaIkan;

    private String ukuran;

    private String jumlah;

    private String hargaAwal;

    private String hargaNego;

    private String hargaAkumulasiNego;

    private String hargaAkhir;

    private LocalDate tanggalDibutuhkan;

    private String alamatPembeli;

    private String catatan;

    private String status;

    private String opsiPengiriman;

    private String catatanPengiriman;

    private String namaNelayan;

    private String alamatNelayan;

    private String kelurahanDesaNelayan;

    private String kecamatanNelayan;

    private String namaPembeli;

    private LocalDate tanggalDiproses;

    private LocalDate tanggalSelesai;

    private String tanggalDikirim;

    private String tanggalSiapDiambil;
}
