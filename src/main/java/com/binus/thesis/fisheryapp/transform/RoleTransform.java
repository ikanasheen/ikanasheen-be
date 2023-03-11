package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.response.ResponseRole;
import com.binus.thesis.fisheryapp.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleTransform {

    @Named("buildResponseRole")
    ResponseRole buildResponseRole(Role data);
}
