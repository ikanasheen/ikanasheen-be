package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
