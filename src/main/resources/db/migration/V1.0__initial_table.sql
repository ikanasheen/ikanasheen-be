CREATE TABLE IF NOT EXISTS role(
    id_role VARCHAR(25) NOT NULL,
    nama_role VARCHAR(255) NOT NULL,
    deskripsi VARCHAR(255),
    CONSTRAINT pkey_role PRIMARY KEY (id_role)
);

CREATE TABLE IF NOT EXISTS user(
    id_user VARCHAR(25) NOT NULL,
    id_role VARCHAR(25) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    CONSTRAINT pkey_user PRIMARY KEY (id_user),
    CONSTRAINT fk_role_user FOREIGN KEY (id_role) REFERENCES role(id_role)
);

CREATE TABLE IF NOT EXISTS nelayan(
    id_nelayan VARCHAR(25) NOT NULL,
    id_user VARCHAR(25) NOT NULL,
    nama_lengkap VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    tanggal_lahir DATE NOT NULL,
    alamat VARCHAR(25) NOT NULL,
    kecamatan VARCHAR(255) NOT NULL,
    kelurahan_desa VARCHAR(255) NOT NULL,
    no_telepon VARCHAR(25) NOT NULL,
    email VARCHAR(255),
    CONSTRAINT pkey_nelayan PRIMARY KEY (id_nelayan),
    CONSTRAINT fk_user_nelayan FOREIGN KEY (id_user) REFERENCES user(id_user)
);

CREATE TABLE IF NOT EXISTS pembeli(
    id_pembeli VARCHAR(25) NOT NULL,
    id_user VARCHAR(25) NOT NULL,
    nama_lengkap VARCHAR(255) NOT NULL,
    alamat VARCHAR(255) NOT NULL,
    no_telepon VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    CONSTRAINT pkey_pembeli PRIMARY KEY (id_pembeli),
    CONSTRAINT fk_user_pembeli FOREIGN KEY (id_user) REFERENCES user(id_user)
);

CREATE TABLE IF NOT EXISTS dinas_perikanan(
    id_dinas_perikanan VARCHAR(25) NOT NULL,
    id_user VARCHAR(25) NOT NULL,
    no_telepon VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    CONSTRAINT pkey_dinas_perikanan PRIMARY KEY (id_dinas_perikanan),
    CONSTRAINT fk_user_dinas_perikanan FOREIGN KEY (id_user) REFERENCES user(id_user)
);

CREATE TABLE IF NOT EXISTS admin(
    id_admin VARCHAR(25) NOT NULL,
    id_user VARCHAR(25) NOT NULL,
    no_telepon VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    CONSTRAINT pkey_admin PRIMARY KEY (id_admin),
    CONSTRAINT fk_user_admin FOREIGN KEY (id_user) REFERENCES user(id_user)
);

CREATE TABLE IF NOT EXISTS transaksi(
    id_transaksi VARCHAR(25) NOT NULL,
    nama_ikan VARCHAR(255) NOT NULL,
    jumlah int NOT NULL,
    satuan VARCHAR(255) NOT NULL,
    id_pembeli VARCHAR(25) NOT NULL,
    ekspedisi VARCHAR(255) NOT NULL,
    tanggal_input DATE NOT NULL,
    tanggal_terima DATE,
    catatan VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    CONSTRAINT pkey_transaksi PRIMARY KEY (id_transaksi),
    CONSTRAINT fk_pembeli_transaksi FOREIGN KEY (id_pembeli) REFERENCES pembeli(id_pembeli)
);

CREATE TABLE IF NOT EXISTS transaksi_nelayan(
    id_transaksi_nelayan VARCHAR(25) NOT NULL,
    id_nelayan VARCHAR(25) NOT NULL,
    id_transaksi VARCHAR(25) NOT NULL,
    CONSTRAINT pkey_transaksi_nelayan PRIMARY KEY (id_transaksi_nelayan),
    CONSTRAINT fk_nelayan_transaksi_nelayan FOREIGN KEY (id_nelayan) REFERENCES nelayan(id_nelayan),
    CONSTRAINT fk_transaksi_transaksi_nelayan FOREIGN KEY (id_transaksi) REFERENCES transaksi(id_transaksi)
);

CREATE TABLE IF NOT EXISTS pengembangan_diri(
    id_pengembangan_diri VARCHAR(25) NOT NULL,
    topik VARCHAR(255) NOT NULL,
    tanggal_pelaksanaan TIMESTAMP NOT NULL,
    lokasi VARCHAR(255) NOT NULL,
    deskripsi VARCHAR(255) NOT NULL,
    CONSTRAINT pkey_pengembangan_diri PRIMARY KEY (id_pengembangan_diri)
);

CREATE TABLE IF NOT EXISTS proposal_bantuan(
    id_proposal_bantuan VARCHAR(25) NOT NULL,
    id_nelayan VARCHAR(25) NOT NULL,
    tanggal_pengajuan TIMESTAMP NOT NULL,
    tanggal_disetujui TIMESTAMP,
    tanggal_ditolak TIMESTAMP,
    deskripsi VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    file VARCHAR(255) NOT NULL,
    catatan VARCHAR(255),
    CONSTRAINT pkey_proposal_bantuan PRIMARY KEY (id_proposal_bantuan),
    CONSTRAINT fk_nelayan_proposal_bantuan FOREIGN KEY (id_nelayan) REFERENCES nelayan(id_nelayan)
);