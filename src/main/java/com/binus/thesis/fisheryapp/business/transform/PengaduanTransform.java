package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.dto.request.RequestCreatePengaduan;
import com.binus.thesis.fisheryapp.business.dto.request.RequestPenangananPengaduan;
import com.binus.thesis.fisheryapp.business.dto.response.ResponsePengaduan;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.model.Pengaduan;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = NelayanTransform.class
)
public interface PengaduanTransform {

    @Named("createPengaduantoEntity")
    @Mapping(target = "idPengaduan", source = "idPengaduan")
    @Mapping(target = "idNelayan", source = "nelayan.idNelayan")
    @Mapping(target = "noTelepon", expression = "java(request.getNoTelepon() != null && !request.getNoTelepon().isEmpty() ? request.getNoTelepon() : null)")
    @Mapping(target = "email", expression = "java(request.getEmail() != null && !request.getEmail().isEmpty() ? request.getEmail() : null)")
    @Mapping(target = "aduan", source = "request.aduan")
    @Mapping(target = "tanggalPengaduan", source = "dateNow")
    @Mapping(target = "status", constant = "BELUM_TERJAWAB")
    Pengaduan createPengaduantoEntity(RequestCreatePengaduan request, Nelayan nelayan, String idPengaduan, String dateNow);

    @Named("penanganantoEntity")
    @Mapping(target = "idPengaduan", source = "request.idPengaduan")
    @Mapping(target = "penanganan", source = "request.penanganan")
    @Mapping(target = "tanggalPenanganan", source = "dateNow")
    @Mapping(target = "status", constant = "TERJAWAB")
    Pengaduan penanganantoEntity(@MappingTarget Pengaduan pengaduan, RequestPenangananPengaduan request, String dateNow);

    @Named("buildResponsePengaduan")
    @Mapping(target = "nelayan", source = "nelayan", qualifiedByName = "buildResponseNelayan")
    @Mapping(target = "namaNelayan", source = "nelayan.namaLengkap")
    ResponsePengaduan buildResponsePengaduan(Pengaduan pengaduan);

    @Named("buildResponsePengaduanList")
    @IterableMapping(qualifiedByName = "buildResponsePengaduan")
    List<ResponsePengaduan> buildResponsePengaduanList(List<Pengaduan> entities);
}
