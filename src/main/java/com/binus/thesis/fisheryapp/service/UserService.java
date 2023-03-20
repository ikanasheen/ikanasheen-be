package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.dto.request.RequestChangePassword;
import com.binus.thesis.fisheryapp.dto.request.RequestUpdateUser;
import com.binus.thesis.fisheryapp.dto.request.RequestLogin;
import com.binus.thesis.fisheryapp.dto.response.ResponseUser;
import com.binus.thesis.fisheryapp.enums.StatusUserEnum;
import com.binus.thesis.fisheryapp.model.Nelayan;
import com.binus.thesis.fisheryapp.model.Pembeli;
import com.binus.thesis.fisheryapp.model.Role;
import com.binus.thesis.fisheryapp.model.User;
import com.binus.thesis.fisheryapp.repository.NelayanRepository;
import com.binus.thesis.fisheryapp.repository.PembeliRepository;
import com.binus.thesis.fisheryapp.repository.UserRepository;
import com.binus.thesis.fisheryapp.service.specification.UserSpecification;
import com.binus.thesis.fisheryapp.transform.UserTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final PembeliRepository pembeliRepository;
    private final NelayanRepository nelayanRepository;

    private final UserRepository repository;

    private final UserSpecification specification;

    private final PageTransform pageTransform;
    private final UserTransform transform;

    public ResponseUser login(RequestLogin requestDto) {
        User userByUsername = repository.findByUsername(requestDto.getUsername());
        if (userByUsername == null) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.USER_NOT_REGISTERED));
        }

        User user = repository.findByUsernameAndPassword(requestDto.getUsername(), requestDto.getPassword());
        if (user == null) {
            throw new ApplicationException(Status.INVALID(GlobalMessage.Error.INVALID_PASSWORD));
        }

        if (user.getStatus().equals(StatusUserEnum.INACTIVE.toString())) {
            throw new ApplicationException(Status.INVALID(GlobalMessage.Error.USER_INACTIVE));
        }

        ResponseUser response = transform.buildResponseUser(user);
        response.setNama(getNama(user));
        return response;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public ResponseUser create(User user) {
        User userSave = repository.save(user);
        ResponseUser response = transform.buildResponseUser(userSave);
        response.setNama(getNama(userSave));
        return response;
    }

    public ResponseUser update(RequestUpdateUser request) {
        User userRepo = getUser(request.getIdUser());
        User userUpdate = repository.save(
                transform.updateUsertoEntity(userRepo, request)
        );
        ResponseUser response = transform.buildResponseUser(userUpdate);
        response.setNama(getNama(userUpdate));
        return response;
    }

    public ResponseUser changePassword(RequestChangePassword request) {
        User userRepo = getUser(request.getIdUser());
        if (!request.getOldPassword().equals(userRepo.getPassword())) {
            throw new ApplicationException(Status.INVALID(GlobalMessage.Error.MISMATCH_PASSWORD));
        }
        User userUpdate = repository.save(
                transform.changePasswordtoEntity(userRepo, request)
        );
        ResponseUser response = transform.buildResponseUser(userUpdate);
        response.setNama(getNama(userUpdate));
        return response;
    }

    public void delete(String username) {
        User user = repository.findByUsername(username);
        repository.deleteById(user.getIdUser());
    }

    public ResponseUser detail(String idUser) {
        User user = getUser(idUser);
        ResponseUser response = transform.buildResponseUser(user);
        response.setNama(getNama(user));
        return response;
    }

    public BaseResponse<List<ResponseUser>> retrieve(BaseRequest<BaseParameter<User>> request) {
        BaseResponse<List<ResponseUser>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<User> data = repository.findAll(specification.predicate(request.getParameter()), pageable);

        Paging paging = pageTransform.toPage(
                request.getPaging().getPage(),
                limit,
                data.getTotalPages(),
                data.getTotalElements()
        );

        response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCCESS_GET_DATA));
        response.setPaging(paging);

        response.setResult(transform.buildResponseUserList(data.getContent()));

        return response;
    }

    public User getUser(String idUser) {
        Optional<User> userRepo = repository.findById(idUser);
        if (userRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "user")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), idUser))
            );
        }

        return userRepo.get();
    }

    public String getNama(User user) {
        String nama = "";
        if (user.getIdRole() == 1) {
            nama = "Admin";
        } else if (user.getIdRole() == 2) {
            nama = "Dinas Perikanan";
        } else if (user.getIdRole() == 3) {
            Nelayan nelayan = nelayanRepository.findByIdUser(user.getIdUser());
            nama = nelayan.getNamaLengkap();
        } else if (user.getIdRole() == 4) {
            Pembeli pembeli = pembeliRepository.findByIdUser(user.getIdUser());
            nama = pembeli.getNamaLengkap();
        }
        return nama;
    }
}
