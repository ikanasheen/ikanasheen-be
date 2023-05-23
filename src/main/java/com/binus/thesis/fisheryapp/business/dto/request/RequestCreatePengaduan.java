package com.binus.thesis.fisheryapp.business.dto.request;

import com.binus.thesis.fisheryapp.business.dto.response.ResponseNelayan;
import lombok.Data;

@Data
public class RequestCreatePengaduan {

    private String idUserNelayan;

    private String noTelepon;

    private String email;

    private String aduan;
}
