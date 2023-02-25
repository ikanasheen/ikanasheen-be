package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.model.Role;
import com.binus.thesis.fisheryapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role findById(int idRole) {
        return repository.findById(idRole).get();
    }

}
