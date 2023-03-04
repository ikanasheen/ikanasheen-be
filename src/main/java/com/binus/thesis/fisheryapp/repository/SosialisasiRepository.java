package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.Sosialisasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SosialisasiRepository extends JpaRepository<Sosialisasi, String>, JpaSpecificationExecutor<Sosialisasi> {

}
