package com.binus.thesis.fisheryapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "file")
    private String file;

    @Column(name = "status")
    private String statusProposal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nelayan", referencedColumnName = "id_nelayan", insertable = false, updatable = false)
    private Nelayan nelayan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bantuan", referencedColumnName = "id_bantuan", insertable = false, updatable = false)
    private BantuanTersedia bantuan;
}
