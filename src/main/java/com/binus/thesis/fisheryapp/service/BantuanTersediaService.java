package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.model.BantuanTersedia;
import com.binus.thesis.fisheryapp.repository.BantuanTersediaRepository;
import com.binus.thesis.fisheryapp.service.specification.BantuanTersediaSpecification;
import com.binus.thesis.fisheryapp.transform.BantuanTersediaTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BantuanTersediaService {

    private final BantuanTersediaRepository repository;

    private final BantuanTersediaSpecification specification;

    private final BantuanTersediaTransform transform;
    private final PageTransform pageTransform;

    public BantuanTersedia create(BantuanTersedia bantuan) {
        String jenis = bantuan.getJenisBantuan().split(" ")[0];
        String idBantuanTersedia = GeneratorUtils.generateId(jenis.toUpperCase(Locale.ROOT), new Date(), 4);
        return repository.save(
                transform.createBantuantoEntity(bantuan, idBantuanTersedia)
        );
    }

    public BantuanTersedia update(BantuanTersedia bantuan) {
        BantuanTersedia bantuanRepo = getBantuanTersedia(bantuan.getIdBantuan());
        return repository.save(
                transform.updateBantuantoEntity(bantuanRepo, bantuan)
        );
    }

    public void delete(String idBantuanTersedia) {
        getBantuanTersedia(idBantuanTersedia);
        repository.deleteById(idBantuanTersedia);
    }

    public BantuanTersedia detail(String idBantuanTersedia) {
        return getBantuanTersedia(idBantuanTersedia);
    }

    public BaseResponse<List<BantuanTersedia>> retrieve(BaseRequest<BaseParameter<BantuanTersedia>> request) {
        BaseResponse<List<BantuanTersedia>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<BantuanTersedia> data = repository.findAll(
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

    public BantuanTersedia getBantuanTersedia(String idBantuanTersedia) {
        Optional<BantuanTersedia> bantuanRepo = repository.findById(idBantuanTersedia);
        if (bantuanRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "bantuan")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idBantuanTersedia))
            );
        }

        return bantuanRepo.get();
    }
}
