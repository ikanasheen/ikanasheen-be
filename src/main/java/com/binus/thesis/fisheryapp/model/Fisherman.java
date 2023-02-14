package com.binus.thesis.fisheryapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fisherman")
public class Fisherman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;
}
