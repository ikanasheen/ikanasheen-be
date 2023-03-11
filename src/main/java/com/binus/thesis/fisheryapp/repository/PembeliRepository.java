package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Pembeli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembeliRepository extends JpaRepository<Pembeli, Integer> {

    Pembeli findByIdUser(String idUser);
}
