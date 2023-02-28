package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.request.RegisterNelayanRequestDto;
import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NelayanTransform {

    @Named("regNelayanReqtoNelayan")
    @Mapping(target = "idNelayan", source = "idNelayan")
    @Mapping(target = "idUser", source = "user.idUser")
    @Mapping(target = "namaLengkap", source = "requestDto.namaLengkap")
    @Mapping(target = "gender", source = "requestDto.gender")
    @Mapping(target = "tanggalLahir", source = "requestDto.tanggalLahir")
    @Mapping(target = "alamat", source = "requestDto.alamat")
    @Mapping(target = "kecamatan", source = "requestDto.kecamatan")
    @Mapping(target = "kelurahanDesa", source = "requestDto.kelurahanDesa")
    @Mapping(target = "noTelepon", source = "requestDto.noTelepon")
    @Mapping(target = "email", source = "requestDto.email")
    @Mapping(target = "user", source = "user")
    Nelayan regNelayantoNelayan(RegisterNelayanRequestDto requestDto, String idNelayan, User user);
}
