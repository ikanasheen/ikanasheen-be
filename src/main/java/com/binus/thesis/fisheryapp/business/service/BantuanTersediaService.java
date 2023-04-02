package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.model.BantuanTersedia;
import com.binus.thesis.fisheryapp.business.transform.BantuanTersediaTransform;
import com.binus.thesis.fisheryapp.business.repository.BantuanTersediaRepository;
import com.binus.thesis.fisheryapp.business.service.specification.BantuanTersediaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BantuanTersediaService {

    private final BantuanTersediaRepository repository;

    private final BantuanTersediaSpecification specification;

    private final BantuanTersediaTransform transform;
    private final PageTransform pageTransform;

    public BantuanTersedia create(BantuanTersedia bantuan) {
        String jenis = bantuan.getJenisBantuan().split(" ")[0].substring(0,3);
        String idBantuan = GeneratorUtils.generateId(jenis.toUpperCase(Locale.ROOT), new Date(), 4);
        return repository.save(
                transform.createBantuantoEntity(bantuan, idBantuan, bantuan.getKuota())
        );
    }

    public BantuanTersedia update(BantuanTersedia bantuan) {
        BantuanTersedia bantuanRepo = getBantuanTersedia(bantuan.getIdBantuan());
        transform.updateKuota(bantuanRepo, bantuan);
        transform.updateBantuantoEntity(bantuanRepo, bantuan);
        return repository.save(bantuanRepo);
    }

    public void delete(String idBantuan) {
        getBantuanTersedia(idBantuan);
        repository.deleteById(idBantuan);
    }

    public BantuanTersedia detail(String idBantuan) {
        return getBantuanTersedia(idBantuan);
    }

    public BaseResponse<List<BantuanTersedia>> retrieve(BaseRequest<BaseParameter<BantuanTersedia>> request) {
        BaseResponse<List<BantuanTersedia>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<BantuanTersedia> data = repository.findAll(
                specification.predicate(request.getParameter()), pageable
        );

        Paging paging = pageTransform.toPage(
                request.getPaging().getPage(),
                limit,
                data.getTotalPages(),
                data.getTotalElements()
        );

        response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
        response.setPaging(paging);
        response.setResult(data.getContent());

        return response;
    }

    public BantuanTersedia getBantuanTersedia(String idBantuan) {
        Optional<BantuanTersedia> bantuanRepo = repository.findById(idBantuan);
        if (bantuanRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "bantuan")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idBantuan))
            );
        }

        return bantuanRepo.get();
    }

    public BantuanTersedia updateKuotaTersisa(BantuanTersedia bantuan) {
        int kuota = Integer.parseInt(bantuan.getKuotaTersisa()) - 1;
        String status = "ACTIVE";
        if (kuota < 1) {
            status = "UNAVAILABLE";
        }
        return repository.save(
                transform.updateKuotaTersisa(bantuan, String.valueOf(kuota), status)
        );
    }

    public InputStream download(String idBantuan) {
        FTPClient ftpClient = new FTPClient();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            setFTPClient(ftpClient);
            String remoteFile = "/oceanare/bantuan/"+idBantuan+".docx";
            ftpClient.retrieveFile(remoteFile, outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ftpClient.logout();
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    public String upload(MultipartFile multipartFile) {
        FTPClient ftpClient = new FTPClient();

        try {
            setFTPClient(ftpClient);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            ftpClient.changeWorkingDirectory("/oceanare/bantuan/");
            String remoteFileName = multipartFile.getOriginalFilename();
            ftpClient.storeFile(remoteFileName, multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return "SUCCESS";
    }

    public void setFTPClient(FTPClient ftpClient) {
        try {
            ftpClient.connect("localhost", 21);
            ftpClient.login("admin", "123");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
