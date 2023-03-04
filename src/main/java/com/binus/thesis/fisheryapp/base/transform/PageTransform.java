package com.binus.thesis.fisheryapp.base.transform;

import com.binus.thesis.fisheryapp.base.dto.Paging;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PageTransform {

    @Named("toPage")
    @Mapping(target = "page", source = "page")
    @Mapping(target = "limit", source = "limit")
    @Mapping(target = "totalPage", source = "totalPage")
    @Mapping(target = "totalRecord", source = "totalRecord")
    Paging toPage(Integer page, Integer limit, Integer totalPage, Long totalRecord);
}
