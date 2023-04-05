package com.binus.thesis.fisheryapp.business.controller;

import com.binus.thesis.fisheryapp.base.constant.EndpointAPI;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.BaseResponse;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseDokumen;
import com.binus.thesis.fisheryapp.business.model.Dokumen;
import com.binus.thesis.fisheryapp.business.service.DokumenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(EndpointAPI.DOKUMEN)
@RequiredArgsConstructor
@Slf4j
public class DokumenController {

    private final DokumenService service;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public BaseResponse<ResponseDokumen> upload(@RequestParam("file") MultipartFile dokumen,
                                                @RequestParam("namaService") String namaService) {
        BaseResponse<ResponseDokumen> response = new BaseResponse<>();
        try {
            ResponseDokumen dokumenResp = service.upload(dokumen, namaService);
            response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_UPLOAD_DOKUMEN));
            response.setResult(dokumenResp);
        } catch (ApplicationException exception) {
            response.setStatus(exception.getStatus());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            response.setStatus(new Status(Status.ERROR_CODE, Status.ERROR_DESC, exception.getLocalizedMessage()));
        }
        return response;
    }

    @RequestMapping(value = "/download/{idDokumen}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<InputStreamResource> download(@Valid @PathVariable(value = "idDokumen") int idDokumen) {

        Dokumen dokumen = service.getDokumen(idDokumen);

        InputStreamResource file = new InputStreamResource(service.download(dokumen));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_OCTET_STREAM, Charset.forName("UTF-8")));
        headers.setContentDispositionFormData("attachment", dokumen.getFileName());
        headers.add("Content-Transfer-Encoding", "binary");

        return ResponseEntity.ok()
                .headers(headers)
                .body(file);
    }
}
