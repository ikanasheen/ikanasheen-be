package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseDashboardDto;
import com.binus.thesis.fisheryapp.business.service.UserService;
import com.binus.thesis.fisheryapp.business.validator.LoginValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.DASHBOARD)
@RequiredArgsConstructor
@Slf4j
public class DashboardController {

    private final UserService userService;

    private final LoginValidator loginValidator;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public BaseResponse<ResponseDashboardDto> hello(@Valid @RequestBody BaseRequest<BaseParameter> request) {
        BaseResponse<ResponseDashboardDto> response = new BaseResponse<>();
        try {
            ResponseDashboardDto responseDto = new ResponseDashboardDto();
            responseDto.setMessage("HELLO GURLSSSSS");
//            loginValidator.validate(request.getParameter());
            response.setResult(responseDto);
            response.setStatus(Status.SUCCESS("Login Success"));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
