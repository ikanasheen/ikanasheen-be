package com.binus.thesis.fisheryapp.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.model.Sosialisasi;
import com.binus.thesis.fisheryapp.service.SosialisasiService;
import com.binus.thesis.fisheryapp.validator.SosialisasiValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.SOSIALISASI)
@RequiredArgsConstructor
@Slf4j
public class SosialisasiController {

    private final SosialisasiService sosialisasiService;

    private final SosialisasiValidator validator;

    @PutMapping()
    public BaseResponse<Sosialisasi> create(@Valid @RequestBody BaseRequest<BaseParameter<Sosialisasi>> request) {
        BaseResponse<Sosialisasi> response = new BaseResponse<>();
        BaseParameter<Sosialisasi> parameter = request.getParameter();
        try {
            validator.validate(parameter, ValidatorTypeEnum.CREATE);
            Sosialisasi sosialisasi = sosialisasiService.create(request.getParameter().getData());
            response.setResult(sosialisasi);
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
    public BaseResponse<Sosialisasi> update(@Valid @RequestBody BaseRequest<BaseParameter<Sosialisasi>> request) {
        BaseResponse<Sosialisasi> response = new BaseResponse<>();
        BaseParameter<Sosialisasi> parameter = request.getParameter();
        try {
            validator.validate(parameter, ValidatorTypeEnum.UPDATE);
            Sosialisasi sosialisasi = sosialisasiService.update(request.getParameter().getData());
            response.setResult(sosialisasi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @DeleteMapping("/{idSosialisasi}")
    public BaseResponse<Sosialisasi> delete(@Valid @PathVariable(value = "idSosialisasi") String idSosialisasi) {
        BaseResponse<Sosialisasi> response = new BaseResponse<>();
        try {
            sosialisasiService.delete(idSosialisasi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @GetMapping("/{idSosialisasi}")
    public BaseResponse<Sosialisasi> detail(@Valid @PathVariable(value = "idSosialisasi") String idSosialisasi) {
        BaseResponse<Sosialisasi> response = new BaseResponse<>();
        try {
            Sosialisasi sosialisasi = sosialisasiService.detail(idSosialisasi);
            response.setResult(sosialisasi);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @PostMapping("")
    public BaseResponse<List<Sosialisasi>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<Sosialisasi>> request) {
        BaseResponse<List<Sosialisasi>> response = new BaseResponse<>();
        try {
            response = sosialisasiService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
