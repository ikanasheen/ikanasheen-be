package com.binus.thesis.fisheryapp.service;

import com.binus.thesis.fisheryapp.base.component.GlobalMessage;
import com.binus.thesis.fisheryapp.base.dto.*;
import com.binus.thesis.fisheryapp.base.exception.ApplicationException;
import com.binus.thesis.fisheryapp.dto.request.LoginRequestDto;
import com.binus.thesis.fisheryapp.model.User;
import com.binus.thesis.fisheryapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

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

    public List<User> retrieveList() {
        List<User> userList = repository.findAll();
        return userList;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User save(User user) {
        return repository.save(user);
    }

}
