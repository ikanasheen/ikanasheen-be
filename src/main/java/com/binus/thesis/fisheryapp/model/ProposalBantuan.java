package com.binus.thesis.fisheryapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "proposal_bantuan")
public class ProposalBantuan {

    @Id
    @Column(name = "id_proposal_bantuan")
    private String idProposalBantuan;

    @Column(name = "id_nelayan")
    private String idNelayan;

    @Column(name = "tanggal_pengajuan")
    private LocalDate tanggalPengajuan;

    @Column(name = "tanggal_disetujui")
    private LocalDate tanggalDisetujui;

    @Column(name = "tanggal_ditolak")
    private LocalDate tanggalDitolak;

    @Column(name = "file")
    private String file;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nelayan", referencedColumnName = "id_nelayan", insertable = false, updatable = false)
    private Nelayan nelayan;
}
