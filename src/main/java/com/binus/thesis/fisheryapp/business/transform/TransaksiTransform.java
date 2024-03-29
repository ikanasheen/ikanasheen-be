package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestApproveNegoTransaksi;
import com.binus.thesis.fisheryapp.business.dto.request.RequestCreateTransaksi;
import com.binus.thesis.fisheryapp.business.dto.request.RequestProsesTransaksi;
import com.binus.thesis.fisheryapp.business.dto.request.RequestUpdateTransaksi;
import com.binus.thesis.fisheryapp.business.model.Ikan;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.model.Pembeli;
import com.binus.thesis.fisheryapp.business.model.Transaksi;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseTransaksi;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", uses = {
        PembeliTransform.class,
        ConvertTransform.class
})
public interface TransaksiTransform {

    @Named("toResponseTransaksi")
    @Mapping(target = "idTransaksi", source = "transaksi.idTransaksi")
    @Mapping(target = "idIkan", source = "transaksi.ikan.idIkan")
    @Mapping(target = "namaIkan", source = "transaksi.ikan.namaIkan")
    @Mapping(target = "ukuran", source = "transaksi.ikan.ukuran")
    @Mapping(target = "jumlah", source = "transaksi.jumlah")
    @Mapping(target = "hargaAwal", source = "transaksi.hargaAwal")
    @Mapping(target = "hargaNego", source = "transaksi.hargaNego")
    @Mapping(target = "hargaAkhir", source = "transaksi.hargaAkhir")
    @Mapping(target = "tanggalDibutuhkan", source = "transaksi.tanggalDibutuhkan")
    @Mapping(target = "status", source = "transaksi.status")
    @Mapping(target = "opsiPengiriman", source = "transaksi.opsiPengiriman")
    @Mapping(target = "namaNelayan", source = "transaksi.nelayan.namaLengkap")
    @Mapping(target = "alamatNelayan", source = "transaksi.nelayan.alamat")
    @Mapping(target = "kelurahanDesaNelayan", source = "transaksi.nelayan.kelurahanDesa")
    @Mapping(target = "kecamatanNelayan", source = "transaksi.nelayan.kecamatan")
    @Mapping(target = "namaPembeli", source = "transaksi.pembeli.namaLengkap")
    @Mapping(target = "alamatPembeli", source = "transaksi.alamatPembeli")
    @Mapping(target = "tanggalDiproses", source = "transaksi.tanggalDiproses")
    @Mapping(target = "tanggalSelesai", source = "transaksi.tanggalSelesai")
    @Mapping(target = "hargaAkumulasiNego", expression = "java(!transaksi.getHargaNego().equals(0) ? amountCalculation(transaksi.getHargaNego(), transaksi.getJumlah()) : \"0\")")
    ResponseTransaksi toResponseTransaksi(
            Transaksi transaksi
    );

    @Named("toResponseTransaksiList")
    @IterableMapping(qualifiedByName = "toResponseTransaksi")
    List<ResponseTransaksi> toResponseTransaksiList(List<Transaksi> transaksiList);

    @Named("createTransaksitoEntity")
    @Mapping(target = "idTransaksi", source = "idTransaksi")
    @Mapping(target = "idPembeli", source = "pembeli.idPembeli")
    @Mapping(target = "idIkan", source = "request.idIkan")
    @Mapping(target = "jumlah", source = "request.jumlah")
    @Mapping(target = "hargaAwal", source = "ikan.hargaDasar")
    @Mapping(target = "hargaNego", expression = "java(\"0\")")
    @Mapping(target = "hargaAkhir", expression = "java(\"0\")")
    @Mapping(target = "tanggalDibutuhkan", source = "request.tanggalDibutuhkan")
    @Mapping(target = "tanggalDiajukan", source = "dateNow")
    @Mapping(target = "alamatPembeli", source = "request.alamatPembeli")
    @Mapping(target = "opsiPengiriman", source = "request.opsiPengiriman")
    @Mapping(target = "status", expression = "java(\"DIAJUKAN\")")
    @Mapping(target = "catatan", expression = "java(request.getCatatan() != null && !request.getCatatan().isEmpty() ? request.getCatatan() : null)")
    Transaksi createTransaksitoEntity(RequestCreateTransaksi request, String idTransaksi, Pembeli pembeli, Ikan ikan, String dateNow);

