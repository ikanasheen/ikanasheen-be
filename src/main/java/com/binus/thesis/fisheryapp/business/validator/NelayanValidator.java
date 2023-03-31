package com.binus.thesis.fisheryapp.business.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.business.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import org.springframework.stereotype.Component;

@Component
public class NelayanValidator extends BaseValidator<Nelayan> {

    public void validate(BaseParameter<Nelayan> parameter, ValidatorTypeEnum type) {
        validate(parameter.getData(), type);
    }

    public void validate(Nelayan request, ValidatorTypeEnum type) throws ApplicationException {
        if (!type.equals(ValidatorTypeEnum.CREATE) && !type.equals(ValidatorTypeEnum.RETRIEVE)) {
            notBlankorNull(request.getIdNelayan(), "Id nelayan");
            return;
        }
        notBlankorNull(request.getIdUser(), "Id user");
        notBlankorNull(request.getNamaLengkap(), "Nama lengkap");
        isMax(request.getNamaLengkap(), "Nama lengkap", 255);
        notBlankorNull(request.getGender(), "Jenis kelamin");
        notNull(request.getTanggalLahir(), "Tanggal lahir");
        notNull(request.getAlamat(), "Alamat");
        notNull(request.getKecamatan(), "Kecamatan");
        notNull(request.getKelurahanDesa(), "Kelurahan / desa");
        notNull(request.getNoTelepon(), "No telepon");
    }
}
