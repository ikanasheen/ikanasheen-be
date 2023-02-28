package com.binus.thesis.fisheryapp.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.dto.request.LoginRequestDto;
import com.binus.thesis.fisheryapp.model.User;
import com.binus.thesis.fisheryapp.service.UserService;
import com.binus.thesis.fisheryapp.validator.LoginValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.USER)
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final LoginValidator loginValidator;

    @PostMapping("/retrieve")
    public BaseResponse<List<User>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<LoginRequestDto>> request) {
        BaseResponse<List<User>> response = new BaseResponse<>();
        try {
            List<User> userLogin = userService.retrieveList();
//            loginValidator.validate(request.getParameter());
            response.setResult(userLogin);
            response.setPaging(request.getPaging());
            response.getPaging().setTotalpage(1);
            response.getPaging().setTotalrecord(2);
            response.setStatus(Status.SUCCESS("Retrieve Data Success"));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
