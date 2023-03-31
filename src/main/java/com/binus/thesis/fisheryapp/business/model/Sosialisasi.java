package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "sosialisasi")
public class Sosialisasi {

    @Id
    @Column(name = "id_sosialisasi")
    private String idSosialisasi;

    @Column(name = "judul")
    private String judul;

    @Column(name = "jenis_konten")
    private String jenisKonten;

    @Column(name = "konten")
    private String konten;

    @Column(name = "status")
    private String status;

    @Column(name = "penulis")
    private String penulis;

    @Column(name = "tanggal_dibuat")
    private String tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private String tanggalDiubah;
}
