package com.binus.thesis.fisheryapp.repository;

import com.binus.thesis.fisheryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
