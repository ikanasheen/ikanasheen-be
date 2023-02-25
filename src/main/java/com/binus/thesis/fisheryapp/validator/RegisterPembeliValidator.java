package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.dto.request.RegisterPembeliRequestDto;
import org.springframework.stereotype.Component;

@Component
public class RegisterPembeliValidator extends BaseValidator<RegisterPembeliRequestDto> {

    public void validate(BaseParameter<RegisterPembeliRequestDto> parameter) {
        validate(parameter.getData());
    }

    public void validate(RegisterPembeliRequestDto request) throws ApplicationException {
        notNull(request.getIdRole(), "id role");
        notBlankorNull(request.getNamaLengkap(), "nama lengkap");
        notBlankorNull(request.getAlamat(), "alamat");
        notBlankorNull(request.getNoTelepon(), "no telepon");
        notBlankorNull(request.getUsername(), "usename");
        notBlankorNull(request.getPassword(), "password");
    }
}
