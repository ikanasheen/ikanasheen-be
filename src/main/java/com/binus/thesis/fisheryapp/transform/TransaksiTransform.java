package com.binus.thesis.fisheryapp.transform;

import com.binus.thesis.fisheryapp.dto.request.RequestCreateTransaksi;
import com.binus.thesis.fisheryapp.dto.request.RequestUpdateTransaksi;
import com.binus.thesis.fisheryapp.dto.response.ResponseTransaksi;
import com.binus.thesis.fisheryapp.model.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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
}
