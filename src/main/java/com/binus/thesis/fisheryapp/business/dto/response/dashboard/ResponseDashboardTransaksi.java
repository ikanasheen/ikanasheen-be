package com.binus.thesis.fisheryapp.business.dto.response.dashboard;

import lombok.Data;

@Data
public class ResponseDashboardTransaksi {

    private Long jumlahTransaksi;

    private Long transaksiDiajukan;

    private Long transaksiDiproses;

    private Long transaksiNego;

    private Long transaksiDibatalkan;

    private Long transaksiSelesai;
}
