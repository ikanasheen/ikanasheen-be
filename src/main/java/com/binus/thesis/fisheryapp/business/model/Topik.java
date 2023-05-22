package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topik")
public class Topik {

    @Id
    @Column(name = "id_topik")
    private int idTopik;

    @Column(name = "nama_topik")
    private String namaTopik;

    @Column(name = "deskripsi")
    private String deskripsi;
}
