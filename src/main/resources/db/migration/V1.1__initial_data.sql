INSERT INTO role(id_role, nama_role, deskripsi)
VALUES
(1, 'admin', 'admin aplikasi'),
(2, 'dinas perikanan', 'dinas perikanan Kepulauan Aru'),
(3, 'nelayan', 'nelayan di Kepulauan Aru'),
(4, 'pembeli', 'pembeli ikan');

INSERT INTO user(id_user, id_role, username, password, status)
VALUES
('17264917', 1, 'admin1', 'Password1!', 'ACTIVE'),
('25374917', 2, 'gov1', 'Password1!', 'ACTIVE');

INSERT INTO admin(id_admin, id_user, no_telepon, email)
VALUES
('17264917230225', '17264917', '081234567890', 'admin1@asheen.com');

INSERT INTO dinas_perikanan(id_dinas_perikanan, id_user, no_telepon, email)
VALUES
('25374917230225', '25374917', '081098765432', 'gov1@gmail.com');