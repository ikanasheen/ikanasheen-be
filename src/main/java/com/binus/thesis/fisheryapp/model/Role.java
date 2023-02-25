package com.binus.thesis.fisheryapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    @Column(name = "nama_role")
    private String namaRole;

    @Column(name = "deskripsi")
    private String deskripsi;
}
