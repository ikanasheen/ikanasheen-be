package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateFAQ;
import com.binus.thesis.fisheryapp.business.dto.request.RequestUpdateFAQ;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseFAQ;
import com.binus.thesis.fisheryapp.business.model.FAQ;
import com.binus.thesis.fisheryapp.business.model.Topik;
import com.binus.thesis.fisheryapp.business.repository.FAQRepository;
import com.binus.thesis.fisheryapp.business.service.specification.FAQSpecification;
import com.binus.thesis.fisheryapp.business.transform.FAQTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FAQService {

    private final FAQRepository repository;
    private final TopikService topikService;

    private final FAQSpecification specification;

    private final PageTransform pageTransform;
    private final FAQTransform transform;


    public ResponseFAQ create(RequestCreateFAQ request) {
        Topik topik = topikService.getTopik(request.getIdTopik());
        String idFaq = GeneratorUtils.generateId(topik.getNamaTopik().substring(0,3), new Date(), 3);
        FAQ faq = repository.saveAndFlush(transform.createFAQtoEntity(request, idFaq, LocalDateTime.now().toString()));
        return transform.buildResponseFAQ(faq);
    }

    public ResponseFAQ update(RequestUpdateFAQ request) {
        FAQ faqRepo = getFAQ(request.getIdFaq());
        FAQ faq = repository.saveAndFlush(transform.updateFAQtoEntity(faqRepo, request, LocalDateTime.now().toString()));
        return transform.buildResponseFAQ(faq);
    }

    public void delete(String idFAQ) {
        getFAQ(idFAQ);
        repository.deleteById(idFAQ);
    }

    public ResponseFAQ detail(String idFAQ) {
        return transform.buildResponseFAQ(getFAQ(idFAQ));
    }

    public BaseResponse<List<ResponseFAQ>> retrieve(BaseRequest<BaseParameter<FAQ>> request) {
        BaseResponse<List<ResponseFAQ>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<FAQ> data = repository.findAll(
                specification.predicate(request.getParameter()), pageable
        );

        Paging paging = pageTransform.toPage(
                request.getPaging().getPage(),
                limit,
                data.getTotalPages(),
                data.getTotalElements()
        );

        response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
        response.setPaging(paging);
        response.setResult(transform.buildResponseFAQList(data.getContent()));

        return response;
    }

    public FAQ getFAQ(String idFAQ) {
        Optional<FAQ> faqRepo = repository.findById(idFAQ);
        if (faqRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "faq")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idFAQ))
            );
        }

        return faqRepo.get();
    }
}
