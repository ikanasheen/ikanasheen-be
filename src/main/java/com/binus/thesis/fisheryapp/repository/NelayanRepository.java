package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.Nelayan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NelayanRepository extends JpaRepository<Nelayan, String>, JpaSpecificationExecutor<Nelayan> {
}
