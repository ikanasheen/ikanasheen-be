package com.binus.thesis.fisheryapp.business.service;


import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestDashboardTransaksi;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardTransform dashboardTransform;

    private final IkanService ikanService;
    private final NelayanService nelayanService;
    private final PembeliService pembeliService;
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

    public ResponseDashboardTransaksi transaksi(RequestDashboardTransaksi request) {
        List<Transaksi> listTransaksi = transaksiService.findAll();
        long jumlah = 0;
        long diajukan = 0;
        long nego = 0;
        long diproses = 0;
        long dibatalkan = 0;
        long selesai = 0;

        switch (request.getIdRole()) {
            case 1:
                jumlah = listTransaksi.size();
                diajukan = listTransaksi.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("DIAJUKAN")).count();
                diproses = listTransaksi.stream().filter(
                        transaksi -> !transaksi.getStatus().equalsIgnoreCase("DIAJUKAN")
                                && !transaksi.getStatus().equalsIgnoreCase("SELESAI")).count();
                selesai = listTransaksi.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("SELESAI")).count();
                break;
            case 3:
                String idNelayan = nelayanService.findByIdUser(request.getIdUser()).getIdNelayan();
                List<Transaksi> transaksiNelayan = listTransaksi.stream().filter(transaksi -> transaksi.getIdNelayan() != null && transaksi.getIdNelayan().equalsIgnoreCase(idNelayan)).collect(Collectors.toList());
                jumlah = transaksiNelayan.size();
                nego = transaksiNelayan.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("NEGO")).count();
                selesai = transaksiNelayan.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("SELESAI")).count();
                diproses = transaksiNelayan.stream().filter(
                        transaksi -> !transaksi.getStatus().equalsIgnoreCase("NEGO")
                                && !transaksi.getStatus().equalsIgnoreCase("SELESAI")).count();
                break;
            case 4:
                String idPembeli = pembeliService.findByIdUser(request.getIdUser()).getIdPembeli();
                List<Transaksi> transaksiPembeli = listTransaksi.stream().filter(transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).collect(Collectors.toList());
                jumlah = transaksiPembeli.size();
                diajukan = transaksiPembeli.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("DIAJUKAN")).count();
                nego = transaksiPembeli.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("NEGO")).count();
                selesai = transaksiPembeli.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("SELESAI")).count();
                diproses = transaksiPembeli.stream().filter(
                        transaksi -> !transaksi.getStatus().equalsIgnoreCase("DIAJUKAN")
                                && !transaksi.getStatus().equalsIgnoreCase("NEGO")
                                && !transaksi.getStatus().equalsIgnoreCase("SELESAI")).count();
                dibatalkan = transaksiPembeli.stream().filter(transaksi -> transaksi.getStatus().equalsIgnoreCase("DIBATALKAN")).count();
                break;
            default:
                throw new ApplicationException(Status.INVALID(GlobalMessage.Error.INVALID_PARAMETER));
        }
        return dashboardTransform.toResponseDashboardTransaksi(
                jumlah,
                diajukan,
                diproses,
                nego,
                dibatalkan,
                selesai
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
