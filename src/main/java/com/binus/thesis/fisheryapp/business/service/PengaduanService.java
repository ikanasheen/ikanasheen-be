package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreatePengaduan;
import com.binus.thesis.fisheryapp.business.dto.request.RequestPenangananPengaduan;
import com.binus.thesis.fisheryapp.business.dto.response.ResponsePengaduan;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.model.Pengaduan;
import com.binus.thesis.fisheryapp.business.repository.PengaduanRepository;
import com.binus.thesis.fisheryapp.business.service.specification.PengaduanSpecification;
import com.binus.thesis.fisheryapp.business.transform.PengaduanTransform;
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
public class PengaduanService {

    private final NelayanService nelayanService;

    private final PengaduanRepository repository;

    private final PengaduanSpecification specification;

    private final PageTransform pageTransform;
    private final PengaduanTransform transform;


    public ResponsePengaduan create(RequestCreatePengaduan request) {
        Nelayan nelayan = nelayanService.findByIdUser(request.getIdUserNelayan());
        String idPengaduan = GeneratorUtils.generateId("ADUAN", new Date(), 3);
        Pengaduan pengaduan = repository.saveAndFlush(transform.createPengaduantoEntity(request, nelayan, idPengaduan, LocalDateTime.now().toString()));
        return transform.buildResponsePengaduan(pengaduan);
    }

    public ResponsePengaduan penanganan(RequestPenangananPengaduan request) {
        Pengaduan pengaduanRepo = getPengaduan(request.getIdPengaduan());
        Pengaduan pengaduan = repository.saveAndFlush(transform.penanganantoEntity(pengaduanRepo, request, LocalDateTime.now().toString()));
        return transform.buildResponsePengaduan(pengaduan);
    }

    public ResponsePengaduan detail(String idPengaduan) {
        return transform.buildResponsePengaduan(getPengaduan(idPengaduan));
    }

    public BaseResponse<List<ResponsePengaduan>> retrieve(BaseRequest<BaseParameter<Pengaduan>> request) {
        BaseResponse<List<ResponsePengaduan>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<Pengaduan> data = repository.findAll(
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
        response.setResult(transform.buildResponsePengaduanList(data.getContent()));

        return response;
    }

    public Pengaduan getPengaduan(String idPengaduan) {
        Optional<Pengaduan> pengaduanRepo = repository.findById(idPengaduan);
        if (pengaduanRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "pengaduan")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idPengaduan))
            );
        }

        return pengaduanRepo.get();
    }
}
