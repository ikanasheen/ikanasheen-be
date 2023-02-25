package com.binus.thesis.fisheryapp.model;

import com.binus.thesis.fisheryapp.enums.EkspedisiEnum;
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

    @Column(name = "nama_ikan")
    private String namaIkan;

    @Column(name = "jumlah")
    private int jumlah;

    @Column(name = "id_pembeli")
    private String idPembeli;

    @Column(name = "ekspedisi")
    private EkspedisiEnum ekspedisi;

    @Column(name = "tanggal_input")
    private LocalDate tanggalInput;

    @Column(name = "tanggal_terima")
    private LocalDate tanggalTerima;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "status")
    private StatusTransaksiEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pembeli", referencedColumnName = "id_pembeli", insertable = false, updatable = false)
    private Pembeli pembeli;
}
