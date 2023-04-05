package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateBantuan;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseBantuan;
import com.binus.thesis.fisheryapp.business.model.BantuanTersedia;
import com.binus.thesis.fisheryapp.business.model.Dokumen;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = DokumenTransform.class
)
public interface BantuanTersediaTransform {

    @Named("buildResponseBantuan")
    @Mapping(target = "dokumen", source = "bantuan.dokumen", qualifiedByName = "buildResponseDokumenList")
    ResponseBantuan buildResponseBantuan(BantuanTersedia bantuan);

    @Named("buildResponseBantuanList")
    @IterableMapping(qualifiedByName = "buildResponseBantuan")
    List<ResponseBantuan> buildResponseBantuanList(List<BantuanTersedia> bantuan);

    @Named("createBantuantoEntity")
    @Mapping(target = "idBantuan", source = "idBantuan")
    @Mapping(target = "jenisBantuan", source = "request.jenisBantuan")
    @Mapping(target = "statusBantuan", expression = "java(\"ACTIVE\")")
    @Mapping(target = "kuotaTersisa", source = "request.kuota")
    @Mapping(target = "namaBantuan", source = "request.namaBantuan")
    @Mapping(target = "idDokumen", source = "dokumen.id")
    @Mapping(target = "dokumen", source = "dokumen")
    BantuanTersedia createBantuantoEntity(
            @MappingTarget BantuanTersedia bantuan,
            String idBantuan,
            RequestCreateBantuan request,
            Dokumen dokumen
    );

    @Named("updateBantuantoEntity")
    @Mapping(target = "idBantuan", source = "idBantuan")
    @Mapping(target = "namaBantuan", expression = "java(bantuan.getNamaBantuan() == null || bantuan.getNamaBantuan().isEmpty() ? bantuanRepo.getNamaBantuan() : bantuan.getNamaBantuan())")
    @Mapping(target = "jenisBantuan", expression = "java(bantuan.getJenisBantuan() == null || bantuan.getJenisBantuan().isEmpty() ? bantuanRepo.getJenisBantuan() : bantuan.getJenisBantuan())")
    @Mapping(target = "statusBantuan", expression = "java(bantuan.getStatusBantuan() == null || bantuan.getStatusBantuan().isEmpty() ? bantuanRepo.getStatusBantuan() : bantuan.getStatusBantuan())")
    @Mapping(target = "kuota", ignore = true)
    @Mapping(target = "kuotaTersisa", ignore = true)
    BantuanTersedia updateBantuantoEntity(@MappingTarget BantuanTersedia bantuanRepo, BantuanTersedia bantuan);

    @Named("updateKuotaTersisa")
    @Mapping(target = "kuotaTersisa", source = "kuotaTersisa")
    @Mapping(target = "statusBantuan", source = "status")
    BantuanTersedia updateKuotaTersisa(@MappingTarget BantuanTersedia bantuan, String kuotaTersisa, String status);

    @Named("updateKuota")
    default BantuanTersedia updateKuota(BantuanTersedia bantuanRepo, BantuanTersedia reqBantuan) {
        int reqKuota = Integer.parseInt(reqBantuan.getKuota());
        int kuotaRepo = Integer.parseInt(bantuanRepo.getKuota());
        int tersisaRepo = Integer.parseInt(bantuanRepo.getKuotaTersisa());
        int selisih = kuotaRepo-tersisaRepo;
        if (reqBantuan.getKuota() != null && reqKuota != kuotaRepo) {
            if (reqKuota < selisih) {
                throw new ApplicationException(Status.INVALID(GlobalMessage.Error.CANT_UPDATE_KUOTA
                        .replaceAll(GlobalMessage.Error.paramVariable.get(0), String.valueOf(selisih))
                ));
            }
            bantuanRepo.setKuota(String.valueOf(reqKuota));
            bantuanRepo.setKuotaTersisa(String.valueOf(reqKuota-selisih));
        }
        return bantuanRepo;
    }
}
