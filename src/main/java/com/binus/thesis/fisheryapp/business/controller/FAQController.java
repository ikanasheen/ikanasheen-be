package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateFAQ;
import com.binus.thesis.fisheryapp.business.dto.request.RequestUpdateFAQ;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseFAQ;
import com.binus.thesis.fisheryapp.business.model.FAQ;
import com.binus.thesis.fisheryapp.business.service.FAQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.FAQ)
@RequiredArgsConstructor
@Slf4j
public class FAQController {

    private final FAQService faqService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse<ResponseFAQ> create(@Valid @RequestBody BaseRequest<BaseParameter<RequestCreateFAQ>> request) {
        BaseResponse<ResponseFAQ> response = new BaseResponse<>();
        BaseParameter<RequestCreateFAQ> parameter = request.getParameter();
        try {
            ResponseFAQ faq = faqService.create(parameter.getData());
            response.setResult(faq);
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
    public BaseResponse<ResponseFAQ> update(@Valid @RequestBody BaseRequest<BaseParameter<RequestUpdateFAQ>> request) {
        BaseResponse<ResponseFAQ> response = new BaseResponse<>();
        BaseParameter<RequestUpdateFAQ> parameter = request.getParameter();
        try {
            ResponseFAQ faq = faqService.update(parameter.getData());
            response.setResult(faq);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPDATE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idFAQ}", method = RequestMethod.DELETE)
    public BaseResponse<FAQ> delete(@Valid @PathVariable(value = "idFAQ") String idFAQ) {
        BaseResponse<FAQ> response = new BaseResponse<>();
        try {
            faqService.delete(idFAQ);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idFAQ}", method = RequestMethod.GET)
    public BaseResponse<ResponseFAQ> detail(@Valid @PathVariable(value = "idFAQ") String idFAQ) {
        BaseResponse<ResponseFAQ> response = new BaseResponse<>();
        try {
            ResponseFAQ faq = faqService.detail(idFAQ);
            response.setResult(faq);
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
    public BaseResponse<List<ResponseFAQ>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<FAQ>> request) {
        BaseResponse<List<ResponseFAQ>> response = new BaseResponse<>();
        try {
            response = faqService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
