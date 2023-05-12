package com.binus.thesis.fisheryapp.business.service;


import com.binus.thesis.fisheryapp.base.constant.GlobalConstant;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestDashboardPerRole;
import com.binus.thesis.fisheryapp.business.dto.response.dashboard.*;
import com.binus.thesis.fisheryapp.business.model.*;
import com.binus.thesis.fisheryapp.business.transform.DashboardTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardTransform dashboardTransform;

    private final BantuanTersediaService bantuanService;
    private final IkanService ikanService;
    private final NelayanService nelayanService;
    private final PembeliService pembeliService;
    private final ProposalBantuanService proposalService;
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

    public ResponseDashboardTransaksi transaksi(RequestDashboardPerRole request) {
        List<Transaksi> listTransaksi = transaksiService.findAll();
        long jumlah = 0;
        long diajukan = 0;
        long nego = 0;
        long diproses = 0;
        long dibatalkan = 0;
        long selesai = 0;

        switch (request.getIdRole()) {
            case 1:
            case 2:
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
                                && !transaksi.getStatus().equalsIgnoreCase("SELESAI")
                                && !transaksi.getStatus().equalsIgnoreCase("DIBATALKAN")).count();
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

    public ResponseDashboardBantuan bantuan(RequestDashboardPerRole request) {
        List<BantuanTersedia> listBantuan = bantuanService.findAll();
        List<ProposalBantuan> listProposal = proposalService.findAll();
        long tersedia = listBantuan.stream().filter(bantuan -> bantuan.getStatusBantuan().equalsIgnoreCase("ACTIVE")).count();
        long diajukan;
        long terproses = 0;
        long disetujui = 0;
        long ditolak = 0;

        switch (request.getIdRole()) {
            case 1:
            case 2:
                diajukan = listProposal.stream().filter(proposal -> proposal.getStatusProposal().equalsIgnoreCase("DIAJUKAN")).count();
                terproses = listProposal.stream().filter(proposal -> !proposal.getStatusProposal().equalsIgnoreCase("DIAJUKAN")).count();
                break;
            case 3:
                String idNelayan = nelayanService.findByIdUser(request.getIdUser()).getIdNelayan();
                List<ProposalBantuan> proposalNelayan = listProposal.stream().filter(proposal -> proposal.getIdNelayan() != null && proposal.getIdNelayan().equalsIgnoreCase(idNelayan)).collect(Collectors.toList());
                diajukan = proposalNelayan.stream().filter(proposal -> proposal.getStatusProposal().equalsIgnoreCase("DIAJUKAN")).count();
                disetujui = proposalNelayan.stream().filter(proposal -> proposal.getStatusProposal().equalsIgnoreCase("DISETUJUI")).count();
                ditolak = proposalNelayan.stream().filter(proposal -> proposal.getStatusProposal().equalsIgnoreCase("DITOLAK")).count();
                break;
            default:
                throw new ApplicationException(Status.INVALID(GlobalMessage.Error.INVALID_PARAMETER));
        }
        return dashboardTransform.toResponseDashboardBantuan(
                tersedia,
                diajukan,
                terproses,
                disetujui,
                ditolak
        );
    }

    public List<ResponseDashboardTransaksiDaily> transaksiDaily(RequestDashboardPerRole request) {
        List<ResponseDashboardTransaksiDaily> response = new ArrayList<>();
        String dateNow = LocalDate.now().toString();
        List<Transaksi> diajukan = transaksiService.findByStatusAndTanggalDiajukan(dateNow);
        List<Transaksi> diproses = transaksiService.findByStatusAndTanggalDiproses(dateNow);
        List<Transaksi> dikirim = transaksiService.findByStatusAndTanggalDikirim(dateNow);
        List<Transaksi> siapDiambil = transaksiService.findByStatusAndTanggalSiapDiambil(dateNow);
        List<Transaksi> selesai = transaksiService.findByStatusAndTanggalSelesai(dateNow);

        switch (request.getIdRole()) {
            case 1:
            case 2:
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("DIAJUKAN", (long) diajukan.size()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("DIPROSES", (long) diproses.size()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("DIKIRIM", (long) dikirim.size()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("SIAP DIAMBIL", (long) siapDiambil.size()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("SELESAI", (long) selesai.size()));
                break;
            case 3:
                String idNelayan = nelayanService.findByIdUser(request.getIdUser()).getIdNelayan();
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("DIPROSES",
                        diproses.stream().filter(transaksi -> transaksi.getIdNelayan() != null && transaksi.getIdNelayan().equalsIgnoreCase(idNelayan)).count()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("DIKIRIM",
                        dikirim.stream().filter(transaksi -> transaksi.getIdNelayan() != null && transaksi.getIdNelayan().equalsIgnoreCase(idNelayan)).count()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("SIAP DIAMBIL",
                        siapDiambil.stream().filter(transaksi -> transaksi.getIdNelayan() != null && transaksi.getIdNelayan().equalsIgnoreCase(idNelayan)).count()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("SELESAI",
                        selesai.stream().filter(transaksi -> transaksi.getIdNelayan() != null && transaksi.getIdNelayan().equalsIgnoreCase(idNelayan)).count()));
                break;
            case 4:
                String idPembeli = pembeliService.findByIdUser(request.getIdUser()).getIdPembeli();
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("DIAJUKAN",
                        diajukan.stream().filter(transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).count()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("DIPROSES",
                        diproses.stream().filter(transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).count()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("DIKIRIM",
                        dikirim.stream().filter(transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).count()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("SIAP DIAMBIL",
                        siapDiambil.stream().filter(transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).count()));
                response.add(dashboardTransform.toResponseDashboardTransaksiDaily("SELESAI",
                        selesai.stream().filter(transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).count()));
                break;
            default:
                throw new ApplicationException(Status.INVALID(GlobalMessage.Error.INVALID_PARAMETER));
        }

        return response;
    }

    public List<ResponseDashboardTransaksiWeekly> transaksiWeekly(RequestDashboardPerRole request) {
        List<ResponseDashboardTransaksiWeekly> response = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();
        long diajukan = 0;
        long diproses = 0;
        long selesai = 0;

        switch (request.getIdRole()) {
            case 1:
            case 2:
                for (long i=6; i>=0; i--) {
                    String date = dateNow.minusDays(i).toString();
                    diajukan = transaksiService.findByTanggalDiajukan(date).size();
                    diproses = transaksiService.findByTanggalDiproses(date).size();
                    selesai = transaksiService.findByTanggalSelesai(date).size();

                    response.add(dashboardTransform.toResponseDashboardTransaksiWeekly(
                            date,
                            diajukan,
                            diproses,
                            selesai
                    ));
                }
                break;
            case 3:
                String idNelayan = nelayanService.findByIdUser(request.getIdUser()).getIdNelayan();
                for (long i=7; i>=0; i--) {
                    String date = dateNow.minusDays(i).toString();
                    diajukan = transaksiService.findByTanggalDiajukan(date).stream().filter(
                            transaksi -> transaksi.getIdNelayan() != null && transaksi.getIdNelayan().equalsIgnoreCase(idNelayan)).count();
                    diproses = transaksiService.findByTanggalDiproses(date).stream().filter(
                            transaksi -> transaksi.getIdNelayan() != null && transaksi.getIdNelayan().equalsIgnoreCase(idNelayan)).count();
                    selesai = transaksiService.findByTanggalSelesai(date).stream().filter(
                            transaksi -> transaksi.getIdNelayan() != null && transaksi.getIdNelayan().equalsIgnoreCase(idNelayan)).count();

                    response.add(dashboardTransform.toResponseDashboardTransaksiWeekly(
                            date,
                            diajukan,
                            diproses,
                            selesai
                    ));
                }
                break;
            case 4:
                String idPembeli = pembeliService.findByIdUser(request.getIdUser()).getIdPembeli();
                for (long i=7; i>=0; i--) {
                    String date = dateNow.minusDays(i).toString();
                    diajukan = transaksiService.findByTanggalDiajukan(date).stream().filter(
                            transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).count();
                    diproses = transaksiService.findByTanggalDiproses(date).stream().filter(
                            transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).count();
                    selesai = transaksiService.findByTanggalSelesai(date).stream().filter(
                            transaksi -> transaksi.getIdPembeli().equalsIgnoreCase(idPembeli)).count();

                    response.add(dashboardTransform.toResponseDashboardTransaksiWeekly(
                            date,
                            diajukan,
                            diproses,
                            selesai
                    ));
                }
                break;
            default:
                throw new ApplicationException(Status.INVALID(GlobalMessage.Error.INVALID_PARAMETER));
        }

        return response;
    }

    public List<ResponseDashboardTransaksiKecamatan> transaksiKecamatan() {
        List<ResponseDashboardTransaksiKecamatan> response = new ArrayList<>();
        String dateNow = LocalDate.now().toString();
        String[] listkecamatan = GlobalConstant.LIST_KECAMATAN;
        for (String kecamatan : listkecamatan) {
            response.add(dashboardTransform.toResponseDashboardTransaksiKecamatan(
                    kecamatan,
                    (long) transaksiService.findByNelayanKecamatan(dateNow, kecamatan).size()
            ));
        }
        return response;
    }
}
