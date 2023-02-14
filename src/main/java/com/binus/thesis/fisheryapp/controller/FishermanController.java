package com.binus.thesis.fisheryapp.controller;

import com.binus.thesis.fisheryapp.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.dto.request.FishermanRequestDto;
import com.binus.thesis.fisheryapp.model.Fisherman;
import com.binus.thesis.fisheryapp.service.FishermanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(EndpointAPI.FISHERMAN)
@RequiredArgsConstructor
public class FishermanController {

    private final FishermanService service;

    @PostMapping("/register")
    public ResponseEntity<Fisherman> register(@Valid @RequestBody FishermanRequestDto requestDto) {
        return ResponseEntity.ok(service.register(requestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Fisherman>> list() {
        return ResponseEntity.ok(service.list());
    }
}
