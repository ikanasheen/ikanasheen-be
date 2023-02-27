package com.binus.thesis.fisheryapp.model;

import com.binus.thesis.fisheryapp.enums.StatusUserEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id_user")
    private String idUser;

    @Column(name = "id_role")
    private String idRole;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusUserEnum status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", insertable = false, updatable = false)
    private Role role;
}
