package com.binus.thesis.fisheryapp.business.dto.response.dashboard;

import lombok.Data;

@Data
public class ResponseDashboardTransaksiWeekly {

    private String tanggal;

    private Long transaksiDiajukan;

    private Long transaksiDiproses;

    private Long transaksiSelesai;
}
