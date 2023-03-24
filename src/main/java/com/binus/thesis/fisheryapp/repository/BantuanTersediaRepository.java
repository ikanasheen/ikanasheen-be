package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.BantuanTersedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BantuanTersediaRepository extends JpaRepository<BantuanTersedia, String>, JpaSpecificationExecutor<BantuanTersedia> {

}
