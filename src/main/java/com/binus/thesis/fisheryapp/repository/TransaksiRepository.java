package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.Sosialisasi;
import com.binus.thesis.fisheryapp.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, String>, JpaSpecificationExecutor<Transaksi> {

}
