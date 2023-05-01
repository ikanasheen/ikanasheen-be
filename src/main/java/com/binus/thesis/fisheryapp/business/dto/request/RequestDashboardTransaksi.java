package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestDashboardTransaksi {

    private String role;

    private String idUser;
}
