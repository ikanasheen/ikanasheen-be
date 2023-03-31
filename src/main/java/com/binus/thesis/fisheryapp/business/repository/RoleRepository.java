package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
