package com.binus.thesis.fisheryapp.business.dto.request;

import com.binus.thesis.fisheryapp.business.model.Dokumen;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class RequestCreateBantuan {

    private String namaBantuan;

    private String jenisBantuan;

    private String kuota;

    private List<Dokumen> dokumen;
}
