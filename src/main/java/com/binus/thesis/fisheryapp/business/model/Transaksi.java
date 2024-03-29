package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @Column(name = "id_transaksi")
    private String idTransaksi;

    @Column(name = "id_pembeli")
    private String idPembeli;

    @Column(name = "id_nelayan")
    private String idNelayan;

    @Column(name = "id_ikan")
    private String idIkan;

    @Column(name = "jumlah")
    private String jumlah;

    @Column(name = "harga_awal")
    private String hargaAwal;

    @Column(name = "harga_nego")
    private String hargaNego;

    @Column(name = "harga_akhir")
    private String hargaAkhir;

    @Column(name = "tanggal_dibutuhkan")
    private String tanggalDibutuhkan;

    @Column(name = "alamat")
    private String alamatPembeli;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "status")
    private String status;

    @Column(name = "tanggal_diajukan")
    private String tanggalDiajukan;

    @Column(name = "tanggal_diproses")
    private String tanggalDiproses;

    @Column(name = "tanggal_selesai")
    private String tanggalSelesai;

    @Column(name = "opsi_pengiriman")
    private String opsiPengiriman;

    @Column(name = "catatan_pengiriman")
    private String catatanPengiriman;

    @Column(name = "tanggal_dikirim")
    private String tanggalDikirim;

    @Column(name = "tanggal_siap_diambil")
    private String tanggalSiapDiambil;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pembeli", referencedColumnName = "id_pembeli", insertable = false, updatable = false)
    private Pembeli pembeli;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ikan", referencedColumnName = "id_ikan", insertable = false, updatable = false)
    private Ikan ikan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nelayan", referencedColumnName = "id_nelayan", insertable = false, updatable = false)
    private Nelayan nelayan;
}
