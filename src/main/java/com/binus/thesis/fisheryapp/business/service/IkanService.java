package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.model.Ikan;
import com.binus.thesis.fisheryapp.business.repository.IkanRepository;
import com.binus.thesis.fisheryapp.business.transform.IkanTransform;
import com.binus.thesis.fisheryapp.business.service.specification.IkanSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IkanService {

    private final IkanRepository repository;

    private final IkanSpecification specification;

    private final IkanTransform transform;
    private final PageTransform pageTransform;

    public Ikan create(Ikan ikan) {
        String idIkan = GeneratorUtils.generateId("", new Date(), 4);
        return repository.save(
                transform.createIkantoEntity(ikan, idIkan)
        );
    }

    public Ikan update(Ikan ikan) {
        Ikan ikanRepo = getIkan(ikan.getIdIkan());
        return repository.save(
                transform.updateIkantoEntity(ikanRepo, ikan)
        );
    }

    public void delete(String idIkan) {
        getIkan(idIkan);
        repository.deleteById(idIkan);
    }

    public Ikan detail(String idIkan) {
        return getIkan(idIkan);
    }

    public BaseResponse<List<Ikan>> retrieve(BaseRequest<BaseParameter<Ikan>> request) {
        BaseResponse<List<Ikan>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<Ikan> data = repository.findAll(
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

    public Ikan getIkan(String idIkan) {
        Optional<Ikan> ikanRepo = repository.findById(idIkan);
        if (ikanRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "ikan")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idIkan))
            );
        }

        return ikanRepo.get();
    }

    public long countIkan() {
        return repository.count();
    }
}
