package com.binus.thesis.fisheryapp.model;

import com.binus.thesis.fisheryapp.enums.GenderEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ikan")
public class Ikan {

    @Id
    @Column(name = "id_ikan")
    private String idIkan;

    @Column(name = "nama_ikan")
    private String namaIkan;

    @Column(name = "deskripsi")
    private String deskripsi;
}
