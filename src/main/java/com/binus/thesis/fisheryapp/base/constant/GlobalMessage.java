package com.binus.thesis.fisheryapp.base.constant;

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
        public static final String DATA_ALREADY_EXISTS = "data " + paramVariable.get(0) + " sudah ada";
        public static final String DATA_NOT_FOUND = "data " + paramVariable.get(0) + " dengan ID " + paramVariable.get(1) + " tidak ditemukan";
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
        public static final String SUCESS_GET_DATA = "berhasil mengambil data";
        public static final String SUCCESS_CREATE_DATA = "data berhasil dibuat";
        public static final String SUCCESS_UPDATE_DATA = "data berhasil diubah";
        public static final String SUCCESS_DELETE_DATA = "data berhasil dihapus";
    }
}
