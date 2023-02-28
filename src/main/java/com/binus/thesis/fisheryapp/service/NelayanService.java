package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.base.component.GlobalMessage;
import com.binus.thesis.fisheryapp.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.base.dto.Status;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.dto.request.RegisterNelayanRequestDto;
import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Role;
import com.binus.thesis.fisheryapp.model.User;
import com.binus.thesis.fisheryapp.repository.NelayanRepository;
import com.binus.thesis.fisheryapp.transform.NelayanTransform;
import com.binus.thesis.fisheryapp.transform.UserTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NelayanService {

    private final NelayanRepository repository;

    private final NelayanTransform nelayanTransform;
    private final UserTransform userTransform;

    private final RoleService roleService;
    private final UserService userService;

    public Nelayan register(RegisterNelayanRequestDto request) {
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
        User user = userTransform.regNelayanReqtoUser(request, idUser, StatusUserEnum.ACTIVE, role);
        userService.save(user);

        String idNelayan = GeneratorUtils.generateId(idUser, new Date(), 0);
        Nelayan nelayan = nelayanTransform.regNelayantoNelayan(request, idNelayan, user);
        nelayan = repository.save(nelayan);

        return nelayan;
    }

    public User checkUser(String username) {
        return userService.findByUsername(username);
    }

    public List<Nelayan> list() {
        return repository.findAll();
    }
}
