package com.binus.thesis.fisheryapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pengembangan_diri")
public class PengembanganDiri {

    @Id
    @Column(name = "id_pengembangan_diri")
    private String idPengembanganDiri;

    @Column(name = "topik")
    private String topik;

    @Column(name = "tanggal_pelaksanaan")
    private LocalDateTime tanggalPelaksanaan;

    @Column(name = "lokasi")
    private String lokasi;

    @Column(name = "deskripsi")
    private String deskripsi;
}
