package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.Admin;
import com.binus.thesis.fisheryapp.business.model.DinasPerikanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DinasRepository extends JpaRepository<DinasPerikanan, String>, JpaSpecificationExecutor<DinasPerikanan> {

    DinasPerikanan findByIdUser(String idUser);
}
