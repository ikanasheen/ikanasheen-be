package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestChangePassword;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseRole;
import com.binus.thesis.fisheryapp.business.model.Role;
import com.binus.thesis.fisheryapp.business.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.ROLE)
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @RequestMapping(value = "/{idRole}", method = RequestMethod.GET)
    public BaseResponse<Role> detail(@Valid @PathVariable(value = "idRole") int idRole) {
        BaseResponse<Role> response = new BaseResponse<>();
        try {
            response.setResult(roleService.detail(idRole));
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
    public BaseResponse<List<Role>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<Role>> request) {
        BaseResponse<List<Role>> response = new BaseResponse<>();
        try {
            response = roleService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
