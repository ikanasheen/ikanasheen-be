package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

@Data
public class ResponseFAQ {

    private String idFaq;

    private int idTopik;

    private String pertanyaan;

    private String jawaban;

    private String tanggalDibuat;

    private String tanggalDiubah;

    private ResponseTopik topik;
}
