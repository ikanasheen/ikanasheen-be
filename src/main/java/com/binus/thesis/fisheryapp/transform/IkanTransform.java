package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.request.RegisterNelayanRequestDto;
import com.binus.thesis.fisheryapp.dto.request.RegisterPembeliRequestDto;
import com.binus.thesis.fisheryapp.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.model.Ikan;
import com.binus.thesis.fisheryapp.model.Role;
import com.binus.thesis.fisheryapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IkanTransform {

    @Named("createIkantoEntity")
    @Mapping(target = "idIkan", source = "idIkan")
    Ikan createIkantoEntity(@MappingTarget Ikan ikan, String idIkan);

    @Named("updateIkantoEntity")
    @Mapping(target = "idIkan", source = "idIkan")
    @Mapping(target = "namaIkan", expression = "java(ikan.getNamaIkan() == null || ikan.getNamaIkan().isEmpty() ? ikanRepo.getNamaIkan() : ikan.getNamaIkan())")
    @Mapping(target = "deskripsi", expression = "java(ikan.getDeskripsi() == null || ikan.getDeskripsi().isEmpty() ? ikanRepo.getDeskripsi() : ikan.getDeskripsi())")
    Ikan updateIkantoEntity(@MappingTarget Ikan ikanRepo, Ikan ikan);
}
