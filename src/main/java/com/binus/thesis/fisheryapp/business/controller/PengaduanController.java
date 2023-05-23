package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreatePengaduan;
import com.binus.thesis.fisheryapp.business.dto.request.RequestPenangananPengaduan;
import com.binus.thesis.fisheryapp.business.dto.response.ResponsePengaduan;
import com.binus.thesis.fisheryapp.business.model.Pengaduan;
import com.binus.thesis.fisheryapp.business.service.PengaduanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.PENGADUAN)
@RequiredArgsConstructor
@Slf4j
public class PengaduanController {

    private final PengaduanService pengaduanService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse<ResponsePengaduan> create(@Valid @RequestBody BaseRequest<BaseParameter<RequestCreatePengaduan>> request) {
        BaseResponse<ResponsePengaduan> response = new BaseResponse<>();
        BaseParameter<RequestCreatePengaduan> parameter = request.getParameter();
        try {
            ResponsePengaduan pengaduan = pengaduanService.create(parameter.getData());
            response.setResult(pengaduan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_CREATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/penanganan", method = RequestMethod.POST)
    public BaseResponse<ResponsePengaduan> update(@Valid @RequestBody BaseRequest<BaseParameter<RequestPenangananPengaduan>> request) {
        BaseResponse<ResponsePengaduan> response = new BaseResponse<>();
        BaseParameter<RequestPenangananPengaduan> parameter = request.getParameter();
        try {
            ResponsePengaduan pengaduan = pengaduanService.penanganan(parameter.getData());
            response.setResult(pengaduan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idPengaduan}", method = RequestMethod.GET)
    public BaseResponse<ResponsePengaduan> detail(@Valid @PathVariable(value = "idPengaduan") String idPengaduan) {
        BaseResponse<ResponsePengaduan> response = new BaseResponse<>();
        try {
            ResponsePengaduan pengaduan = pengaduanService.detail(idPengaduan);
            response.setResult(pengaduan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public BaseResponse<List<ResponsePengaduan>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<Pengaduan>> request) {
        BaseResponse<List<ResponsePengaduan>> response = new BaseResponse<>();
        try {
            response = pengaduanService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
