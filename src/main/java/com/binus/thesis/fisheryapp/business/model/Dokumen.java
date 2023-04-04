package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dokumen")
public class Dokumen {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "modul")
    private String modul;

    @Column(name = "url")
    private String url;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
