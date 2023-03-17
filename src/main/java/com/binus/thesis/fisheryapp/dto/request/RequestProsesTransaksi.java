package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestProsesTransaksi {

    private String idTransaksi;

    private String idUserNelayan;

    private String isNego;

    private String hargaNego;
}
