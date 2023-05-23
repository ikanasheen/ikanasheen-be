package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pengaduan")
public class Pengaduan {

    @Id
    @Column(name = "id_pengaduan")
    private String idPengaduan;

    @Column(name = "id_nelayan")
    private String idNelayan;

    @Column(name = "no_telepon")
    private String noTelepon;

    @Column(name = "email")
    private String email;

    @Column(name = "aduan")
    private String aduan;

    @Column(name = "penanganan")
    private String penanganan;

    @Column(name = "status")
    private String status;

    @Column(name = "tanggal_pengaduan")
    private String tanggalPengaduan;

    @Column(name = "tanggal_penanganan")
    private String tanggalPenanganan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nelayan", referencedColumnName = "id_nelayan", insertable = false, updatable = false)
    private Nelayan nelayan;
}
