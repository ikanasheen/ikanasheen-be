package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.model.Ikan;
import com.binus.thesis.fisheryapp.repository.IkanRepository;
import com.binus.thesis.fisheryapp.transform.IkanTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IkanService {

    private final IkanRepository repository;

    private final IkanTransform transform;

    public Ikan create(Ikan ikan) {
        String idIkan = GeneratorUtils.generateId("", new Date(), 4);
        return repository.save(
                transform.createIkantoEntity(ikan, idIkan)
        );
    }

    public Ikan update(Ikan ikan) {
        Ikan ikanRepo = getIkan(ikan.getIdIkan());
        return repository.save(
                transform.updateIkantoEntity(ikanRepo, ikan)
        );
    }

    public void delete(String idIkan) {
        getIkan(idIkan);
        repository.deleteById(idIkan);
    }

    public Ikan detail(String idIkan) {
        return getIkan(idIkan);
    }

    public List<Ikan> retrieveList() {
        List<Ikan> ikanList = repository.findAll();
        return ikanList;
    }

    public Ikan getIkan(String idIkan) {
        Optional<Ikan> ikanRepo = repository.findById(idIkan);
        if (ikanRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "ikan")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idIkan))
            );
        }

        return ikanRepo.get();
    }
}
