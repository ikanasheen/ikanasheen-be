package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

@Data
public class RequestApproveProposal {

    private String idProposalBantuan;

    private String catatan;

    private String isApprove;
}
