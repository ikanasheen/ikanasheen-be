package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.dto.request.RequestLogin;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator extends BaseValidator<RequestLogin> {

    public void validate(BaseParameter<RequestLogin> parameter) {
        validate(parameter.getData());
    }

    public void validate(RequestLogin request) throws ApplicationException {
        notBlankorNull(request.getUsername(), "Username");
        notBlankorNull(request.getUsername(), "Password");
    }
}
