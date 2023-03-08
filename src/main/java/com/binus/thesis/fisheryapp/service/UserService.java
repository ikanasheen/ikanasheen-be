package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.dto.request.ChangePasswordRequestDto;
import com.binus.thesis.fisheryapp.dto.request.UpdateUserRequestDto;
import com.binus.thesis.fisheryapp.dto.request.LoginRequestDto;
import com.binus.thesis.fisheryapp.dto.response.ResponseUser;
import com.binus.thesis.fisheryapp.model.User;
import com.binus.thesis.fisheryapp.model.User;
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

    private final UserRepository repository;

    private final UserSpecification specification;

    private final PageTransform pageTransform;
    private final UserTransform transform;

    public User login(LoginRequestDto requestDto) {
        User userByUsername = repository.findByUsername(requestDto.getUsername());
        if (userByUsername == null) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.USER_NOT_REGISTERED));
        }

        User user = repository.findByUsernameAndPassword(requestDto.getUsername(), requestDto.getPassword());
        if (user == null) {
            throw new ApplicationException(Status.INVALID(GlobalMessage.Error.INVALID_PASSWORD));
        }
        return user;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(UpdateUserRequestDto request) {
        User userRepo = getUser(request.getIdUser());
        return repository.save(
                transform.updateUsertoEntity(userRepo, request)
        );
    }

    public User changePassword(ChangePasswordRequestDto request) {
        User userRepo = getUser(request.getIdUser());
        if (!request.getOldPassword().equals(userRepo.getPassword())) {
            throw new ApplicationException(Status.INVALID(GlobalMessage.Error.MISMATCH_PASSWORD));
        }
        return repository.save(
                transform.changePasswordtoEntity(userRepo, request)
        );
    }

    public void delete(String username) {
        User user = repository.findByUsername(username);
        repository.deleteById(user.getIdUser());
    }

    public User detail(String idUser) {
        return getUser(idUser);
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

        response.setStatus(Status.SUCCESS(GlobalMessage.Resp.SUCESS_GET_DATA));
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
}
