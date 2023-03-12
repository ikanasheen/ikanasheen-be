package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;

@Data
public class RequestApproveNegoTransaksi {

    private String idTransaksi;

    private String isApprove;
}
