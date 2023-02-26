package com.binus.thesis.fisheryapp.base.component;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public @interface GlobalMessage {

    class Error {

        Error(){}

        public static final List<String> paramVariable = List.of(
                "#Variable1",
                "#Variable2",
                "#Variable3",
                "#Variable4",
                "#Variable5");

        public static final String FIELD_REQUIRED = "field " + paramVariable.get(0) + " tidak boleh kosong";
        public static final String USER_NOT_REGISTERED = "user belum terdaftar";
        public static final String INVALID_PASSWORD = "password salah";
        public static final String USER_ALREADY_EXISTS = "username " + paramVariable.get(0) + " sudah digunakan";
    }

    class Resp {

        Resp(){}

        public static final List<String> paramVariable = List.of(
                "#Variable1",
                "#Variable2",
                "#Variable3",
                "#Variable4",
                "#Variable5");

        public static final String SUCCESS_CREATE_ACCOUNT = "akun berhasil dibuat";
    }
}
