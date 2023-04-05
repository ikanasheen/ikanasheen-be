package com.binus.thesis.fisheryapp.business.dto.request;

import com.binus.thesis.fisheryapp.business.model.Dokumen;
import lombok.Data;

import java.util.List;

@Data
public class RequestCreateProposal {

    private String idUserNelayan;

    private String idBantuan;

    private List<Dokumen> dokumen;
}
