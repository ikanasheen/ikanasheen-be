package com.binus.thesis.fisheryapp.controller;

import com.binus.thesis.fisheryapp.base.component.GlobalMessage;
import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.dto.request.LoginRequestDto;
import com.binus.thesis.fisheryapp.dto.request.RegisterNelayanRequestDto;
import com.binus.thesis.fisheryapp.dto.request.RegisterPembeliRequestDto;
import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Pembeli;
import com.binus.thesis.fisheryapp.model.User;
import com.binus.thesis.fisheryapp.service.NelayanService;
import com.binus.thesis.fisheryapp.service.PembeliService;
import com.binus.thesis.fisheryapp.service.UserService;
import com.binus.thesis.fisheryapp.validator.LoginValidator;
import com.binus.thesis.fisheryapp.validator.RegisterNelayanValidator;
import com.binus.thesis.fisheryapp.validator.RegisterPembeliValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.LOGIN)
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

    private final LoginValidator loginValidator;

    @PostMapping
    public BaseResponse<User> login(@Valid @RequestBody BaseRequest<BaseParameter<LoginRequestDto>> request) {
        BaseResponse<User> response = new BaseResponse<>();
        try {
            User userLogin = userService.login(request.getParameter().getData());
            loginValidator.validate(request.getParameter());
            response.setResult(userLogin);
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
