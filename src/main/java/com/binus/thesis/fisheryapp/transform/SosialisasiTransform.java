package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.model.Sosialisasi;
import com.binus.thesis.fisheryapp.model.Sosialisasi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface SosialisasiTransform {

    @Named("createSosialisasitoEntity")
    @Mapping(target = "idSosialisasi", source = "idSosialisasi")
    @Mapping(target = "tanggalDibuat", source = "date")
    Sosialisasi createSosialisasitoEntity(@MappingTarget Sosialisasi sosialisasi, String idSosialisasi, LocalDate date);

    @Named("updateSosialisasitoEntity")
    @Mapping(target = "idSosialisasi", source = "idSosialisasi")
    @Mapping(target = "judul", expression = "java(sosialisasi.getJudul() == null || sosialisasi.getJudul().isEmpty() ? sosialisasiRepo.getJudul() : sosialisasi.getJudul())")
    @Mapping(target = "jenisKonten", expression = "java(sosialisasi.getJenisKonten() == null ? sosialisasiRepo.getJenisKonten() : sosialisasi.getJenisKonten())")
    @Mapping(target = "konten", expression = "java(sosialisasi.getKonten() == null || sosialisasi.getKonten().isEmpty() ? sosialisasiRepo.getKonten() : sosialisasi.getKonten())")
    @Mapping(target = "status", expression = "java(sosialisasi.getStatus() == null ? sosialisasiRepo.getStatus() : sosialisasi.getStatus())")
    @Mapping(target = "tanggalDibuat", expression = "java(sosialisasi.getTanggalDibuat() == null ? sosialisasiRepo.getTanggalDibuat() : sosialisasi.getTanggalDibuat())")
    @Mapping(target = "tanggalDiubah", expression = "java(LocalDate.now().toString())")
    Sosialisasi updateSosialisasitoEntity(@MappingTarget Sosialisasi sosialisasiRepo, Sosialisasi sosialisasi);
}
