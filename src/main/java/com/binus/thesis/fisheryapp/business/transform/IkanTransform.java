package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.model.Ikan;
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
    @Mapping(target = "hargaDasar", source = "hargaDasar")
    Ikan updateIkantoEntity(@MappingTarget Ikan ikanRepo, Ikan ikan);
}
