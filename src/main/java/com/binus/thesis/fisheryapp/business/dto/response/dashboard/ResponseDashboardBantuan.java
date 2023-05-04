package com.binus.thesis.fisheryapp.business.dto.response.dashboard;

import lombok.Data;

@Data
public class ResponseDashboardBantuan {

    private Long bantuanTersedia;

    private Long bantuanDiajukan;

    private Long bantuanTerproses;

    private Long bantuanDisetujui;

    private Long bantuanDitolak;
}
