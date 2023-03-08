package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.model.Ikan;
import org.springframework.stereotype.Component;

@Component
public class IkanValidator extends BaseValidator<Ikan> {

    public void validate(BaseParameter<Ikan> parameter, ValidatorTypeEnum type) {
        validate(parameter.getData(), type);
    }

    public void validate(Ikan request, ValidatorTypeEnum type) throws ApplicationException {
        if (!type.equals(ValidatorTypeEnum.CREATE) && !type.equals(ValidatorTypeEnum.RETRIEVE)) {
            notBlankorNull(request.getIdIkan(), "Id ikan");
            return;
        }
        notBlankorNull(request.getNamaIkan(), "Nama ikan");
        isMax(request.getNamaIkan(), "Nama ikan", 255);
        notBlankorNull(request.getDeskripsi(), "Deskripsi");
        isMax(request.getDeskripsi(), "Deskripsi", 255);
    }
}
