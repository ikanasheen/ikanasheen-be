package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.model.BantuanTersedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BantuanTersediaTransform {

    @Named("createBantuantoEntity")
    @Mapping(target = "idBantuan", source = "idBantuan")
    @Mapping(target = "statusBantuan", expression = "java(\"ACTIVE\")")
    BantuanTersedia createBantuantoEntity(@MappingTarget BantuanTersedia bantuan, String idBantuan);

    @Named("updateBantuantoEntity")
    @Mapping(target = "idBantuan", source = "idBantuan")
    @Mapping(target = "namaBantuan", expression = "java(bantuan.getNamaBantuan() == null || bantuan.getNamaBantuan().isEmpty() ? bantuanRepo.getNamaBantuan() : bantuan.getNamaBantuan())")
    @Mapping(target = "jenisBantuan", expression = "java(bantuan.getJenisBantuan() == null || bantuan.getJenisBantuan().isEmpty() ? bantuanRepo.getJenisBantuan() : bantuan.getJenisBantuan())")
    @Mapping(target = "formatProposal", expression = "java(bantuan.getFormatProposal() == null || bantuan.getFormatProposal().isEmpty() ? bantuanRepo.getFormatProposal() : bantuan.getFormatProposal())")
    @Mapping(target = "statusBantuan", expression = "java(bantuan.getStatusBantuan() == null || bantuan.getStatusBantuan().isEmpty() ? bantuanRepo.getStatusBantuan() : bantuan.getStatusBantuan())")
    @Mapping(target = "kuota", expression = "java(bantuan.getKuota() == null ? bantuanRepo.getKuota() : bantuan.getKuota())")
    BantuanTersedia updateBantuantoEntity(@MappingTarget BantuanTersedia bantuanRepo, BantuanTersedia bantuan);
}
