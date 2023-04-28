package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateBantuan;
import com.binus.thesis.fisheryapp.business.dto.request.RequestUpdateBantuan;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseBantuan;
import com.binus.thesis.fisheryapp.business.model.BantuanTersedia;
import com.binus.thesis.fisheryapp.business.repository.ProposalBantuanRepository;
import com.binus.thesis.fisheryapp.business.transform.BantuanTersediaTransform;
import com.binus.thesis.fisheryapp.business.repository.BantuanTersediaRepository;
import com.binus.thesis.fisheryapp.business.service.specification.BantuanTersediaSpecification;
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
    private final ProposalBantuanRepository proposalBantuanRepository;

    private final BantuanTersediaSpecification specification;

    private final BantuanTersediaTransform transform;
    private final PageTransform pageTransform;

    public ResponseBantuan create(RequestCreateBantuan request) {
        String jenis = request.getJenisBantuan().split(" ")[0].substring(0,3);
        String idBantuan = GeneratorUtils.generateId(jenis.toUpperCase(Locale.ROOT), new Date(), 4);
        BantuanTersedia savedBantuan = repository.saveAndFlush(
                transform.createBantuantoEntity(
                        new BantuanTersedia(),
                        idBantuan,
                        request,
                        request.getDokumen().get(0)
                )
        );

        return transform.buildResponseBantuan(savedBantuan);
    }

    public ResponseBantuan update(RequestUpdateBantuan request) {
        BantuanTersedia bantuanRepo = getBantuanTersedia(request.getIdBantuan());
        transform.updateKuota(bantuanRepo, request);
        transform.updateBantuantoEntity(bantuanRepo, request, request.getDokumen().get(0));
        BantuanTersedia savedBantuan = repository.saveAndFlush(bantuanRepo);
        return transform.buildResponseBantuan(savedBantuan);
    }

    public void delete(String idBantuan) {
        getBantuanTersedia(idBantuan);
        repository.deleteById(idBantuan);
    }

    public ResponseBantuan detail(String idBantuan) {
        return transform.buildResponseBantuan(getBantuanTersedia(idBantuan));
    }

    public BaseResponse<List<ResponseBantuan>> retrieve(BaseRequest<BaseParameter<BantuanTersedia>> request) {
        BaseResponse<List<ResponseBantuan>> response = new BaseResponse<>();
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
        response.setResult(transform.buildResponseBantuanList(data.getContent()));

        return response;
    }

    public BantuanTersedia getBantuanTersedia(String idBantuan) {
        Optional<BantuanTersedia> bantuanRepo = repository.findById(idBantuan);
        if (bantuanRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "bantuan")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idBantuan))
            );
        }

        return bantuanRepo.get();
    }

    public BantuanTersedia updateKuotaTersisa(BantuanTersedia bantuan) {
        int kuota = Integer.parseInt(bantuan.getKuotaTersisa()) - 1;
        String status = "ACTIVE";
        if (kuota < 1) {
            status = "UNAVAILABLE";
        }
        return repository.save(
                transform.updateKuotaTersisa(bantuan, String.valueOf(kuota), status)
        );
    }
}
