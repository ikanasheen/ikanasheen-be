package com.binus.thesis.fisheryapp.business.dto.response;

import lombok.Data;

@Data
public class ResponseDokumen {

    private Integer id;

    private String fileName;

    private String originalName;

    private String fileExtension;

    private String fileSize;

    private String url;

    private String status;

    private String createdBy;

    private String createdOn;
}
