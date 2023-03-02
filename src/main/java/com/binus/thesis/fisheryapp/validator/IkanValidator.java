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
            notBlankorNull(request.getIdIkan(), "id ikan");
            return;
        }
        notBlankorNull(request.getNamaIkan(), "nama ikan");
        notBlankorNull(request.getDeskripsi(), "deskripsi");
    }
}
