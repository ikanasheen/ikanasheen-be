package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.response.ResponseUser;
import com.binus.thesis.fisheryapp.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.dto.request.RegisterNelayanRequestDto;
import com.binus.thesis.fisheryapp.dto.request.RegisterPembeliRequestDto;
import com.binus.thesis.fisheryapp.model.Role;
import com.binus.thesis.fisheryapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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
    User regNelayanReqtoUser(RegisterNelayanRequestDto requestDto, String idUser, StatusUserEnum status, Role role);

    @Named("regPembeliReqtoUser")
    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "username", source = "requestDto.username")
    @Mapping(target = "password", source = "requestDto.password")
    @Mapping(target = "idRole", source = "requestDto.idRole")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "role", source = "role")
    User regPembeliReqtoUser(RegisterPembeliRequestDto requestDto, String idUser, StatusUserEnum status, Role role);

    @Named("buildResponseUser")
    @Mapping(target = "role", source = "role", qualifiedByName = "buildResponseRole")
    ResponseUser buildResponseUser(User data);
}
