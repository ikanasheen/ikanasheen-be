package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.business.model.Ikan;
import com.binus.thesis.fisheryapp.business.service.IkanService;
import com.binus.thesis.fisheryapp.business.validator.IkanValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.IKAN)
@RequiredArgsConstructor
@Slf4j
public class IkanController {

    private final IkanService ikanService;

    private final IkanValidator validator;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse<Ikan> create(@Valid @RequestBody BaseRequest<BaseParameter<Ikan>> request) {
        BaseResponse<Ikan> response = new BaseResponse<>();
        BaseParameter<Ikan> parameter = request.getParameter();
        try {
            validator.validate(parameter, ValidatorTypeEnum.CREATE);
            Ikan ikan = ikanService.create(request.getParameter().getData());
            response.setResult(ikan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_CREATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public BaseResponse<Ikan> update(@Valid @RequestBody BaseRequest<BaseParameter<Ikan>> request) {
        BaseResponse<Ikan> response = new BaseResponse<>();
        BaseParameter<Ikan> parameter = request.getParameter();
        try {
            validator.validate(parameter, ValidatorTypeEnum.UPDATE);
            Ikan ikan = ikanService.update(request.getParameter().getData());
            response.setResult(ikan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idIkan}", method = RequestMethod.DELETE)
    public BaseResponse<Ikan> delete(@Valid @PathVariable(value = "idIkan") String idIkan) {
        BaseResponse<Ikan> response = new BaseResponse<>();
        try {
            ikanService.delete(idIkan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idIkan}", method = RequestMethod.GET)
    public BaseResponse<Ikan> detail(@Valid @PathVariable(value = "idIkan") String idIkan) {
        BaseResponse<Ikan> response = new BaseResponse<>();
        try {
            Ikan ikan = ikanService.detail(idIkan);
            response.setResult(ikan);
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
    public BaseResponse<List<Ikan>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<Ikan>> request) {
        BaseResponse<List<Ikan>> response = new BaseResponse<>();
        try {
            response = ikanService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
