package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateBantuan;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseBantuan;
import com.binus.thesis.fisheryapp.business.model.BantuanTersedia;
import com.binus.thesis.fisheryapp.business.service.BantuanTersediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.BANTUAN)
@RequiredArgsConstructor
@Slf4j
public class BantuanTersediaController {

    private final BantuanTersediaService bantuanTersediaService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse<ResponseBantuan> create(@Valid @RequestBody BaseRequest<BaseParameter<RequestCreateBantuan>> request) {
        BaseResponse<ResponseBantuan> response = new BaseResponse<>();
        BaseParameter<RequestCreateBantuan> parameter = request.getParameter();
        try {
            ResponseBantuan bantuanTersedia = bantuanTersediaService.create(parameter.getData());
            response.setResult(bantuanTersedia);
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
    public BaseResponse<ResponseBantuan> update(@Valid @RequestBody BaseRequest<BaseParameter<BantuanTersedia>> request) {
        BaseResponse<ResponseBantuan> response = new BaseResponse<>();
        BaseParameter<BantuanTersedia> parameter = request.getParameter();
        try {
            ResponseBantuan bantuan = bantuanTersediaService.update(parameter.getData());
            response.setResult(bantuan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }


    @RequestMapping(value = "/{idBantuanTersedia}", method = RequestMethod.DELETE)
    public BaseResponse<BantuanTersedia> delete(@Valid @PathVariable(value = "idBantuanTersedia") String idBantuanTersedia) {
        BaseResponse<BantuanTersedia> response = new BaseResponse<>();
        try {
            bantuanTersediaService.delete(idBantuanTersedia);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idBantuanTersedia}", method = RequestMethod.GET)
    public BaseResponse<ResponseBantuan> detail(@Valid @PathVariable(value = "idBantuanTersedia") String idBantuanTersedia) {
        BaseResponse<ResponseBantuan> response = new BaseResponse<>();
        try {
            ResponseBantuan bantuanTersedia = bantuanTersediaService.detail(idBantuanTersedia);
            response.setResult(bantuanTersedia);
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
    public BaseResponse<List<ResponseBantuan>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<BantuanTersedia>> request) {
        BaseResponse<List<ResponseBantuan>> response = new BaseResponse<>();
        try {
            response = bantuanTersediaService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
