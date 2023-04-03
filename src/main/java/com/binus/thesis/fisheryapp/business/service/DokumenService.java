package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
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

    public Dokumen upload(MultipartFile multipartFile, String namaService) {
        FTPClient ftpClient = new FTPClient();
        String idDokumen = GeneratorUtils.generateId(
                "DOK"+namaService.substring(0,3),
                new Date(),
                5
        );
        String filepath = dokumenPath+"/"+namaService.toLowerCase(Locale.ROOT)+"/";
        String namaFile = "";
        String url = "";
        try {
            setFTPClient(ftpClient);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            ftpClient.changeWorkingDirectory(filepath);
            namaFile = idDokumen+extension;
            url = filepath+namaFile;
            ftpClient.storeFile(namaFile, multipartFile.getInputStream());
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

        return repository.save(transform.buildDokumenEntity(
                idDokumen,
                namaFile,
                namaService.toLowerCase(Locale.ROOT),
                url
        ));
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

    public Dokumen getDokumen(String idDokumen) {
        Optional<Dokumen> dokumenRepo = repository.findById(idDokumen);
        if (dokumenRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "dokumen")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idDokumen))
            );
        }

        return dokumenRepo.get();
    }
}
