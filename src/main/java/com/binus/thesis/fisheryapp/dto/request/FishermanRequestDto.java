package com.binus.thesis.fisheryapp.dto.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
public class FishermanRequestDto {

    @NotEmpty(message = "cannot be null")
    private String name;

    @NotEmpty(message = "cannot be null")
    private String address;
}
