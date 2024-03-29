package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ikan")
public class Ikan {

    @Id
    @Column(name = "id_ikan")
    private String idIkan;

    @Column(name = "nama_ikan")
    private String namaIkan;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "ukuran")
    private String ukuran;

    @Column(name = "harga_dasar")
    private String hargaDasar;
}
