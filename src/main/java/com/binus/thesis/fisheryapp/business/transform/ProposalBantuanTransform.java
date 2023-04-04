package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.model.ProposalBantuan;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateProposal;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseProposalBantuan;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        BantuanTersediaTransformImpl.class,
        DokumenTransform.class
})
public interface ProposalBantuanTransform {

    @Named("createProposalntoEntity")
    @Mapping(target = "idProposalBantuan", source = "idProposal")
    @Mapping(target = "idBantuan", source = "request.idBantuan")
    @Mapping(target = "idDokumen", source = "request.dokumen.id")
    @Mapping(target = "idNelayan", source = "idNelayan")
    @Mapping(target = "tanggalDiajukan", source = "dateNow")
    @Mapping(target = "statusProposal", expression = "java(\"DIAJUKAN\")")
    ProposalBantuan createProposaltoEntity(RequestCreateProposal request, String idProposal, String idNelayan, String dateNow);

    @Named("buildResponseProposal")
    @Mapping(target = "idNelayan", source = "proposal.nelayan.idNelayan")
    @Mapping(target = "idUserNelayan", source = "proposal.nelayan.idUser")
    @Mapping(target = "namaNelayan", source = "proposal.nelayan.namaLengkap")
    @Mapping(target = "idBantuan", source = "proposal.bantuan.idBantuan")
    @Mapping(target = "namaBantuan", source = "proposal.bantuan.namaBantuan")
    @Mapping(target = "jenisBantuan", source = "proposal.bantuan.jenisBantuan")
    @Mapping(target = "dokumen", source = "proposal.dokumen", qualifiedByName = "buildResponseDokumen")
    ResponseProposalBantuan buildResponseProposal(ProposalBantuan proposal);

    @Named("buildResponseProposalList")
    @IterableMapping(qualifiedByName = "buildResponseProposal")
    List<ResponseProposalBantuan> buildResponseProposalList(List<ProposalBantuan> proposal);

    @Named("approvalProposaltoEntity")
    @Mapping(target = "statusProposal", expression = "java(isApprove.equalsIgnoreCase(\"Ya\") ? \"DISETUJUI\" : \"DITOLAK\")")
    @Mapping(target = "tanggalDisetujui", expression = "java(isApprove.equalsIgnoreCase(\"Ya\") ? dateNow : null)")
    @Mapping(target = "tanggalDitolak", expression = "java(isApprove.equalsIgnoreCase(\"Ya\") ? null : dateNow)")
    ProposalBantuan approvalProposaltoEntity(@MappingTarget ProposalBantuan proposal, String isApprove, String dateNow);
}
