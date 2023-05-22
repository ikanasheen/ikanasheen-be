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

    @Column(name = "id_topik")
    private int idTopik;

    @Column(name = "id_nelayan")
    private String idNelayan;

    @Column(name = "aduan")
    private String aduan;

    @Column(name = "tanggapan")
    private String tanggapan;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topik", referencedColumnName = "id_topik", insertable = false, updatable = false)
    private Topik topik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nelayan", referencedColumnName = "id_nelayan", insertable = false, updatable = false)
    private Nelayan nelayan;
}
