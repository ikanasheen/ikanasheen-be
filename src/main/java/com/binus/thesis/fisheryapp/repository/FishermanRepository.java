package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.Fisherman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Integer> {
}
