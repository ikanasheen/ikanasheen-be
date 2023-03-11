package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.request.RequestRegisterNelayan;
import com.binus.thesis.fisheryapp.dto.response.ResponseNelayan;
import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UserTransform.class,
})
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
    Nelayan regNelayantoNelayan(RequestRegisterNelayan requestDto, String idNelayan, User user);

    @Named("updateNelayantoEntity")
    @Mapping(target = "idNelayan", source = "idNelayan")
    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "namaLengkap", expression = "java(nelayan.getNamaLengkap() == null || nelayan.getNamaLengkap().isEmpty() ? nelayanRepo.getNamaLengkap() : nelayan.getNamaLengkap())")
    @Mapping(target = "gender", expression = "java(nelayan.getGender() == null || nelayan.getGender().isEmpty() ? nelayanRepo.getGender() : nelayan.getGender())")
    @Mapping(target = "tanggalLahir", expression = "java(nelayan.getTanggalLahir() == null ? nelayanRepo.getTanggalLahir() : nelayan.getTanggalLahir())")
    @Mapping(target = "alamat", expression = "java(nelayan.getAlamat() == null || nelayan.getAlamat().isEmpty() ? nelayanRepo.getAlamat() : nelayan.getAlamat())")
    @Mapping(target = "kecamatan", expression = "java(nelayan.getKecamatan() == null || nelayan.getKecamatan().isEmpty() ? nelayanRepo.getKecamatan() : nelayan.getKecamatan())")
    @Mapping(target = "kelurahanDesa", expression = "java(nelayan.getKelurahanDesa() == null || nelayan.getKelurahanDesa().isEmpty() ? nelayanRepo.getKelurahanDesa() : nelayan.getKelurahanDesa())")
    @Mapping(target = "noTelepon", expression = "java(nelayan.getNoTelepon() == null || nelayan.getNoTelepon().isEmpty() ? nelayanRepo.getNoTelepon() : nelayan.getNoTelepon())")
    @Mapping(target = "user", source = "user")
    Nelayan updateNelayantoEntity(@MappingTarget Nelayan nelayanRepo, Nelayan nelayan);

    @Named("buildResponseNelayan")
    @Mapping(target = "user", source = "user", qualifiedByName = "buildResponseUser")
    ResponseNelayan buildResponseNelayan(Nelayan nelayan);

    @Named("buildResponseNelayanList")
    @IterableMapping(qualifiedByName = "buildResponseNelayan")
    List<ResponseNelayan> buildResponseNelayanList(List<Nelayan> entities);
}
