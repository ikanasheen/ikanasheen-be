package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.dto.request.RequestApproveProposal;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateProposal;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseProposalBantuan;
import com.binus.thesis.fisheryapp.business.model.BantuanTersedia;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.model.ProposalBantuan;
import com.binus.thesis.fisheryapp.business.repository.ProposalBantuanRepository;
import com.binus.thesis.fisheryapp.business.service.specification.ProposalBantuanSpecification;
import com.binus.thesis.fisheryapp.business.transform.ProposalBantuanTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProposalBantuanService {

    private final ProposalBantuanRepository repository;

    private final ProposalBantuanSpecification specification;

    private final ProposalBantuanTransform transform;
    private final PageTransform pageTransform;

    private final NelayanService nelayanService;

    private final BantuanTersediaService bantuanService;

    public ResponseProposalBantuan create(RequestCreateProposal req) {
        Nelayan nelayan = nelayanService.findByIdUser(req.getIdUserNelayan());
        checkProposal(nelayan.getIdNelayan(), req.getIdBantuan());
        String idProposalBantuan = GeneratorUtils.generateId("PROP"+req.getIdBantuan().substring(0,3), new Date(), 3);
        ProposalBantuan proposal = repository.save(
                transform.createProposaltoEntity(req, idProposalBantuan, nelayan.getIdNelayan(), GeneratorUtils.generateTimeStamp(LocalDateTime.now()))
        );
        return transform.buildResponseProposal(proposal);
    }

    public void delete(String idProposalBantuan) {
        getProposalBantuan(idProposalBantuan);
        repository.deleteById(idProposalBantuan);
    }

    public ResponseProposalBantuan detail(String idProposalBantuan) {
        return transform.buildResponseProposal(getProposalBantuan(idProposalBantuan));
    }

    public ResponseProposalBantuan approval(RequestApproveProposal req) {
        ProposalBantuan proposal = getProposalBantuan(req.getIdProposalBantuan());
        BantuanTersedia bantuan = bantuanService.getBantuanTersedia(proposal.getIdBantuan());
        if (bantuan.getKuotaTersisa().equalsIgnoreCase("0")) {
            throw new ApplicationException(Status.INVALID(GlobalMessage.Error.KUOTA_EXCEED));
        }
        ProposalBantuan savedProposal = repository.saveAndFlush(
                transform.approvalProposaltoEntity(proposal, req.getIsApprove(), GeneratorUtils.generateTimeStamp(LocalDateTime.now()))
        );
        if (req.getIsApprove().equalsIgnoreCase("Ya"))
            bantuanService.updateKuotaTersisa(bantuan);
        return transform.buildResponseProposal(savedProposal);
    }

    public BaseResponse<List<ResponseProposalBantuan>> retrieve(BaseRequest<BaseParameter<ProposalBantuan>> request) {
        BaseResponse<List<ResponseProposalBantuan>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<ProposalBantuan> data = repository.findAll(
                specification.predicate(request.getParameter()), pageable
        );

        Paging paging = pageTransform.toPage(
                request.getPaging().getPage(),
                limit,
                data.getTotalPages(),
                data.getTotalElements()
        );

        response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
        response.setPaging(paging);
        response.setResult(transform.buildResponseProposalList(data.getContent()));

        return response;
    }

    public ProposalBantuan getProposalBantuan(String idProposalBantuan) {
        Optional<ProposalBantuan> proposalRepo = repository.findById(idProposalBantuan);
        if (proposalRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "proposal bantuan")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idProposalBantuan))
            );
        }

        return proposalRepo.get();
    }

    public void checkProposal(String idNelayan, String idBantuan) {
        List<ProposalBantuan> proposal = repository.findByNelayanIdNelayanAndBantuanIdBantuanAndStatusProposalIn(
                idNelayan,
                idBantuan,
                Arrays.asList("DIAJUKAN", "DISETUJUI")
        );
        if (!proposal.isEmpty())
            throw new ApplicationException(Status.INVALID(GlobalMessage.Error.CANT_PROPOSE
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), proposal.get(0).getStatusProposal())
            ));
    }
}
