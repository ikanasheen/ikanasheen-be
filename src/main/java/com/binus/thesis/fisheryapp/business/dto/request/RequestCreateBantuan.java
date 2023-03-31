package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestCreateBantuan {

    private String namaBantuan;

    private String jenisBantuan;

    private String kuota;
}
