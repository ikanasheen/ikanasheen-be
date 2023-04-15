package com.binus.thesis.fisheryapp.business.service;


import com.binus.thesis.fisheryapp.business.dto.response.dashboard.ResponseDashboardIkan;
import com.binus.thesis.fisheryapp.business.dto.response.dashboard.ResponseDashboardNelayan;
import com.binus.thesis.fisheryapp.business.dto.response.dashboard.ResponseDashboardSosialisasi;
import com.binus.thesis.fisheryapp.business.dto.response.dashboard.ResponseDashboardTransaksi;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.model.Sosialisasi;
import com.binus.thesis.fisheryapp.business.model.Transaksi;
import com.binus.thesis.fisheryapp.business.transform.DashboardTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardTransform dashboardTransform;

    private final IkanService ikanService;
    private final NelayanService nelayanService;
    private final SosialisasiService sosialisasiService;
    private final TransaksiService transaksiService;

    public ResponseDashboardIkan ikan() {
        return dashboardTransform.toResponseDashboardIkan(
                ikanService.countIkan()
        );
    }

    public ResponseDashboardNelayan nelayan() {
        List<Nelayan> listNelayan = nelayanService.findAll();
        return dashboardTransform.toResponseDashboardNelayan(
                (long) listNelayan.size(),
                listNelayan.stream().filter(nelayan -> nelayan.getUser().getStatus().equalsIgnoreCase("ACTIVE")).count(),
                listNelayan.stream().filter(nelayan -> nelayan.getUser().getStatus().equalsIgnoreCase("INACTIVE")).count()
        );
    }

    public ResponseDashboardTransaksi transaksi() {
        List<Transaksi> listTransaksi = transaksiService.findAll();
        return dashboardTransform.toResponseDashboardTransaksi(
                (long) listTransaksi.size(),
                listTransaksi.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("DIAJUKAN")).count(),
                listTransaksi.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("NEGO")).count(),
                listTransaksi.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("DIPROSES")).count(),
                listTransaksi.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("DIBATALKAN")).count(),
                listTransaksi.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("SELESAI")).count()
        );
    }

    public ResponseDashboardSosialisasi sosialisasi() {
        List<Sosialisasi> listSosialisasi = sosialisasiService.findAll();
        return dashboardTransform.toResponseDashboardSosialisasi(
                (long) listSosialisasi.size(),
                listSosialisasi.stream().filter(sosialisasi -> sosialisasi.getJenisKonten().equalsIgnoreCase("BERITA")).count(),
                listSosialisasi.stream().filter(sosialisasi -> sosialisasi.getJenisKonten().equalsIgnoreCase("INFORMASI")).count(),
                listSosialisasi.stream().filter(sosialisasi -> sosialisasi.getJenisKonten().equalsIgnoreCase("PENGEMBANGAN_DIRI")).count()
        );
    }
}
