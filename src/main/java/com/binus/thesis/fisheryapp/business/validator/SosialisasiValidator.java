package com.binus.thesis.fisheryapp.business.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.business.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.business.model.Sosialisasi;
import org.springframework.stereotype.Component;

@Component
public class SosialisasiValidator extends BaseValidator<Sosialisasi> {

    public void validate(BaseParameter<Sosialisasi> parameter, ValidatorTypeEnum type) {
        validate(parameter.getData(), type);
    }

    public void validate(Sosialisasi request, ValidatorTypeEnum type) throws ApplicationException {
        if (!type.equals(ValidatorTypeEnum.CREATE) && !type.equals(ValidatorTypeEnum.RETRIEVE)) {
            notBlankorNull(request.getIdSosialisasi(), "Id sosialisasi");
            return;
        }
        notBlankorNull(request.getJudul(), "Judul");
        isMax(request.getJudul(), "Judul", 255);
        notBlankorNull(request.getJenisKonten(), "Jenis konten");
        notBlankorNull(request.getKonten(), "Konten");
        notBlankorNull(request.getStatus(), "Status");
        notBlankorNull(request.getPenulis(), "Penulis");
        isMax(request.getPenulis(), "Penulis", 255);
    }
}
