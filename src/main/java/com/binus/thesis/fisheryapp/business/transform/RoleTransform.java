package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.dto.response.ResponseRole;
import com.binus.thesis.fisheryapp.business.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleTransform {

    @Named("buildResponseRole")
    ResponseRole buildResponseRole(Role data);
}
