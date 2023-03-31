package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.business.dto.request.RequestRegisterPembeli;
import com.binus.thesis.fisheryapp.business.model.Pembeli;
import com.binus.thesis.fisheryapp.business.repository.PembeliRepository;
import com.binus.thesis.fisheryapp.business.transform.PembeliTransform;
import com.binus.thesis.fisheryapp.business.transform.UserTransform;
import com.binus.thesis.fisheryapp.business.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.model.Role;
import com.binus.thesis.fisheryapp.business.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PembeliService {

    private final PembeliRepository repository;

    private final PembeliTransform pembeliTransform;
    private final UserTransform userTransform;

    private final RoleService roleService;
    private final UserService userService;

    public Pembeli register(RequestRegisterPembeli request) {
        User checkUser = checkUser(request.getUsername());

        if (checkUser != null) {
            throw new ApplicationException(Status.DATA_ALREADY_EXIST(
                    GlobalMessage.Error.USER_ALREADY_EXISTS.replaceAll(
                            GlobalMessage.Error.paramVariable.get(0), request.getUsername()
                    )
            ));
        }

        Role role = roleService.findById(request.getIdRole());
        String idUser = GeneratorUtils.generateId(String.valueOf(request.getIdRole()), null, 7);
        User user = userTransform.regPembeliReqtoUser(request, idUser, StatusUserEnum.ACTIVE, role);
        userService.save(user);

        String idPembeli = GeneratorUtils.generateId(idUser, new Date(), 0);
        Pembeli pembeli = pembeliTransform.regPembeliReqtoPembeli(request, idPembeli, user);
        pembeli = repository.save(pembeli);

        return pembeli;
    }

    public User checkUser(String username) {
        return userService.findByUsername(username);
    }

    public Pembeli findByIdUser(String idUser) {
        return repository.findByIdUser(idUser);
    }

    public List<Pembeli> list() {
        return repository.findAll();
    }
}
