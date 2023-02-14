package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.request.FishermanRequestDto;
import com.binus.thesis.fisheryapp.model.Fisherman;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FishermanTransform {

    @Named("buildFisherman")
    Fisherman buildFisherman(FishermanRequestDto requestDto);
}
