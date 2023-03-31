package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "id_admin")
    private String idAdmin;

    @Column(name = "id_user")
    private String idUser;

    @Column(name = "no_telepon")
    private String noTelepon;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private User user;
}
