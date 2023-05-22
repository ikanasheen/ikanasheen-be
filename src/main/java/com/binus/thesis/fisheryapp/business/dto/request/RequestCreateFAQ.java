package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

@Data
public class RequestCreateFAQ {

    private int idTopik;

    private String pertanyaan;

    private String jawaban;
}
