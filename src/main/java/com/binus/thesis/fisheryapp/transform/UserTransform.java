package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.dto.request.RegisterNelayanRequestDto;
import com.binus.thesis.fisheryapp.dto.request.RegisterPembeliRequestDto;
import com.binus.thesis.fisheryapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserTransform {

    @Named("regNelayanReqtoUser")
    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "username", source = "requestDto.username")
    @Mapping(target = "password", source = "requestDto.password")
    @Mapping(target = "idRole", source = "requestDto.idRole")
    @Mapping(target = "status", source = "status")
    User regNelayanReqtoUser(RegisterNelayanRequestDto requestDto, String idUser, StatusUserEnum status);

    @Named("regPembeliReqtoUser")
    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "username", source = "requestDto.username")
    @Mapping(target = "password", source = "requestDto.password")
    @Mapping(target = "idRole", source = "requestDto.idRole")
    @Mapping(target = "status", source = "status")
    User regPembeliReqtoUser(RegisterPembeliRequestDto requestDto, String idUser, StatusUserEnum status);
}
