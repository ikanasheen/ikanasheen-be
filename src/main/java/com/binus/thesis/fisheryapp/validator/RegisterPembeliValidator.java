package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.dto.request.RequestRegisterPembeli;
import org.springframework.stereotype.Component;

@Component
public class RegisterPembeliValidator extends BaseValidator<RequestRegisterPembeli> {

    public void validate(BaseParameter<RequestRegisterPembeli> parameter) {
        validate(parameter.getData());
    }

    public void validate(RequestRegisterPembeli request) throws ApplicationException {
        notNull(request.getIdRole(), "Id role");
        notBlankorNull(request.getNamaLengkap(), "Nama lengkap");
        notBlankorNull(request.getAlamat(), "Alamat");
        notBlankorNull(request.getNoTelepon(), "Ao telepon");
        notBlankorNull(request.getUsername(), "Username");
        notBlankorNull(request.getPassword(), "Password");
    }
}
