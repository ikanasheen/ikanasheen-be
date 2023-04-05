package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.base.constant.GlobalConstant;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseDokumen;
import com.binus.thesis.fisheryapp.business.model.Dokumen;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Named("buildResponseDokumenList")
    default List<ResponseDokumen> buildResponseDokumenList(Dokumen dokumen){
        List<ResponseDokumen> dokumenList = new ArrayList<>();
        ResponseDokumen resp = new ResponseDokumen();
        resp.setId(dokumen.getId());
        resp.setFileName(dokumen.getFileName());
        resp.setFileExtension(dokumen.getFileExtension());
        resp.setFileSize(dokumen.getFileSize());
        resp.setOriginalName(dokumen.getOriginalName());
        resp.setModul(dokumen.getModul());
        resp.setUrl(dokumen.getUrl());
        resp.setStatus(dokumen.getStatus());
        resp.setCreatedBy(dokumen.getCreatedBy());
        resp.setCreatedOn(localDateTimetoString(dokumen.getCreatedOn()));
        dokumenList.add(resp);

        return dokumenList;
    }

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
