package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.request.RequestApproveNegoTransaksi;
import com.binus.thesis.fisheryapp.dto.request.RequestCreateTransaksi;
import com.binus.thesis.fisheryapp.dto.request.RequestProsesTransaksi;
import com.binus.thesis.fisheryapp.dto.request.RequestUpdateTransaksi;
import com.binus.thesis.fisheryapp.dto.response.ResponseTransaksi;
import com.binus.thesis.fisheryapp.model.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        PembeliTransform.class
})
public interface TransaksiTransform {

    @Named("toResponseTransaksi")
    @Mapping(target = "idTransaksi", source = "transaksi.idTransaksi")
    @Mapping(target = "namaIkan", source = "transaksi.ikan.namaIkan")
    @Mapping(target = "ukuran", source = "transaksi.ikan.ukuran")
    @Mapping(target = "jumlah", source = "transaksi.jumlah")
    @Mapping(target = "hargaAwal", source = "transaksi.hargaAwal")
    @Mapping(target = "hargaNego", source = "transaksi.hargaNego")
    @Mapping(target = "hargaAkhir", source = "transaksi.hargaAkhir")
    @Mapping(target = "tanggalDibutuhkan", source = "transaksi.tanggalDibutuhkan")
    @Mapping(target = "status", source = "transaksi.status")
    @Mapping(target = "namaNelayan", source = "transaksi.nelayan.namaLengkap")
    @Mapping(target = "namaPembeli", source = "transaksi.pembeli.namaLengkap")
    @Mapping(target = "alamatPembeli", source = "transaksi.alamat")
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
    @Mapping(target = "hargaAwal", source = "hargaAwal")
    @Mapping(target = "tanggalDibutuhkan", source = "request.tanggalDibutuhkan")
    @Mapping(target = "alamat", source = "request.alamat")
    @Mapping(target = "status", expression = "java(\"DIAJUKAN\")")
    @Mapping(target = "catatan", expression = "java(!request.getCatatan().isEmpty() ? request.getCatatan() : null)")
    Transaksi createTransaksitoEntity(RequestCreateTransaksi request, String idTransaksi, Pembeli pembeli, int hargaAwal);

    @Named("updateTransaksitoEntity")
    @Mapping(target = "idTransaksi", source = "request.idTransaksi")
    @Mapping(target = "idPembeli", source = "pembeli.idPembeli")
    @Mapping(target = "idIkan", source = "request.idIkan")
    @Mapping(target = "jumlah", source = "request.jumlah")
    @Mapping(target = "hargaAwal", source = "hargaAwal")
    @Mapping(target = "tanggalDibutuhkan", source = "request.tanggalDibutuhkan")
    @Mapping(target = "alamat", source = "request.alamat")
    @Mapping(target = "status", expression = "java(\"DIAJUKAN\")")
    @Mapping(target = "catatan", expression = "java(!request.getCatatan().isEmpty() ? request.getCatatan() : null)")
    Transaksi updateTransaksitoEntity(RequestUpdateTransaksi request, Pembeli pembeli, int hargaAwal);

    @Named("prosesTransaksitoEntity")
    @Mapping(target = "idNelayan", source = "nelayan.idNelayan")
    @Mapping(target = "hargaNego", expression = "java(request.getIsNego().equals(\"Ya\") ? request.getHargaNego() : transaksi.getHargaNego())")
    @Mapping(target = "hargaAkhir", expression = "java(request.getIsNego().equals(\"Ya\") ? 0 : transaksi.getHargaAwal())")
    @Mapping(target = "status", expression = "java(request.getIsNego().equals(\"Ya\") ? \"NEGO\" : \"DIPROSES\")")
    Transaksi prosesTransaksitoEntity(@MappingTarget Transaksi transaksi, RequestProsesTransaksi request, Nelayan nelayan);

    @Named("approvalNegotoEntity")
    @Mapping(target = "hargaNego", expression = "java(request.getIsApprove().equals(\"Ya\") ? transaksi.getHargaNego() : 0)")
    @Mapping(target = "hargaAkhir", expression = "java(request.getIsApprove().equals(\"Ya\") ? transaksi.getHargaNego() : 0)")
    @Mapping(target = "status", expression = "java(request.getIsApprove().equals(\"Ya\") ? \"DIPROSES\" : \"DIAJUKAN\")")
    @Mapping(target = "idNelayan", expression = "java(request.getIsApprove().equals(\"Ya\") ? transaksi.getIdNelayan() : null)")
    Transaksi approvalNegotoEntity(@MappingTarget Transaksi transaksi, RequestApproveNegoTransaksi request);
}
