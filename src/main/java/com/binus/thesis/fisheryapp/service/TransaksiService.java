package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.dto.request.*;
import com.binus.thesis.fisheryapp.dto.response.ResponseTransaksi;
import com.binus.thesis.fisheryapp.model.Ikan;
import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Pembeli;
import com.binus.thesis.fisheryapp.model.Transaksi;
import com.binus.thesis.fisheryapp.repository.TransaksiRepository;
import com.binus.thesis.fisheryapp.service.specification.TransaksiSpecification;
import com.binus.thesis.fisheryapp.transform.TransaksiTransform;
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
public class TransaksiService {

    private final TransaksiRepository repository;

    private final TransaksiSpecification specification;

    private final PageTransform pageTransform;
    private final TransaksiTransform transform;

    private final IkanService ikanService;
    private final NelayanService nelayanService;
    private final PembeliService pembeliService;


    public Transaksi create(RequestCreateTransaksi request) {
        String idTransaksi = GeneratorUtils.generateId("TRX", new Date(), 4);
        Pembeli pembeli = pembeliService.findByIdUser(request.getIdUserPembeli());
        Ikan ikan = ikanService.detail(request.getIdIkan());
        return repository.save(
                transform.createTransaksitoEntity(request, idTransaksi, pembeli, ikan)
        );
    }

    public Transaksi update(RequestUpdateTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        Pembeli pembeli = pembeliService.findByIdUser(request.getIdUserPembeli());
        Ikan ikan = ikanService.detail(request.getIdIkan());
        return repository.save(
                transform.updateTransaksitoEntity(request, pembeli, ikan)
        );
    }

    public void delete(String idTransaksi) {
        getTransaksi(idTransaksi);
        repository.deleteById(idTransaksi);
    }

    public ResponseTransaksi detail(String idTransaksi) {
        Transaksi transaksi = getTransaksi(idTransaksi);
        return transform.toResponseTransaksi(transaksi);
    }

    public BaseResponse<List<ResponseTransaksi>> retrieve(BaseRequest<BaseParameter<Transaksi>> request) {
        BaseResponse<List<ResponseTransaksi>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<Transaksi> data = repository.findAll(
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
        response.setResult(transform.toResponseTransaksiList(data.getContent()));

        return response;
    }

    public ResponseTransaksi prosesTransaksi(RequestProsesTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        Nelayan nelayan = nelayanService.findByIdUser(request.getIdUserNelayan());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.prosesTransaksitoEntity(transaksiRepo, request, nelayan, LocalDate.now())
                )
        );
    }

    public ResponseTransaksi approvalNego(RequestApproveNegoTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.approvalNegotoEntity(transaksiRepo, request, LocalDate.now())
                )
        );
    }

    public ResponseTransaksi completeTransaksi(RequestCompleteCancelTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.completeCancelTransaksitoEntity(transaksiRepo, "COMPLETE")
                )
        );
    }

    public ResponseTransaksi cancelTransaksi(RequestCompleteCancelTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.completeCancelTransaksitoEntity(transaksiRepo, "CANCEL")
                )
        );
    }

    public Transaksi getTransaksi(String idTransaksi) {
        Optional<Transaksi> transaksiRepo = repository.findById(idTransaksi);
        if (transaksiRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "transaksi")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idTransaksi))
            );
        }

        return transaksiRepo.get();
    }
}
