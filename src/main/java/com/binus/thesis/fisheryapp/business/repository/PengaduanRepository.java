package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.FAQ;
import com.binus.thesis.fisheryapp.business.model.Pengaduan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PengaduanRepository extends JpaRepository<Pengaduan, String>, JpaSpecificationExecutor<Pengaduan> {

}
