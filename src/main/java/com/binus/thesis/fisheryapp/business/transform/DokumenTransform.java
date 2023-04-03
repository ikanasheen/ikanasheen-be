package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.dto.response.ResponseDokumen;
import com.binus.thesis.fisheryapp.business.model.Dokumen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DokumenTransform {

    @Named("buildResponseDokumen")
    ResponseDokumen buildResponseDokumen(Dokumen data);

    @Named("buildDokumenEntity")
    @Mapping(target = "idDokumen", source = "idDokumen")
    @Mapping(target = "namaDokumen", source = "namaDokumen")
    @Mapping(target = "namaService", source = "namaService")
    @Mapping(target = "url", source = "url")
    Dokumen buildDokumenEntity(String idDokumen, String namaDokumen, String namaService, String url);
}