    @Named("updateTransaksitoEntity")
    @Mapping(target = "idTransaksi", source = "request.idTransaksi")
    @Mapping(target = "idPembeli", source = "pembeli.idPembeli")
    @Mapping(target = "idIkan", source = "request.idIkan")
    @Mapping(target = "jumlah", source = "request.jumlah")
    @Mapping(target = "hargaAwal", source = "ikan.hargaDasar")
    @Mapping(target = "tanggalDibutuhkan", source = "request.tanggalDibutuhkan")
    @Mapping(target = "alamatPembeli", source = "request.alamatPembeli")
    @Mapping(target = "status", expression = "java(\"DIAJUKAN\")")
    @Mapping(target = "catatan", expression = "java(request.getCatatan() != null && !request.getCatatan().isEmpty() ? request.getCatatan() : null)")
    Transaksi updateTransaksitoEntity(RequestUpdateTransaksi request, Pembeli pembeli, Ikan ikan);

    @Named("prosesTransaksitoEntity")
    @Mapping(target = "idNelayan", source = "nelayan.idNelayan")
    @Mapping(target = "hargaNego", expression = "java(request.getIsNego().equalsIgnoreCase(\"Ya\") ? request.getHargaNego() : transaksi.getHargaNego())")
    @Mapping(target = "hargaAkhir", expression = "java(request.getIsNego().equalsIgnoreCase(\"Ya\") ? \"0\" : amountCalculation(transaksi.getIkan().getHargaDasar(), transaksi.getJumlah()))")
    @Mapping(target = "status", expression = "java(request.getIsNego().equalsIgnoreCase(\"Ya\") ? \"NEGO\" : \"DIPROSES\")")
    @Mapping(target = "tanggalDiproses", expression = "java(request.getIsNego().equalsIgnoreCase(\"Ya\") ? null : dateNow)")
    Transaksi prosesTransaksitoEntity(@MappingTarget Transaksi transaksi, RequestProsesTransaksi request, Nelayan nelayan, String dateNow);

    @Named("approvalNegotoEntity")
    @Mapping(target = "hargaNego", expression = "java(request.getIsApprove().equalsIgnoreCase(\"Ya\") ? transaksi.getHargaNego() : \"0\")")
    @Mapping(target = "hargaAkhir", expression = "java(request.getIsApprove().equalsIgnoreCase(\"Ya\") ? amountCalculation(transaksi.getHargaNego(), transaksi.getJumlah()) : \"0\")")
    @Mapping(target = "status", expression = "java(request.getIsApprove().equalsIgnoreCase(\"Ya\") ? \"DIPROSES\" : \"DIAJUKAN\")")
    @Mapping(target = "idNelayan", expression = "java(request.getIsApprove().equalsIgnoreCase(\"Ya\") ? transaksi.getIdNelayan() : null)")
    @Mapping(target = "tanggalDiproses", expression = "java(request.getIsApprove().equalsIgnoreCase(\"Ya\") ? dateNow : null)")
    Transaksi approvalNegotoEntity(@MappingTarget Transaksi transaksi, RequestApproveNegoTransaksi request, String dateNow);

    @Named("completeCancelProsesPengirimanTransaksitoEntity")
    default Transaksi completeCancelProsesPengirimanTransaksitoEntity(Transaksi transaksi, String action, String catatanPengiriman) {
        if (action.equals("COMPLETE")) {
            transaksi.setStatus("SELESAI");
            transaksi.setTanggalSelesai(LocalDate.now().toString());
        } else if (action.equals("CANCEL")){
            if (!transaksi.getStatus().equals("DIAJUKAN")) {
                throw new ApplicationException(Status.INVALID(GlobalMessage.Error.CANT_CANCEL
                        .replaceAll(GlobalMessage.Error.paramVariable.get(0), transaksi.getStatus().toLowerCase())
                ));
            }
            transaksi.setStatus("DIBATALKAN");
        } else if (action.equals("KIRIM")) {
            if (!transaksi.getStatus().equals("DIPROSES")) {
                throw new ApplicationException(Status.INVALID(GlobalMessage.Error.CANT_PROCCESS
                        .replaceAll(GlobalMessage.Error.paramVariable.get(0), transaksi.getStatus().toLowerCase())
                ));
            }
            transaksi.setStatus("DIKIRIM");
            transaksi.setTanggalDikirim(LocalDate.now().toString());
            if (catatanPengiriman != null && !catatanPengiriman.isEmpty()){
                transaksi.setCatatanPengiriman(catatanPengiriman);
            }
        } else {
            if (!transaksi.getStatus().equals("DIPROSES")) {
                throw new ApplicationException(Status.INVALID(GlobalMessage.Error.CANT_PROCCESS
                        .replaceAll(GlobalMessage.Error.paramVariable.get(0), transaksi.getStatus().toLowerCase())
                ));
            }
            transaksi.setStatus("SIAP_DIAMBIL");
            transaksi.setTanggalSiapDiambil(LocalDate.now().toString());
        }
        return transaksi;
    }

    @Named("amountCalculation")
    default String amountCalculation(String amount1, String amount2){
        BigDecimal amount = new BigDecimal(amount1).multiply(new BigDecimal(amount2));
        return amount.toString();
    }
}
