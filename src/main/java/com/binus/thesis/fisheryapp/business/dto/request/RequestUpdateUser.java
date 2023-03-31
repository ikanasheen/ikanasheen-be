package com.binus.thesis.fisheryapp.business.dto.request;

import lombok.Data;

@Data
public class RequestUpdateUser {

    private String idUser;

    private String username;

    private String status;
}
