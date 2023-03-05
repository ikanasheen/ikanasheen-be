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

        public static final String FIELD_REQUIRED = "Filed " + paramVariable.get(0) + " tidak boleh kosong!";
        public static final String USER_NOT_REGISTERED = "User belum terdaftar!";
        public static final String INVALID_PASSWORD = "Password yang Anda masukkan salah!";
        public static final String USER_ALREADY_EXISTS = "Username " + paramVariable.get(0) + " sudah digunakan!";
        public static final String DATA_ALREADY_EXISTS = "Data " + paramVariable.get(0) + " sudah ada!";
        public static final String DATA_NOT_FOUND = "Data " + paramVariable.get(0) + " dengan ID " + paramVariable.get(1) + " tidak ditemukan!";
    }

    class Resp {

        Resp(){}

        public static final List<String> paramVariable = List.of(
                "#Variable1",
                "#Variable2",
                "#Variable3",
                "#Variable4",
                "#Variable5");

        public static final String SUCCESS_CREATE_ACCOUNT = "Akun berhasil dibuat";
        public static final String SUCESS_GET_DATA = "Berhasil mengambil data";
        public static final String SUCCESS_CREATE_DATA = "Data berhasil dibuat";
        public static final String SUCCESS_UPDATE_DATA = "Data berhasil diubah";
        public static final String SUCCESS_DELETE_DATA = "Data berhasil dihapus";
    }
}