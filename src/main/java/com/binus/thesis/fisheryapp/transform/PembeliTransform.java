package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.request.RegisterPembeliRequestDto;
import com.binus.thesis.fisheryapp.model.Pembeli;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PembeliTransform {

    @Named("regPembeliReqtoNelayan")
    @Mapping(target = "idPembeli", source = "idPembeli")
    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "namaLengkap", source = "requestDto.namaLengkap")
    @Mapping(target = "alamat", source = "requestDto.alamat")
    @Mapping(target = "noTelepon", source = "requestDto.noTelepon")
    @Mapping(target = "email", source = "requestDto.email")
    Pembeli regPembelitoPembeli(RegisterPembeliRequestDto requestDto, String idPembeli, String idUser);
}
