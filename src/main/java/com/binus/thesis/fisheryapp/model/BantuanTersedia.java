package com.binus.thesis.fisheryapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bantuan_tersedia")
public class BantuanTersedia {

    @Id
    @Column(name = "id_bantuan")
    private String idBantuan;

    @Column(name = "nama_bantuan")
    private String namaBantuan;

    @Column(name = "jenis_bantuan")
    private String jenisBantuan;

    @Column(name = "kuota")
    private String kuota;

    @Column(name = "kuota_tersisa")
    private String kuotaTersisa;

    @Column(name = "format_proposal")
    private String formatProposal;

    @Column(name = "status")
    private String statusBantuan;
}
