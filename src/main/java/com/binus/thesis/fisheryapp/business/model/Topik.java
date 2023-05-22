package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topik")
public class Topik {

    @Id
    @Column(name = "id_topik")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTopik;

    @Column(name = "nama_topik")
    private String namaTopik;
}
