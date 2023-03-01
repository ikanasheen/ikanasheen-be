package com.binus.thesis.fisheryapp.model;

import com.binus.thesis.fisheryapp.enums.JenisKontenSosialisasiEnum;
import com.binus.thesis.fisheryapp.enums.StatusSosialisasiEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sosialisasi")
public class Sosialisasi {

    @Id
    @Column(name = "id_sosialisasi")
    private String idSosialisasi;

    @Column(name = "judul")
    private String judul;

    @Column(name = "jenis_konten")
    private JenisKontenSosialisasiEnum jenisKonten;

    @Column(name = "konten")
    private String konten;

    @Column(name = "status")
    private StatusSosialisasiEnum status;
}
