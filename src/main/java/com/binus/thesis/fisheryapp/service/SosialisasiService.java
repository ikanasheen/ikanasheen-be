package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.model.Sosialisasi;
import com.binus.thesis.fisheryapp.repository.SosialisasiRepository;
import com.binus.thesis.fisheryapp.transform.SosialisasiTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SosialisasiService {

    private final SosialisasiRepository repository;

    private final SosialisasiTransform transform;

    public Sosialisasi create(Sosialisasi sosialisasi) {
        String idSosialisasi = GeneratorUtils.generateId(sosialisasi.getJenisKonten().toString().substring(0,3), new Date(), 4);
        return repository.save(
                transform.createSosialisasitoEntity(sosialisasi, idSosialisasi)
        );
    }

    public Sosialisasi update(Sosialisasi sosialisasi) {
        Sosialisasi sosialisasiRepo = getSosialisasi(sosialisasi.getIdSosialisasi());
        return repository.save(
                transform.updateSosialisasitoEntity(sosialisasiRepo, sosialisasi)
        );
    }

    public void delete(String idSosialisasi) {
        getSosialisasi(idSosialisasi);
        repository.deleteById(idSosialisasi);
    }

    public Sosialisasi detail(String idSosialisasi) {
        return getSosialisasi(idSosialisasi);
    }

    public List<Sosialisasi> retrieveList() {
        List<Sosialisasi> sosialisasiList = repository.findAll();
        return sosialisasiList;
    }

    public Sosialisasi getSosialisasi(String idSosialisasi) {
        Optional<Sosialisasi> sosialisasiRepo = repository.findById(idSosialisasi);
        if (sosialisasiRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "sosialisasi")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idSosialisasi))
            );
        }

        return sosialisasiRepo.get();
    }
}
