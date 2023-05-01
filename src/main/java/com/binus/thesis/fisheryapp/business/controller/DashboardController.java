package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BaseRequest;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestDashboardTransaksi;
import com.binus.thesis.fisheryapp.business.dto.response.dashboard.ResponseDashboardIkan;
import com.binus.thesis.fisheryapp.business.dto.response.dashboard.ResponseDashboardNelayan;
import com.binus.thesis.fisheryapp.business.dto.response.dashboard.ResponseDashboardSosialisasi;
import com.binus.thesis.fisheryapp.business.dto.response.dashboard.ResponseDashboardTransaksi;
import com.binus.thesis.fisheryapp.business.model.Ikan;
import com.binus.thesis.fisheryapp.business.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.DASHBOARD)
@RequiredArgsConstructor
@Slf4j
public class DashboardController {

    private final DashboardService dashboardService;

    @RequestMapping(value = "/ikan", method = RequestMethod.POST)
    public BaseResponse<ResponseDashboardIkan> ikan() {
        BaseResponse<ResponseDashboardIkan> response = new BaseResponse<>();
        try {
            ResponseDashboardIkan responseDto = dashboardService.ikan();
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
            response.setResult(responseDto);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/nelayan", method = RequestMethod.POST)
    public BaseResponse<ResponseDashboardNelayan> nelayan() {
        BaseResponse<ResponseDashboardNelayan> response = new BaseResponse<>();
        try {
            ResponseDashboardNelayan responseDto = dashboardService.nelayan();
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
            response.setResult(responseDto);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/transaksi", method = RequestMethod.POST)
    public BaseResponse<ResponseDashboardTransaksi> transaksi(@RequestBody BaseRequest<BaseParameter<RequestDashboardTransaksi>> request) {
        BaseResponse<ResponseDashboardTransaksi> response = new BaseResponse<>();
        try {
            ResponseDashboardTransaksi responseDto = dashboardService.transaksi(request.getParameter().getData());
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
            response.setResult(responseDto);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/sosialisasi", method = RequestMethod.POST)
    public BaseResponse<ResponseDashboardSosialisasi> sosialisasi() {
        BaseResponse<ResponseDashboardSosialisasi> response = new BaseResponse<>();
        try {
            ResponseDashboardSosialisasi responseDto = dashboardService.sosialisasi();
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
            response.setResult(responseDto);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }
}
