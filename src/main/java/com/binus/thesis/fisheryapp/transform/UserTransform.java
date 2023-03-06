package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.request.UpdateUserRequestDto;
import com.binus.thesis.fisheryapp.dto.response.ResponseUser;
import com.binus.thesis.fisheryapp.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.dto.request.RegisterNelayanRequestDto;
import com.binus.thesis.fisheryapp.dto.request.RegisterPembeliRequestDto;
import com.binus.thesis.fisheryapp.model.Role;
import com.binus.thesis.fisheryapp.model.User;
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

    @Named("buildResponseUserList")
    @IterableMapping(qualifiedByName = "buildResponseUser")
    List<ResponseUser> buildResponseUserList(List<User> data);

    @Named("toUserEntity")
    @Mapping(target = "username", expression = "java(request.getUsername() == null || request.getUsername().isEmpty() ? userRepo.getUsername() : request.getUsername())")
    @Mapping(target = "password", expression = "java(request.getPassword() == null || request.getPassword().isEmpty() ? userRepo.getPassword() : request.getPassword())")
    @Mapping(target = "status", expression = "java(request.getStatus() == null || request.getStatus().isEmpty() ? userRepo.getStatus() : request.getStatus())")
    User updateUsertoEntity(@MappingTarget User userRepo, UpdateUserRequestDto request);
}
