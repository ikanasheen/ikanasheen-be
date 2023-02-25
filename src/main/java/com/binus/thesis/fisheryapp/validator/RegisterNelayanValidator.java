package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.dto.request.LoginRequestDto;
import com.binus.thesis.fisheryapp.dto.request.RegisterNelayanRequestDto;
import org.springframework.stereotype.Component;

@Component
public class RegisterNelayanValidator extends BaseValidator<RegisterNelayanRequestDto> {

    public void validate(BaseParameter<RegisterNelayanRequestDto> parameter) {
        validate(parameter.getData());
    }

    public void validate(RegisterNelayanRequestDto request) throws ApplicationException {
        notNull(request.getIdRole(), "id role");
        notBlankorNull(request.getNamaLengkap(), "nama lengkap");
        notNull(request.getGender(), "gender");
        notNull(request.getTanggalLahir(), "tanggal lahir");
        notBlankorNull(request.getAlamat(), "alamat");
        notBlankorNull(request.getKecamatan(), "kecamatan");
        notBlankorNull(request.getKelurahanDesa(), "kelurahan/desa");
        notBlankorNull(request.getNoTelepon(), "no telepon");
        notBlankorNull(request.getUsername(), "usename");
        notBlankorNull(request.getPassword(), "password");
    }
}
