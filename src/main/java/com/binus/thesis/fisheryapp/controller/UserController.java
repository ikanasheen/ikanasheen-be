package com.binus.thesis.fisheryapp.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.dto.request.RequestChangePassword;
import com.binus.thesis.fisheryapp.dto.request.RequestUpdateUser;
import com.binus.thesis.fisheryapp.dto.response.ResponseUser;
import com.binus.thesis.fisheryapp.model.User;
import com.binus.thesis.fisheryapp.service.UserService;
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

    @PutMapping()
    public BaseResponse<User> create(@Valid @RequestBody BaseRequest<BaseParameter<User>> request) {
        BaseResponse<User> response = new BaseResponse<>();
        BaseParameter<User> parameter = request.getParameter();
        try {
            User user = userService.create(parameter.getData());
            response.setResult(user);
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
    public BaseResponse<User> update(@Valid @RequestBody BaseRequest<BaseParameter<RequestUpdateUser>> request) {
        BaseResponse<User> response = new BaseResponse<>();
        BaseParameter<RequestUpdateUser> parameter = request.getParameter();
        try {
            User user = userService.update(parameter.getData());
            response.setResult(user);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @PostMapping("/changepassword")
    public BaseResponse<User> changePassword(@Valid @RequestBody BaseRequest<BaseParameter<RequestChangePassword>> request) {
        BaseResponse<User> response = new BaseResponse<>();
        BaseParameter<RequestChangePassword> parameter = request.getParameter();
        try {
            User user = userService.changePassword(parameter.getData());
            response.setResult(user);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @DeleteMapping("/{idUser}")
    public BaseResponse<User> delete(@Valid @PathVariable(value = "idUser") String idUser) {
        BaseResponse<User> response = new BaseResponse<>();
        try {
            userService.delete(idUser);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @GetMapping("/{idUser}")
    public BaseResponse<User> detail(@Valid @PathVariable(value = "idUser") String idUser) {
        BaseResponse<User> response = new BaseResponse<>();
        try {
            User user = userService.detail(idUser);
            response.setResult(user);
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
    public BaseResponse<List<ResponseUser>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<User>> request) {
        BaseResponse<List<ResponseUser>> response = new BaseResponse<>();
        try {
            response = userService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
