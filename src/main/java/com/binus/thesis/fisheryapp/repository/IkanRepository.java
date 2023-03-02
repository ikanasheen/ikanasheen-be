package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.Ikan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IkanRepository extends JpaRepository<Ikan, String> {

}
