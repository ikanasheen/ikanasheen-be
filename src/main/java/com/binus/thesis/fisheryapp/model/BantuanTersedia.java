package com.binus.thesis.fisheryapp.model;

import com.binus.thesis.fisheryapp.enums.JenisBantuanEnum;
import com.binus.thesis.fisheryapp.enums.StatusBantuanEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bantuan_tersedia")
public class BantuanTersedia {

    @Id
    @Column(name = "id_bantuan")
    private String idBantuan;

    @Column(name = "nama_bantuan")
    private String namaBantuan;

    @Column(name = "jenis_bantuan")
    private JenisBantuanEnum jenisBantuan;

    @Column(name = "kuota")
    private int kuota;

    @Column(name = "format_proposal")
    private String formatProposal;

    @Column(name = "status_bantuan")
    private StatusBantuanEnum statusBantuan;
}
