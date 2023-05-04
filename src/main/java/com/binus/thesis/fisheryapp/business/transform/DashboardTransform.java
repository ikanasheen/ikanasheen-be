package com.binus.thesis.fisheryapp.business.transform;

import com.binus.thesis.fisheryapp.business.dto.response.dashboard.*;
import com.binus.thesis.fisheryapp.business.model.Ikan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DashboardTransform {

    @Named("toResponseDashboardIkan")
    @Mapping(target = "jumlahIkan", source = "jumlah")
    ResponseDashboardIkan toResponseDashboardIkan(Long jumlah);

    @Named("toResponseDashboardNelayan")
    @Mapping(target = "jumlahNelayan", source = "jumlahNelayan")
    @Mapping(target = "nelayanActive", source = "nelayanActive")
    @Mapping(target = "nelayanInactive", source = "nelayanInactive")
    ResponseDashboardNelayan toResponseDashboardNelayan(Long jumlahNelayan, Long nelayanActive, Long nelayanInactive);

    @Named("toResponseDashboardTransaksi")
    @Mapping(target = "jumlahTransaksi", source = "jumlahTransaksi")
    @Mapping(target = "transaksiDiajukan", source = "transaksiDiajukan")
    @Mapping(target = "transaksiDiproses", source = "transaksiDiproses")
    @Mapping(target = "transaksiNego", source = "transaksiNego")
    @Mapping(target = "transaksiDibatalkan", source = "transaksiDibatalkan")
    @Mapping(target = "transaksiSelesai", source = "transaksiSelesai")
    ResponseDashboardTransaksi toResponseDashboardTransaksi(
            Long jumlahTransaksi,
            Long transaksiDiajukan,
            Long transaksiDiproses,
            Long transaksiNego,
            Long transaksiDibatalkan,
            Long transaksiSelesai
    );

    @Named("toResponseDashboardBantuan")
    @Mapping(target = "bantuanTersedia", source = "bantuanTersedia")
    @Mapping(target = "bantuanDiajukan", source = "bantuanDiajukan")
    @Mapping(target = "bantuanTerproses", source = "bantuanTerproses")
    @Mapping(target = "bantuanDisetujui", source = "bantuanDisetujui")
    @Mapping(target = "bantuanDitolak", source = "bantuanDitolak")
    ResponseDashboardBantuan toResponseDashboardBantuan(
            Long bantuanTersedia,
            Long bantuanDiajukan,
            Long bantuanTerproses,
            Long bantuanDisetujui,
            Long bantuanDitolak
    );

    @Named("toResponseDashboardSosialisasi")
    @Mapping(target = "jumlahSosialisasi", source = "jumlahSosialisasi")
    @Mapping(target = "berita", source = "berita")
    @Mapping(target = "informasi", source = "informasi")
    @Mapping(target = "pengembanganDiri", source = "pengembanganDiri")
    ResponseDashboardSosialisasi toResponseDashboardSosialisasi(
            Long jumlahSosialisasi,
            Long berita,
            Long informasi,
            Long pengembanganDiri
    );
}
