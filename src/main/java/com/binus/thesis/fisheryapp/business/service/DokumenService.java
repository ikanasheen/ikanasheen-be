package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseDokumen;
import com.binus.thesis.fisheryapp.business.model.Dokumen;
import com.binus.thesis.fisheryapp.business.repository.DokumenRepository;
import com.binus.thesis.fisheryapp.business.transform.DokumenTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DokumenService {

    private final DokumenRepository repository;

    private final DokumenTransform transform;

    @Value("${ftp.hostname}")
    private String hostname;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${dokumen.path}")
    private String dokumenPath;

    @Value("${dokumen.extension}")
    private String extension;

    public InputStream download(Dokumen dokumen) {
        FTPClient ftpClient = new FTPClient();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            setFTPClient(ftpClient);
            String remoteFile = dokumen.getUrl();
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

    public ResponseDokumen upload(MultipartFile multipartFile, String namaService) {
        FTPClient ftpClient = new FTPClient();
        String idDokumen = GeneratorUtils.generateId(
                "DOK"+namaService.substring(0,3),
                new Date(),
                5
        );
        String filepath = dokumenPath+"/"+namaService.toLowerCase(Locale.ROOT)+"/";
        String fileName = "";
        String url = "";
        try {
            setFTPClient(ftpClient);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            ftpClient.changeWorkingDirectory(filepath);
            fileName = idDokumen+extension;
            url = filepath+fileName;
            ftpClient.storeFile(fileName, multipartFile.getInputStream());
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

        Dokumen dokumen = repository.save(transform.buildDokumenEntity(
                Math.toIntExact(repository.count()),
                fileName,
                multipartFile.getOriginalFilename(),
                getFileExtension(multipartFile),
                String.valueOf(multipartFile.getSize()),
                namaService,
                url,
                LocalDateTime.now()
        ));
        return transform.buildResponseDokumen(dokumen);
    }

    public void setFTPClient(FTPClient ftpClient) {
        try {
            ftpClient.connect(hostname, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        } else {
            return "";
        }
    }

    public Dokumen getDokumen(int idDokumen) {
        Optional<Dokumen> dokumenRepo = repository.findById(idDokumen);
        if (dokumenRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "dokumen")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), String.valueOf(idDokumen)))
            );
        }

        return dokumenRepo.get();
    }
}
