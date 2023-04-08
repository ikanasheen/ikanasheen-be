package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestUpdateUser;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseUser;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseUserProfile;
import com.binus.thesis.fisheryapp.business.model.User;
import com.binus.thesis.fisheryapp.business.service.UserService;
import com.binus.thesis.fisheryapp.business.dto.request.RequestChangePassword;
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

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse<ResponseUser> create(@Valid @RequestBody BaseRequest<BaseParameter<User>> request) {
        BaseResponse<ResponseUser> response = new BaseResponse<>();
        BaseParameter<User> parameter = request.getParameter();
        try {
            response.setResult(userService.create(parameter.getData()));
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
    public BaseResponse<ResponseUser> update(@Valid @RequestBody BaseRequest<BaseParameter<RequestUpdateUser>> request) {
        BaseResponse<ResponseUser> response = new BaseResponse<>();
        BaseParameter<RequestUpdateUser> parameter = request.getParameter();
        try {
            response.setResult(userService.update(parameter.getData()));
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public BaseResponse<ResponseUser> changePassword(@Valid @RequestBody BaseRequest<BaseParameter<RequestChangePassword>> request) {
        BaseResponse<ResponseUser> response = new BaseResponse<>();
        BaseParameter<RequestChangePassword> parameter = request.getParameter();
        try {
            response.setResult(userService.changePassword(parameter.getData()));
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idUser}", method = RequestMethod.DELETE)
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

    @RequestMapping(value = "/{idUser}", method = RequestMethod.GET)
    public BaseResponse<ResponseUser> detail(@Valid @PathVariable(value = "idUser") String idUser) {
        BaseResponse<ResponseUser> response = new BaseResponse<>();
        try {
            response.setResult(userService.detail(idUser));
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

    @RequestMapping(value = "/profile/{idUser}", method = RequestMethod.GET)
    public BaseResponse<Object> profile(@Valid @PathVariable(value = "idUser") String idUser) {
        BaseResponse<Object> response = new BaseResponse<>();
        try {
            response.setResult(userService.profile(idUser));
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
