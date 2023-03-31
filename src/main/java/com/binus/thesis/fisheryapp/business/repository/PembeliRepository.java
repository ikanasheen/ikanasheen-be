package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.Pembeli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembeliRepository extends JpaRepository<Pembeli, Integer> {

    Pembeli findByIdUser(String idUser);
}
