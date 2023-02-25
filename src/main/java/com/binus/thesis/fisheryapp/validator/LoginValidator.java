package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.dto.request.LoginRequestDto;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator extends BaseValidator<LoginRequestDto> {

    public void validate(BaseParameter<LoginRequestDto> parameter) {
        validate(parameter.getData());
    }

    public void validate(LoginRequestDto request) throws ApplicationException {
        notBlankorNull(request.getUsername(), "username");
        notBlankorNull(request.getUsername(), "password");
    }
}
