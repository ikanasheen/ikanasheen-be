package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.BantuanTersedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BantuanTersediaRepository extends JpaRepository<BantuanTersedia, String>, JpaSpecificationExecutor<BantuanTersedia> {

}
