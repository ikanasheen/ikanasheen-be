package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "status")
    private String statusBantuan;

    @Column(name = "id_dokumen")
    private Integer idDokumen;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen", referencedColumnName = "id", insertable = false, updatable = false)
    private Dokumen dokumen;
}
