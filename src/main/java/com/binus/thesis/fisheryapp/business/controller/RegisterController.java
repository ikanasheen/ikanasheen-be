package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestRegisterPembeli;
import com.binus.thesis.fisheryapp.business.model.Pembeli;
import com.binus.thesis.fisheryapp.business.dto.request.RequestRegisterNelayan;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.service.NelayanService;
import com.binus.thesis.fisheryapp.business.service.PembeliService;
import com.binus.thesis.fisheryapp.business.validator.RegisterNelayanValidator;
import com.binus.thesis.fisheryapp.business.validator.RegisterPembeliValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(EndpointAPI.REGISTER)
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final NelayanService nelayanService;
    private final PembeliService pembeliService;
    private final RegisterNelayanValidator regNelayanValidator;
    private final RegisterPembeliValidator regPembeliValidator;

    @RequestMapping(value = "/nelayan", method = RequestMethod.PUT)
    public BaseResponse<Nelayan> registerNelayan(@Valid @RequestBody BaseRequest<BaseParameter<RequestRegisterNelayan>> request) {
        BaseResponse<Nelayan> response = new BaseResponse<>();
        try {
            Nelayan nelayan = nelayanService.register(request.getParameter().getData());
            regNelayanValidator.validate(request.getParameter());
            response.setResult(nelayan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_CREATE_ACCOUNT));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/pembeli", method = RequestMethod.PUT)
    public BaseResponse<Pembeli> registerPembeli(@Valid @RequestBody BaseRequest<BaseParameter<RequestRegisterPembeli>> request) {
        BaseResponse<Pembeli> response = new BaseResponse<>();
        try {
            Pembeli pembeli = pembeliService.register(request.getParameter().getData());
            regPembeliValidator.validate(request.getParameter());
            response.setResult(pembeli);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_CREATE_ACCOUNT));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
