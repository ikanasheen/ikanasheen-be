package com.binus.thesis.fisheryapp.dto.response;

import lombok.Data;

@Data
public class ResponseUser {

    private String idUser;

    private int idRole;

    private String username;

    private String password;

    private String status;

    private ResponseRole role;
}
