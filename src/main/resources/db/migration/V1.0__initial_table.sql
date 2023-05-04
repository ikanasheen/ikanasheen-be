CREATE TABLE IF NOT EXISTS role(
    id_role INT AUTO_INCREMENT NOT NULL,
    nama_role VARCHAR(255) NOT NULL,
    deskripsi VARCHAR(255),
    CONSTRAINT pkey_role PRIMARY KEY (id_role)
);

CREATE TABLE IF NOT EXISTS user(
    id_user VARCHAR(25) NOT NULL,
    id_role INT NOT NULL,
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
    alamat VARCHAR(255) NOT NULL,
    kecamatan VARCHAR(255) NOT NULL,
    kecamatan_id VARCHAR(25) NOT NULL,
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

CREATE TABLE IF NOT EXISTS ikan(
    id_ikan VARCHAR(25) NOT NULL,
    nama_ikan VARCHAR(255) NOT NULL,
    deskripsi VARCHAR(255) NOT NULL,
    ukuran VARCHAR(255),
    harga_dasar numeric(18,0) DEFAULT 0 NOT NULL,
    CONSTRAINT pkey_ikan PRIMARY KEY (id_ikan)
);

CREATE TABLE IF NOT EXISTS transaksi(
    id_transaksi VARCHAR(25) NOT NULL,
    id_pembeli VARCHAR(25) NOT NULL,
    id_nelayan VARCHAR(25),
    id_ikan VARCHAR(25) NOT NULL,
    jumlah int NOT NULL,
    harga_awal numeric(18,0) DEFAULT 0,
    harga_nego numeric(18,0) DEFAULT 0,
    harga_akhir numeric(18,0) DEFAULT 0,
    tanggal_dibutuhkan DATE NOT NULL,
    alamat VARCHAR(255) NOT NULL,
    catatan VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    tanggal_diajukan DATE,
    tanggal_diproses DATE,
    tanggal_selesai DATE,
    opsi_pengiriman VARCHAR(255),
    catatan_pengiriman VARCHAR(255),
    tanggal_dikirim DATE,
    tanggal_siap_diambil DATE,
    CONSTRAINT pkey_transaksi PRIMARY KEY (id_transaksi),
    CONSTRAINT fk_pembeli_transaksi FOREIGN KEY (id_pembeli) REFERENCES pembeli(id_pembeli),
    CONSTRAINT fk_nelayan_transaksi FOREIGN KEY (id_nelayan) REFERENCES nelayan(id_nelayan),
    CONSTRAINT fk_ikan_transaksi FOREIGN KEY (id_ikan) REFERENCES ikan(id_ikan)
);

CREATE TABLE IF NOT EXISTS sosialisasi(
    id_sosialisasi VARCHAR(25) NOT NULL,
    judul VARCHAR(255) NOT NULL,
    jenis_konten VARCHAR(255) NOT NULL,
    konten TEXT NOT NULL,
    status VARCHAR(255) NOT NULL,
    penulis VARCHAR(255) NOT NULL,
    tanggal_dibuat DATE NOT NULL,
    tanggal_diubah DATE,
    CONSTRAINT pkey_sosialisasi PRIMARY KEY (id_sosialisasi)
);

CREATE TABLE IF NOT EXISTS dokumen(
    id INT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    file_extension VARCHAR(255) NOT NULL,
    file_size VARCHAR(255) NOT NULL,
    modul VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_on TIMESTAMP,
    CONSTRAINT pkey_dokumen PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS bantuan_tersedia(
    id_bantuan VARCHAR(25) NOT NULL,
    nama_bantuan VARCHAR(255) NOT NULL,
    jenis_bantuan VARCHAR(255) NOT NULL,
    kuota int DEFAULT 0,
    kuota_tersisa int NOT NULL,
    id_dokumen int NOT NULL,
    status VARCHAR(255) NOT NULL,
    CONSTRAINT pkey_bantuan PRIMARY KEY (id_bantuan),
    CONSTRAINT fk_dokumen_bantuan FOREIGN KEY (id_dokumen) REFERENCES fishery_db.dokumen(id)
);

CREATE TABLE IF NOT EXISTS proposal_bantuan(
    id_proposal_bantuan VARCHAR(25) NOT NULL,
    id_nelayan VARCHAR(25) NOT NULL,
    id_bantuan VARCHAR(25) NOT NULL,
    tanggal_diajukan TIMESTAMP NOT NULL,
    tanggal_disetujui TIMESTAMP,
    tanggal_ditolak TIMESTAMP,
    catatan VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    id_dokumen int NOT NULL,
    CONSTRAINT pkey_proposal_bantuan PRIMARY KEY (id_proposal_bantuan),
    CONSTRAINT fk_nelayan_proposal_bantuan FOREIGN KEY (id_nelayan) REFERENCES nelayan(id_nelayan),
    CONSTRAINT fk_bantuan_proposal_bantuan FOREIGN KEY (id_bantuan) REFERENCES bantuan_tersedia(id_bantuan),
    CONSTRAINT fk_dokumen_proposal_bantuan FOREIGN KEY (id_dokumen) REFERENCES fishery_db.dokumen(id)
);
