package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

@Data
public class RequestCompleteCancelProsesPengirimanTransaksi {

    private String idTransaksi;

    private String catatanPengiriman;
}
