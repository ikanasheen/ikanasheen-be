package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.Topik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TopikRepository extends JpaRepository<Topik, Integer>, JpaSpecificationExecutor<Topik> {
    Topik findByNamaTopik(String namaTopik);
}
