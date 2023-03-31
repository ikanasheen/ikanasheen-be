package com.binus.thesis.fisheryapp.business.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.business.dto.request.RequestRegisterNelayan;
import org.springframework.stereotype.Component;

@Component
public class RegisterNelayanValidator extends BaseValidator<RequestRegisterNelayan> {

    public void validate(BaseParameter<RequestRegisterNelayan> parameter) {
        validate(parameter.getData());
    }

    public void validate(RequestRegisterNelayan request) throws ApplicationException {
        notNull(request.getIdRole(), "Id role");
        notBlankorNull(request.getNamaLengkap(), "Nama lengkap");
        isMax(request.getNamaLengkap(), "Nama lengkap", 255);
        notBlankorNull(request.getGender(), "Gender");
        notNull(request.getTanggalLahir(), "Tanggal lahir");
        notBlankorNull(request.getAlamat(), "Alamat");
        notBlankorNull(request.getKecamatan(), "Kecamatan");
        notBlankorNull(request.getKelurahanDesa(), "Kelurahan / desa");
        notBlankorNull(request.getNoTelepon(), "No telepon");
        notBlankorNull(request.getUsername(), "Username");
        notBlankorNull(request.getPassword(), "Password");
    }
}
