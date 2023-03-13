package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.model.Sosialisasi;
import com.binus.thesis.fisheryapp.repository.SosialisasiRepository;
import com.binus.thesis.fisheryapp.service.specification.SosialisasiSpecification;
import com.binus.thesis.fisheryapp.transform.SosialisasiTransform;
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
public class SosialisasiService {

    private final SosialisasiRepository repository;

    private final SosialisasiSpecification specification;

    private final PageTransform pageTransform;
    private final SosialisasiTransform transform;


    public Sosialisasi create(Sosialisasi sosialisasi) {
        String idSosialisasi = GeneratorUtils.generateId(sosialisasi.getJenisKonten().substring(0,3), new Date(), 4);
        return repository.save(
                transform.createSosialisasitoEntity(sosialisasi, idSosialisasi, LocalDate.now())
        );
    }

    public Sosialisasi update(Sosialisasi sosialisasi) {
        Sosialisasi sosialisasiRepo = getSosialisasi(sosialisasi.getIdSosialisasi());
        return repository.save(
                transform.updateSosialisasitoEntity(sosialisasiRepo, sosialisasi)
        );
    }

    public void delete(String idSosialisasi) {
        getSosialisasi(idSosialisasi);
        repository.deleteById(idSosialisasi);
    }

    public Sosialisasi detail(String idSosialisasi) {
        return getSosialisasi(idSosialisasi);
    }

    public List<Sosialisasi> retrieveList() {
        List<Sosialisasi> sosialisasiList = repository.findAll();
        return sosialisasiList;
    }

    public BaseResponse<List<Sosialisasi>> retrieve(BaseRequest<BaseParameter<Sosialisasi>> request) {
        BaseResponse<List<Sosialisasi>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<Sosialisasi> data = repository.findAll(
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

    public Sosialisasi getSosialisasi(String idSosialisasi) {
        Optional<Sosialisasi> sosialisasiRepo = repository.findById(idSosialisasi);
        if (sosialisasiRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "sosialisasi")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idSosialisasi))
            );
        }

        return sosialisasiRepo.get();
    }
}
