package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.base.constant.GlobalConstant;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ConvertTransform {

    @Named("localDatetoString")
    default String localDateTimetoString(LocalDate dateTime){
        if (dateTime != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_DATE);
                return dateTime.format(formatter);
            }catch (Exception e){
                return dateTime.toString();
            }
        }
        return null;
    }
}
