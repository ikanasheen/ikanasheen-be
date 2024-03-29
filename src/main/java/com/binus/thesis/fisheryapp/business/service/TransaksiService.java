package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.dto.request.*;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseTransaksi;
import com.binus.thesis.fisheryapp.business.model.Pembeli;
import com.binus.thesis.fisheryapp.business.model.Transaksi;
import com.binus.thesis.fisheryapp.business.service.specification.TransaksiSpecification;
import com.binus.thesis.fisheryapp.business.transform.TransaksiTransform;
import com.binus.thesis.fisheryapp.business.model.Ikan;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.repository.TransaksiRepository;
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
                transform.createTransaksitoEntity(request, idTransaksi, pembeli, ikan, LocalDate.now().toString())
        );
    }

    public Transaksi update(RequestUpdateTransaksi request) {
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
                        transform.prosesTransaksitoEntity(transaksiRepo, request, nelayan, LocalDate.now().toString())
                )
        );
    }

    public ResponseTransaksi approvalNego(RequestApproveNegoTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.approvalNegotoEntity(transaksiRepo, request, LocalDate.now().toString())
                )
        );
    }

    public ResponseTransaksi completeTransaksi(RequestCompleteCancelProsesPengirimanTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.completeCancelProsesPengirimanTransaksitoEntity(transaksiRepo, "COMPLETE", null)
                )
        );
    }

    public ResponseTransaksi cancelTransaksi(RequestCompleteCancelProsesPengirimanTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.completeCancelProsesPengirimanTransaksitoEntity(transaksiRepo, "CANCEL", null)
                )
        );
    }

    public ResponseTransaksi prosesDikirimTransaksi(RequestCompleteCancelProsesPengirimanTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.completeCancelProsesPengirimanTransaksitoEntity(transaksiRepo, "KIRIM", request.getCatatanPengiriman())
                )
        );
    }

    public ResponseTransaksi prosesSiapDiambilTransaksi(RequestCompleteCancelProsesPengirimanTransaksi request) {
        Transaksi transaksiRepo = getTransaksi(request.getIdTransaksi());
        return transform.toResponseTransaksi(
                repository.saveAndFlush(
                        transform.completeCancelProsesPengirimanTransaksitoEntity(transaksiRepo, "SIAP_DIAMBIL", null)
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

    public List<Transaksi> findAll() {
        return repository.findAll();
    }

    public List<Transaksi> findByStatusAndTanggalDiajukan(String tanggalDiajukan) {
        return repository.findByStatusAndTanggalDiajukan("DIAJUKAN", tanggalDiajukan);
    }

    public List<Transaksi> findByStatusAndTanggalDiproses(String tanggalDiproses) {
        return repository.findByStatusAndTanggalDiproses("DIPROSES", tanggalDiproses);
    }

    public List<Transaksi> findByStatusAndTanggalDikirim(String tanggalDikirim) {
        return repository.findByStatusAndTanggalDikirim("DIKIRIM", tanggalDikirim);
    }

    public List<Transaksi> findByStatusAndTanggalSiapDiambil(String tanggalSiapDiambil) {
        return repository.findByStatusAndTanggalSiapDiambil("SIAP_DIAMBIL", tanggalSiapDiambil);
    }

    public List<Transaksi> findByStatusAndTanggalSelesai(String tanggalSelesai) {
        return repository.findByStatusAndTanggalSelesai("SELESAI", tanggalSelesai);
    }

    public List<Transaksi> findByTanggalDiajukan(String tanggalDiajukan) {
        return repository.findByTanggalDiajukan(tanggalDiajukan);
    }

    public List<Transaksi> findByTanggalDiproses(String tanggalDiproses) {
        return repository.findByTanggalDiproses(tanggalDiproses);
    }

    public List<Transaksi> findByTanggalSelesai(String tanggalSelesai) {
        return repository.findByTanggalSelesai(tanggalSelesai);
    }

    public List<Transaksi> findByNelayanKecamatan(String tanggalDiproses, String kecamatan) {
        return repository.findByIdNelayanNotNullAndTanggalDiprosesAndNelayanKecamatan(tanggalDiproses, kecamatan);
    }
}
