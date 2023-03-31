package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.dto.request.RequestRegisterPembeli;
import com.binus.thesis.fisheryapp.business.model.Pembeli;
import com.binus.thesis.fisheryapp.business.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PembeliTransform {

    @Named("regPembeliReqtoPembeli")
    @Mapping(target = "idPembeli", source = "idPembeli")
    @Mapping(target = "idUser", source = "user.idUser")
    @Mapping(target = "namaLengkap", source = "requestDto.namaLengkap")
    @Mapping(target = "alamat", source = "requestDto.alamat")
    @Mapping(target = "noTelepon", source = "requestDto.noTelepon")
    @Mapping(target = "email", source = "requestDto.email")
    @Mapping(target = "user", source = "user")
    Pembeli regPembeliReqtoPembeli(RequestRegisterPembeli requestDto, String idPembeli, User user);

    @Named("toEntity")
    Pembeli toEntity(Pembeli pembeli);
}
