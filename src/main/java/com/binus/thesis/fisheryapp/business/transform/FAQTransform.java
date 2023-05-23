package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateFAQ;
import com.binus.thesis.fisheryapp.business.dto.request.RequestUpdateFAQ;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseFAQ;
import com.binus.thesis.fisheryapp.business.model.FAQ;
import com.binus.thesis.fisheryapp.business.model.Topik;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = TopikTransform.class
)
public interface FAQTransform {

    @Named("createFAQtoEntity")
    @Mapping(target = "idFaq", source = "idFaq")
    @Mapping(target = "idTopik", source = "request.idTopik")
    @Mapping(target = "pertanyaan", source = "request.pertanyaan")
    @Mapping(target = "jawaban", source = "request.jawaban")
    @Mapping(target = "tanggalDibuat", source = "dateNow")
    FAQ createFAQtoEntity(RequestCreateFAQ request, String idFaq, String dateNow);

    @Named("updateFAQtoEntity")
    @Mapping(target = "idFaq", source = "request.idFaq")
    @Mapping(target = "idTopik", source = "request.idTopik")
    @Mapping(target = "pertanyaan", expression = "java(request.getPertanyaan() == null || request.getPertanyaan().isEmpty() ? faq.getPertanyaan() : request.getPertanyaan())")
    @Mapping(target = "jawaban", expression = "java(request.getJawaban() == null || request.getJawaban().isEmpty() ? faq.getJawaban() : request.getJawaban())")
//    @Mapping(target = "tanggalDibuat", source = "faq.tanggalDibuat")
    @Mapping(target = "tanggalDiubah", source = "dateNow")
    FAQ updateFAQtoEntity(@MappingTarget FAQ faq, RequestUpdateFAQ request, String dateNow);

    @Named("buildResponseFAQ")
    @Mapping(target = "topik", source = "topik", qualifiedByName = "buildResponseTopik")
    @Mapping(target = "namaTopik", source = "topik.namaTopik")
    ResponseFAQ buildResponseFAQ(FAQ faq);

    @Named("buildResponseFAQList")
    @IterableMapping(qualifiedByName = "buildResponseFAQ")
    List<ResponseFAQ> buildResponseFAQList(List<FAQ> entities);
}
