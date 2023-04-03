package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dokumen")
public class Dokumen {

    @Id
    @Column(name = "id_dokumen")
    private String idDokumen;

    @Column(name = "nama_dokumen")
    private String namaDokumen;

    @Column(name = "nama_service")
    private String namaService;

    @Column(name = "url")
    private String url;
}
