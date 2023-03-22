package com.binus.thesis.fisheryapp.dto.response;

import com.binus.thesis.fisheryapp.model.Ikan;
import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Pembeli;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseTransaksi {

    private String idTransaksi;

    private ResponseIkan ikan;

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

    private String namaNelayan;

    private String alamatNelayan;

    private String kelurahanDesaNelayan;

    private String kecamatanNelayan;

    private String namaPembeli;

    private LocalDate tanggalDiproses;

    private LocalDate tanggalSelesai;
}
