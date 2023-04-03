package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "proposal_bantuan")
public class ProposalBantuan {

    @Id
    @Column(name = "id_proposal_bantuan")
    private String idProposalBantuan;

    @Column(name = "id_nelayan")
    private String idNelayan;

    @Column(name = "id_bantuan")
    private String idBantuan;

    @Column(name = "tanggal_diajukan")
    private String tanggalDiajukan;

    @Column(name = "tanggal_disetujui")
    private String tanggalDisetujui;

    @Column(name = "tanggal_ditolak")
    private String tanggalDitolak;

    @Column(name = "status")
    private String statusProposal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nelayan", referencedColumnName = "id_nelayan", insertable = false, updatable = false)
    private Nelayan nelayan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bantuan", referencedColumnName = "id_bantuan", insertable = false, updatable = false)
    private BantuanTersedia bantuan;

    @Column(name = "id_dokumen")
    private String idDokumen;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen", referencedColumnName = "id_dokumen", insertable = false, updatable = false)
    private Dokumen dokumen;
}
