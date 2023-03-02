package com.binus.thesis.fisheryapp.model;

import com.binus.thesis.fisheryapp.enums.StatusTransaksiEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @Column(name = "id_transaksi")
    private String idTransaksi;

    @Column(name = "id_pembeli")
    private String idPembeli;

    @Column(name = "id_ikan")
    private String idIkan;

    @Column(name = "jumlah")
    private int jumlah;

    @Column(name = "harga_diajukan")
    private int hargaDiajukan;

    @Column(name = "harga_nego")
    private int hargaNego;

    @Column(name = "harga_akhir")
    private int hargaAkhir;

    @Column(name = "tanggal_dibutuhkan")
    private LocalDate tanggalDibutuhkan;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "status")
    private StatusTransaksiEnum statusTransaksi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pembeli", referencedColumnName = "id_pembeli", insertable = false, updatable = false)
    private Pembeli pembeli;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ikan", referencedColumnName = "id_ikan", insertable = false, updatable = false)
    private Ikan ikan;
}
