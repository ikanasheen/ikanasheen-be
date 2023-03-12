package com.binus.thesis.fisheryapp.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.dto.request.*;
import com.binus.thesis.fisheryapp.dto.response.ResponseTransaksi;
import com.binus.thesis.fisheryapp.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.model.Transaksi;
import com.binus.thesis.fisheryapp.service.TransaksiService;
import com.binus.thesis.fisheryapp.validator.TransaksiValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.TRANSAKSI)
@RequiredArgsConstructor
@Slf4j
public class TransaksiController {

    private final TransaksiService transaksiService;

    private final TransaksiValidator validator;

    @PutMapping()
    public BaseResponse<Transaksi> create(@Valid @RequestBody BaseRequest<BaseParameter<RequestCreateTransaksi>> request) {
        BaseResponse<Transaksi> response = new BaseResponse<>();
        BaseParameter<RequestCreateTransaksi> parameter = request.getParameter();
        try {
            Transaksi transaksi = transaksiService.create(request.getParameter().getData());
            response.setResult(transaksi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_CREATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @PatchMapping()
    public BaseResponse<Transaksi> update(@Valid @RequestBody BaseRequest<BaseParameter<RequestUpdateTransaksi>> request) {
        BaseResponse<Transaksi> response = new BaseResponse<>();
        BaseParameter<RequestUpdateTransaksi> parameter = request.getParameter();
        try {
            Transaksi transaksi = transaksiService.update(parameter.getData());
            response.setResult(transaksi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @DeleteMapping("/{idTransaksi}")
    public BaseResponse<Transaksi> delete(@Valid @PathVariable(value = "idTransaksi") String idTransaksi) {
        BaseResponse<Transaksi> response = new BaseResponse<>();
        try {
            transaksiService.delete(idTransaksi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @GetMapping("/{idTransaksi}")
    public BaseResponse<ResponseTransaksi> detail(@Valid @PathVariable(value = "idTransaksi") String idTransaksi) {
        BaseResponse<ResponseTransaksi> response = new BaseResponse<>();
        try {
            ResponseTransaksi transaksi = transaksiService.detail(idTransaksi);
            response.setResult(transaksi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCESS_GET_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @PostMapping
    public BaseResponse<List<ResponseTransaksi>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<Transaksi>> request) {
        BaseResponse<List<ResponseTransaksi>> response = new BaseResponse<>();
        try {
            response = transaksiService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @PostMapping("/proses")
    public BaseResponse<ResponseTransaksi> prosesTransaksi(@Valid @RequestBody BaseRequest<BaseParameter<RequestProsesTransaksi>> request) {
        BaseResponse<ResponseTransaksi> response = new BaseResponse<>();
        try {
            ResponseTransaksi transaksi = transaksiService.prosesTransaksi(request.getParameter().getData());
            response.setResult(transaksi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @PostMapping("/approvalnego")
    public BaseResponse<ResponseTransaksi> approvalNego(@Valid @RequestBody BaseRequest<BaseParameter<RequestApproveNegoTransaksi>> request) {
        BaseResponse<ResponseTransaksi> response = new BaseResponse<>();
        try {
            ResponseTransaksi transaksi = transaksiService.approvalNego(request.getParameter().getData());
            response.setResult(transaksi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @PostMapping("/complete")
    public BaseResponse<ResponseTransaksi> completeTransaksi(@Valid @RequestBody BaseRequest<BaseParameter<RequestCompleteTransaksi>> request) {
        BaseResponse<ResponseTransaksi> response = new BaseResponse<>();
        try {
            ResponseTransaksi transaksi = transaksiService.completeTransaksi(request.getParameter().getData());
            response.setResult(transaksi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
