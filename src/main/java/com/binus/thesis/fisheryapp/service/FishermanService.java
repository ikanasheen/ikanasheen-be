package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.dto.request.FishermanRequestDto;
import com.binus.thesis.fisheryapp.model.Fisherman;
import com.binus.thesis.fisheryapp.repository.FishermanRepository;
import com.binus.thesis.fisheryapp.transform.FishermanTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FishermanService{

    private final FishermanRepository repository;

    private final FishermanTransform transform;

    public Fisherman register(FishermanRequestDto requestDto) {
        return repository.save(
                transform.buildFisherman(requestDto)
        );
    }

    public List<Fisherman> list() {
        return repository.findAll();
    }
}
