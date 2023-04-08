package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.business.model.DinasPerikanan;
import com.binus.thesis.fisheryapp.business.repository.AdminRepository;
import com.binus.thesis.fisheryapp.business.repository.DinasRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DinasService {

    private final DinasRepository repository;

    public DinasPerikanan findByIdUser(String idUser) {
        return repository.findByIdUser(idUser);
    }

}
