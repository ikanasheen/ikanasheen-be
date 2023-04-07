package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.base.utils.GeneratorUtils;
import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.business.model.Nelayan;
import com.binus.thesis.fisheryapp.business.model.Role;
import com.binus.thesis.fisheryapp.business.model.User;
import com.binus.thesis.fisheryapp.business.transform.UserTransform;
import com.binus.thesis.fisheryapp.business.dto.response.ResponseNelayan;
import com.binus.thesis.fisheryapp.business.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.business.dto.request.RequestRegisterNelayan;
import com.binus.thesis.fisheryapp.business.repository.NelayanRepository;
import com.binus.thesis.fisheryapp.business.service.specification.NelayanSpecification;
import com.binus.thesis.fisheryapp.business.transform.NelayanTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NelayanService {

    private final NelayanRepository repository;

    private final NelayanTransform transform;
    private final PageTransform pageTransform;
    private final UserTransform userTransform;

    private final RoleService roleService;
    private final UserService userService;

    private final NelayanSpecification specification;

    public Nelayan register(RequestRegisterNelayan request) {
        User checkUser = checkUser(request.getUsername());
        if (checkUser != null) {
            throw new ApplicationException(Status.DATA_ALREADY_EXIST(
                    GlobalMessage.Error.USER_ALREADY_EXISTS.replaceAll(
                            GlobalMessage.Error.paramVariable.get(0), request.getUsername()
                    )
            ));
        }

        Role role = roleService.getRole(request.getIdRole());
        String idUser = GeneratorUtils.generateId(String.valueOf(request.getIdRole()), null, 7);
        User user = userTransform.regNelayanReqtoUser(request, idUser, StatusUserEnum.ACTIVE, role);
        userService.save(user);

        String idNelayan = GeneratorUtils.generateId(idUser, new Date(), 0);
        Nelayan nelayan = transform.regNelayantoNelayan(request, idNelayan, user);
        nelayan = repository.save(nelayan);

        return nelayan;
    }

    public User checkUser(String username) {
        return userService.findByUsername(username);
    }

    public Nelayan update(Nelayan nelayan) {
        Nelayan nelayanRepo = getNelayan(nelayan.getIdNelayan());
        return repository.save(
                transform.updateNelayantoEntity(nelayanRepo, nelayan)
        );
    }

    public void delete(String idNelayan) {
        getNelayan(idNelayan);
        repository.deleteById(idNelayan);
    }

    public ResponseNelayan detail(String idNelayan) {
        Nelayan nelayan = getNelayan(idNelayan);
        return transform.buildResponseNelayan(nelayan);
    }

    public BaseResponse<List<ResponseNelayan>> retrieve(BaseRequest<BaseParameter<Nelayan>> request) {
        BaseResponse<List<ResponseNelayan>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<Nelayan> data = repository.findAll(
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
        response.setResult(transform.buildResponseNelayanList(data.getContent()));

        return response;
    }

    public Nelayan getNelayan(String idNelayan) {
        Optional<Nelayan> nelayanRepo = repository.findById(idNelayan);
        if (nelayanRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "nelayan")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idNelayan))
            );
        }

        return nelayanRepo.get();
    }

    public Nelayan findByIdUser(String idUser) {
        return repository.findByIdUser(idUser);
    }
}
