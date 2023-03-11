package com.binus.thesis.fisheryapp.validator;

import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.validator.BaseValidator;
import com.binus.thesis.fisheryapp.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.model.Transaksi;
import org.springframework.stereotype.Component;

@Component
public class TransaksiValidator extends BaseValidator<Transaksi> {

    public void validate(BaseParameter<Transaksi> parameter, ValidatorTypeEnum type) {
        validate(parameter.getData(), type);
    }

    public void validate(Transaksi request, ValidatorTypeEnum type) throws ApplicationException {
        if (!type.equals(ValidatorTypeEnum.CREATE) && !type.equals(ValidatorTypeEnum.RETRIEVE)) {
            notBlankorNull(request.getIdTransaksi(), "Id sosialisasi");
            return;
        }
//        notBlankorNull(request.getJudul(), "Judul");
//        isMax(request.getJudul(), "Judul", 255);
//        notBlankorNull(request.getJenisKonten(), "Jenis konten");
//        notBlankorNull(request.getKonten(), "Konten");
//        notBlankorNull(request.getStatus(), "Status");
//        notBlankorNull(request.getPenulis(), "Penulis");
//        isMax(request.getPenulis(), "Penulis", 255);
    }
}
