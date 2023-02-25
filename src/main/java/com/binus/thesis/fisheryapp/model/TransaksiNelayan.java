package com.binus.thesis.fisheryapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaksi_nelayan")
public class TransaksiNelayan {

    @Id
    @Column(name = "id_transaksi_nelayan")
    private String idTransaksiNelayan;

    @Column(name = "id_nelayan")
    private String idNelayan;

    @Column(name = "id_transaksi")
    private String idTransaksi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nelayan", referencedColumnName = "id_nelayan", insertable = false, updatable = false)
    private Nelayan nelayan;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaksi", referencedColumnName = "id_transaksi", insertable = false, updatable = false)
    private Transaksi transaksi;
}
