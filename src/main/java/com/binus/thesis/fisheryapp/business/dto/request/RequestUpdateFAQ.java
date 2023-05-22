package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

@Data
public class RequestUpdateFAQ {

    private String idFaq;

    private int idTopik;

    private String pertanyaan;

    private String jawaban;
}
