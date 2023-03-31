package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
