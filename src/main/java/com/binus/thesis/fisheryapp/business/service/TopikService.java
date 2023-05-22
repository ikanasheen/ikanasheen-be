package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateTopik;
import com.binus.thesis.fisheryapp.business.model.Topik;
import com.binus.thesis.fisheryapp.business.repository.TopikRepository;
import com.binus.thesis.fisheryapp.business.service.specification.TopikSpecification;
import com.binus.thesis.fisheryapp.business.transform.TopikTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopikService {

    private final TopikRepository repository;

    private final TopikSpecification specification;

    private final PageTransform pageTransform;
    private final TopikTransform transform;


    public Topik create(RequestCreateTopik request) {
        checkTopikByName(request.getNamaTopik());
        return repository.save(
                transform.createTopiktoEntity(request, Math.toIntExact(repository.count()))
        );
    }

    public Topik update(Topik request) {
        Topik topik = getTopik(request.getIdTopik());
        if (!topik.getNamaTopik().equalsIgnoreCase(request.getNamaTopik())) {
            checkTopikByName(request.getNamaTopik());
        }
        return repository.save(
                transform.updateTopiktoEntity(topik, request)
        );
    }

    public void delete(int idTopik) {
        getTopik(idTopik);
        repository.deleteById(idTopik);
    }

    public Topik detail(int idTopik) {
        return getTopik(idTopik);
    }

    public BaseResponse<List<Topik>> retrieve(BaseRequest<BaseParameter<Topik>> request) {
        BaseResponse<List<Topik>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<Topik> data = repository.findAll(
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
        response.setResult(data.getContent());

        return response;
    }

    public Topik getTopik(int idTopik) {
        Optional<Topik> topikRepo = repository.findById(idTopik);
        if (topikRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "topik")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), String.valueOf(idTopik)))
            );
        }

        return topikRepo.get();
    }

    public void checkTopikByName(String namaTopik) {
        Topik topik = repository.findByNamaTopik(namaTopik);
        if (topik != null) {
            throw new ApplicationException(Status.DATA_ALREADY_EXIST("Topik sudah ada!"));
        }
    }
}
