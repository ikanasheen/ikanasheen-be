package com.binus.thesis.fisheryapp.business.dto.request;

import com.binus.thesis.fisheryapp.business.model.Dokumen;
import lombok.Data;

import java.util.List;

@Data
public class RequestUpdateBantuan {

    private String idBantuan;

    private String namaBantuan;

    private String jenisBantuan;

    private String statusBantuan;

    private String kuota;

    private List<Dokumen> dokumen;
}
