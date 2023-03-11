package com.binus.thesis.fisheryapp.dto.request;

import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Pembeli;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestCreateTransaksi {

    private String idIkan;

    private int jumlah;

    private LocalDate tanggalDibutuhkan;

    private String alamat;

    private String catatan;

    private String idUserPembeli;
}
