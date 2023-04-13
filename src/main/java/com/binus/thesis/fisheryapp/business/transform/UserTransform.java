package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.dto.request.RequestChangePassword;
import com.binus.thesis.fisheryapp.business.dto.request.RequestRegisterNelayan;
import com.binus.thesis.fisheryapp.business.dto.request.RequestUpdateUser;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseUser;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseUserProfile;
import com.binus.thesis.fisheryapp.business.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.business.dto.request.RequestRegisterPembeli;
import com.binus.thesis.fisheryapp.business.model.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        RoleTransform.class
})
public interface UserTransform {

    @Named("regNelayanReqtoUser")
    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "username", source = "requestDto.username")
    @Mapping(target = "password", source = "requestDto.password")
    @Mapping(target = "idRole", source = "requestDto.idRole")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "role", source = "role")
    User regNelayanReqtoUser(RequestRegisterNelayan requestDto, String idUser, StatusUserEnum status, Role role);

    @Named("regPembeliReqtoUser")
    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "username", source = "requestDto.username")
    @Mapping(target = "password", source = "requestDto.password")
    @Mapping(target = "idRole", source = "requestDto.idRole")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "role", source = "role")
    User regPembeliReqtoUser(RequestRegisterPembeli requestDto, String idUser, StatusUserEnum status, Role role);

    @Named("buildResponseUser")
    @Mapping(target = "role", source = "role", qualifiedByName = "buildResponseRole")
    ResponseUser buildResponseUser(User data);

    @Named("buildResponseUserList")
    @IterableMapping(qualifiedByName = "buildResponseUser")
    List<ResponseUser> buildResponseUserList(List<User> data);

    @Named("updateUsertoEntity")
    @Mapping(target = "idUser", source = "request.idUser")
    @Mapping(target = "username", expression = "java(request.getUsername() == null || request.getUsername().isEmpty() ? userRepo.getUsername() : request.getUsername())")
    @Mapping(target = "status", expression = "java(request.getStatus() == null || request.getStatus().isEmpty() ? userRepo.getStatus() : request.getStatus())")
    User updateUsertoEntity(@MappingTarget User userRepo, RequestUpdateUser request);

    @Named("changePasswordtoEntity")
    @Mapping(target = "password", source = "request.password")
    User changePasswordtoEntity(@MappingTarget User userRepo, RequestChangePassword request);

    @Named("adminProfile")
    @Mapping(target = "idUser", source = "user.idUser")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "noTelepon", source = "admin.noTelepon")
    @Mapping(target = "email", source = "admin.email")
    ResponseUserProfile adminProfile(User user, Admin admin);

    @Named("dinasProfile")
    @Mapping(target = "idUser", source = "user.idUser")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "noTelepon", source = "dinas.noTelepon")
    @Mapping(target = "email", source = "dinas.email")
    ResponseUserProfile dinasProfile(User user, DinasPerikanan dinas);

    @Named("nelayanProfile")
    @Mapping(target = "idUser", source = "user.idUser")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "noTelepon", source = "nelayan.noTelepon")
    @Mapping(target = "email", source = "nelayan.email")
    @Mapping(target = "namaLengkap", source = "nelayan.namaLengkap")
    @Mapping(target = "alamat", source = "nelayan.alamat")
    @Mapping(target = "kecamatanId", source = "nelayan.kecamatanId")
    @Mapping(target = "kecamatan", source = "nelayan.kecamatan")
    @Mapping(target = "kelurahanDesa", source = "nelayan.kelurahanDesa")
    ResponseUserProfile nelayanProfile(User user, Nelayan nelayan);

    @Named("pembeliProfile")
    @Mapping(target = "idUser", source = "user.idUser")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "noTelepon", source = "pembeli.noTelepon")
    @Mapping(target = "email", source = "pembeli.email")
    @Mapping(target = "namaLengkap", source = "pembeli.namaLengkap")
    @Mapping(target = "alamat", source = "pembeli.alamat")
    ResponseUserProfile pembeliProfile(User user, Pembeli pembeli);
}
