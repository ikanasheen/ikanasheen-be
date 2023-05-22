package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateTopik;
import com.binus.thesis.fisheryapp.business.model.Topik;
import com.binus.thesis.fisheryapp.business.service.TopikService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.TOPIK)
@RequiredArgsConstructor
@Slf4j
public class TopikController {

    private final TopikService topikService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse<Topik> create(@Valid @RequestBody BaseRequest<BaseParameter<RequestCreateTopik>> request) {
        BaseResponse<Topik> response = new BaseResponse<>();
        BaseParameter<RequestCreateTopik> parameter = request.getParameter();
        try {
            Topik topik = topikService.create(parameter.getData());
            response.setResult(topik);
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
    public BaseResponse<Topik> update(@Valid @RequestBody BaseRequest<BaseParameter<Topik>> request) {
        BaseResponse<Topik> response = new BaseResponse<>();
        BaseParameter<Topik> parameter = request.getParameter();
        try {
            Topik topik = topikService.update(parameter.getData());
            response.setResult(topik);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idTopik}", method = RequestMethod.DELETE)
    public BaseResponse<Topik> delete(@Valid @PathVariable(value = "idTopik") int idTopik) {
        BaseResponse<Topik> response = new BaseResponse<>();
        try {
            topikService.delete(idTopik);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idTopik}", method = RequestMethod.GET)
    public BaseResponse<Topik> detail(@Valid @PathVariable(value = "idTopik") int idTopik) {
        BaseResponse<Topik> response = new BaseResponse<>();
        try {
            Topik topik = topikService.detail(idTopik);
            response.setResult(topik);
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
    public BaseResponse<List<Topik>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<Topik>> request) {
        BaseResponse<List<Topik>> response = new BaseResponse<>();
        try {
            response = topikService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
