package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.model.Sosialisasi;
import org.springframework.stereotype.Component;

@Component
public class SosialisasiValidator extends BaseValidator<Sosialisasi> {

    public void validate(BaseParameter<Sosialisasi> parameter, ValidatorTypeEnum type) {
        validate(parameter.getData(), type);
    }

    public void validate(Sosialisasi request, ValidatorTypeEnum type) throws ApplicationException {
        if (!type.equals(ValidatorTypeEnum.CREATE) && !type.equals(ValidatorTypeEnum.RETRIEVE)) {
            notBlankorNull(request.getIdSosialisasi(), "id sosialisasi");
            return;
        }
        notBlankorNull(request.getJudul(), "judul");
        notBlankorNull(request.getJenisKonten(), "jenis konten");
        notBlankorNull(request.getKonten(), "konten");
        notBlankorNull(request.getStatus(), "status");
        notBlankorNull(request.getPenulis(), "penulis");
    }
}
