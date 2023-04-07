package com.binus.thesis.fisheryapp.business.service;

import com.binus.thesis.fisheryapp.base.constant.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.base.transform.PageTransform;
import com.binus.thesis.fisheryapp.business.model.Role;
import com.binus.thesis.fisheryapp.business.repository.RoleRepository;
import com.binus.thesis.fisheryapp.business.service.specification.RoleSpecification;
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
public class RoleService {

    private final RoleRepository repository;

    private final RoleSpecification specification;

    private final PageTransform pageTransform;

    public Role detail(int idRole) {
        return getRole(idRole);
    }

    public BaseResponse<List<Role>> retrieve(BaseRequest<BaseParameter<Role>> request) {
        BaseResponse<List<Role>> response = new BaseResponse<>();
        int page = request.getPaging().getPage() - 1;
        int limit = request.getPaging().getLimit();
        Pageable pageable = specification.pageGenerator(page, limit);
        Page<Role> data = repository.findAll(
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

    public Role getRole(int idRole) {
        Optional<Role> roleRepo = repository.findById(idRole);
        if (roleRepo.isEmpty()) {
            throw new ApplicationException(Status.DATA_NOT_FOUND(GlobalMessage.Error.DATA_NOT_FOUND
                    .replaceAll(GlobalMessage.Error.paramVariable.get(0), "role")
                    .replaceAll(GlobalMessage.Error.paramVariable.get(1), String.valueOf(idRole)))
            );
        }

        return roleRepo.get();
    }


}
