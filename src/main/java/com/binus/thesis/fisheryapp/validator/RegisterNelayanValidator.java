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
