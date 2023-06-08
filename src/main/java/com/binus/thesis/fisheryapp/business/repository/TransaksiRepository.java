package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, String>, JpaSpecificationExecutor<Transaksi> {

    List<Transaksi> findByStatusAndTanggalDiajukan(String status, String tanggalDiajukan);

    List<Transaksi> findByStatusAndTanggalDiproses(String status, String tanggalDiproses);

    List<Transaksi> findByStatusAndTanggalDikirim(String status, String tanggalDikirim);

    List<Transaksi> findByStatusAndTanggalSiapDiambil(String status, String tanggalSiapDiambil);

    List<Transaksi> findByStatusAndTanggalSelesai(String status, String tanggalSelesai);

    List<Transaksi> findByTanggalDiajukan(String tanggalDiajukan);

    List<Transaksi> findByTanggalDiproses(String tanggalDiproses);

    List<Transaksi> findByTanggalSelesai(String tanggalSelesai);

    List<Transaksi> findByIdNelayanNotNullAndTanggalDiprosesAndNelayanKecamatan(String tanggalDiajukan, String kecamatan);
}
