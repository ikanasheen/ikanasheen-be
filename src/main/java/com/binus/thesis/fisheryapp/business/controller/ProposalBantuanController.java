package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestApproveProposal;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateProposal;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseProposalBantuan;
import com.binus.thesis.fisheryapp.business.model.ProposalBantuan;
import com.binus.thesis.fisheryapp.business.service.ProposalBantuanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.PROPOSAL_BANTUAN)
@RequiredArgsConstructor
@Slf4j
public class ProposalBantuanController {

    private final ProposalBantuanService proposalBantuanService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse<ResponseProposalBantuan> create(@Valid @RequestBody BaseRequest<BaseParameter<RequestCreateProposal>> request) {
        BaseResponse<ResponseProposalBantuan> response = new BaseResponse<>();
        BaseParameter<RequestCreateProposal> parameter = request.getParameter();
        try {
            ResponseProposalBantuan proposalBantuan = proposalBantuanService.create(parameter.getData());
            response.setResult(proposalBantuan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_SUBMMIT_PROPOSAL));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idProposalBantuan}", method = RequestMethod.DELETE)
    public BaseResponse<ProposalBantuan> delete(@Valid @PathVariable(value = "idProposalBantuan") String idProposalBantuan) {
        BaseResponse<ProposalBantuan> response = new BaseResponse<>();
        try {
            proposalBantuanService.delete(idProposalBantuan);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_DELETE_DATA));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/{idProposalBantuan}", method = RequestMethod.GET)
    public BaseResponse<ResponseProposalBantuan> detail(@Valid @PathVariable(value = "idProposalBantuan") String idProposalBantuan) {
        BaseResponse<ResponseProposalBantuan> response = new BaseResponse<>();
        try {
            ResponseProposalBantuan proposalBantuan = proposalBantuanService.detail(idProposalBantuan);
            response.setResult(proposalBantuan);
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
    public BaseResponse<List<ResponseProposalBantuan>> retrieve(@Valid @RequestBody BaseRequest<BaseParameter<ProposalBantuan>> request) {
        BaseResponse<List<ResponseProposalBantuan>> response = new BaseResponse<>();
        try {
            response = proposalBantuanService.retrieve(request);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/approval", method = RequestMethod.POST)
    public BaseResponse<ResponseProposalBantuan> approval(@Valid @RequestBody BaseRequest<BaseParameter<RequestApproveProposal>> request) {
        BaseResponse<ResponseProposalBantuan> response = new BaseResponse<>();
        RequestApproveProposal data = request.getParameter().getData();
        try {
            ResponseProposalBantuan transaksi = proposalBantuanService.approval(data);
            response.setResult(transaksi);
            response.setStatus(Status.SUCCESS(data.getIsApprove().equalsIgnoreCase("Ya")
                    ? GlobalMessage.Resp.SUCCESS_APPROVE_PROPOSAL
                    : GlobalMessage.Resp.SUCCESS_REJECT_PROPOSAL
            ));
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
