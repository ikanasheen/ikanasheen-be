package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateTopik;
import com.binus.thesis.fisheryapp.business.model.Topik;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TopikTransform {

    @Named("generateId")
    default Integer generateId(Integer count) {
        return count+1;
    }

    @Named("createTopiktoEntity")
    @Mapping(target = "idTopik", expression = "java(generateId(count))")
    @Mapping(target = "namaTopik", source = "request.namaTopik")
    @Mapping(target = "deskripsi", expression = "java(request.getDeskripsi() == null || request.getDeskripsi().isEmpty() ? request.getDeskripsi() : request.getDeskripsi())")
    Topik createTopiktoEntity(RequestCreateTopik request, Integer count);

    @Named("updateTopiktoEntity")
    @Mapping(target = "idTopik", source = "idTopik")
    @Mapping(target = "namaTopik", expression = "java(request.getNamaTopik() == null || request.getNamaTopik().isEmpty() ? request.getNamaTopik() : request.getNamaTopik())")
    @Mapping(target = "deskripsi", expression = "java(request.getDeskripsi() == null || request.getDeskripsi().isEmpty() ? request.getDeskripsi() : request.getDeskripsi())")
    Topik updateTopiktoEntity(@MappingTarget Topik topik, Topik request);
}
