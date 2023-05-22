package com.binus.thesis.fisheryapp.business.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "faq")
public class FAQ {

    @Id
    @Column(name = "id_faq")
    private String idFaq;

    @Column(name = "id_topik")
    private int idTopik;

    @Column(name = "pertanyaan")
    private String pertanyaan;

    @Column(name = "jawaban")
    private String jawaban;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topik", referencedColumnName = "id_topik", insertable = false, updatable = false)
    private Topik topik;
}
