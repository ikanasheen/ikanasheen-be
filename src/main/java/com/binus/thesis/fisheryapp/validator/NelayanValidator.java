package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.model.Ikan;
import com.binus.thesis.fisheryapp.model.Nelayan;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class NelayanValidator extends BaseValidator<Nelayan> {

    public void validate(BaseParameter<Nelayan> parameter, ValidatorTypeEnum type) {
        validate(parameter.getData(), type);
    }

    public void validate(Nelayan request, ValidatorTypeEnum type) throws ApplicationException {
        if (!type.equals(ValidatorTypeEnum.CREATE) && !type.equals(ValidatorTypeEnum.RETRIEVE)) {
            notBlankorNull(request.getIdNelayan(), "id nelayan");
            return;
        }
        notBlankorNull(request.getIdUser(), "id user");
        notBlankorNull(request.getNamaLengkap(), "nama lengkap");
        notBlankorNull(request.getGender(), "jenis kelamin");
        notNull(request.getTanggalLahir(), "tanggal lahir");
        notNull(request.getAlamat(), "alamat");
        notNull(request.getKecamatan(), "kecamatan");
        notNull(request.getKelurahanDesa(), "kelurahan / desa");
        notNull(request.getNoTelepon(), "no telepon");
    }
}
