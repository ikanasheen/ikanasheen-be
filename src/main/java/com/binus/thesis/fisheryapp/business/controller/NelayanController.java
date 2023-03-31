package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.enums.ValidatorTypeEnum;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseNelayan;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.service.NelayanService;
import com.binus.thesis.fisheryapp.business.validator.NelayanValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.NELAYAN)
@RequiredArgsConstructor
@Slf4j
public class NelayanController {

    private final NelayanService nelayanService;

    private final NelayanValidator validator;

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public BaseResponse<Nelayan> update(@Valid @RequestBody BaseRequest<BaseParameter<Nelayan>> request) {
        BaseResponse<Nelayan> response = new BaseResponse<>();
        BaseParameter<Nelayan> parameter = request.getParameter();
        try {
            validator.validate(parameter, ValidatorTypeEnum.UPDATE);
            Nelayan nelayan = nelayanService.update(request.getParameter().getData());
            response.setResult(nelayan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idNelayan}", method = RequestMethod.DELETE)
    public BaseResponse<Nelayan> delete(@Valid @PathVariable(value = "idNelayan") String idNelayan) {
        BaseResponse<Nelayan> response = new BaseResponse<>();
        try {
            nelayanService.delete(idNelayan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idNelayan}", method = RequestMethod.GET)
    public BaseResponse<ResponseNelayan> detail(@Valid @PathVariable(value = "idNelayan") String idNelayan) {
        BaseResponse<ResponseNelayan> response = new BaseResponse<>();
        try {
            ResponseNelayan nelayan = nelayanService.detail(idNelayan);
            response.setResult(nelayan);
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
    public BaseResponse<List<ResponseNelayan>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<Nelayan>> request) {
        BaseResponse<List<ResponseNelayan>> response = new BaseResponse<>();
        try {
            response = nelayanService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
