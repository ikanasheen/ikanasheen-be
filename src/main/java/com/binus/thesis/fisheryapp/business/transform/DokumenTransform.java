package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.base.constant.GlobalConstant;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseDokumen;
import com.binus.thesis.fisheryapp.business.model.Dokumen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface DokumenTransform {

    @Named("generateId")
    default Integer generateId(Integer count) {
        return count+1;
    }

    @Named("localDateTimetoString")
    default String localDateTimetoString(LocalDateTime dateTime){
        if (dateTime != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_TIMESTAMP);
                return dateTime.format(formatter);
            }catch (Exception e){
                return dateTime.toString();
            }
        }
        return null;
    }

    @Named("buildResponseDokumen")
    @Mapping(target = "createdOn", expression = "java(localDateTimetoString(data.getCreatedOn()))")
    ResponseDokumen buildResponseDokumen(Dokumen data);

    @Named("buildDokumenEntity")
    @Mapping(target = "id", expression = "java(generateId(count))")
    @Mapping(target = "fileName", source = "fileName")
    @Mapping(target = "originalName", source = "originalName")
    @Mapping(target = "fileExtension", source = "fileExtension")
    @Mapping(target = "fileSize", source = "fileSize")
    @Mapping(target = "modul", source = "modul")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "status", expression = "java(\"ACTIVE\")")
    @Mapping(target = "createdBy", expression = "java(\"SYSTEM\")")
    @Mapping(target = "createdOn", source = "createdOn")
    Dokumen buildDokumenEntity(
            Integer count,
            String fileName,
            String originalName,
            String fileExtension,
            String fileSize,
            String modul,
            String url,
            LocalDateTime createdOn
    );
}
