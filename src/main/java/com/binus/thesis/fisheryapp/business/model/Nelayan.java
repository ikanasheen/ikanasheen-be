package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "nelayan")
public class Nelayan {

    @Id
    @Column(name = "id_nelayan")
    private String idNelayan;

    @Column(name = "id_user")
    private String idUser;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "gender")
    private String gender;

    @Column(name = "tanggal_lahir")
    private String tanggalLahir;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "kecamatan_id")
    private String kecamatanId;

    @Column(name = "kecamatan")
    private String kecamatan;

    @Column(name = "kelurahan_desa")
    private String kelurahanDesa;

    @Column(name = "no_telepon")
    private String noTelepon;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private User user;
}
