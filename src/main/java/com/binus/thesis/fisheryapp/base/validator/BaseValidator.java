package com.binus.thesis.fisheryapp.base.validator;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import org.springframework.util.StringUtils;

public class BaseValidator<T> {

    public Status fieldIsRequired(String field) {
        return Status.INVALID(GlobalMessage.Error.FIELD_REQUIRED
                .replaceAll(GlobalMessage.Error.paramVariable.get(0), field)
        );
    }

    protected void notNull(Object value, String field) {
        if (value == null) {
            throw new ApplicationException(fieldIsRequired(field));
        }
    }

    protected void notBlankorNull(String value, String field) {
        if (value == null || StringUtils.isEmpty(value)) {
            throw new ApplicationException(fieldIsRequired(field));
        }
    }
}
