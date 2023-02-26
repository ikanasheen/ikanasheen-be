INSERT INTO role(nama_role, deskripsi)
VALUES
('admin', 'admin aplikasi'),
('dinas perikanan', 'dinas perikanan Kepulauan Aru'),
('nelayan', 'nelayan di Kepulauan Aru'),
('pembeli', 'pembeli ikan');

INSERT INTO user(id_user, id_role, username, password, status)
VALUES
('17264917', 1, 'admin1', 'password', 'ACTIVE'),
('16356294', 1, 'admin2', 'password', 'ACTIVE'),
('25374917', 2, 'gov1', 'password', 'ACTIVE'),
('24516491', 2, 'gov2', 'password', 'ACTIVE');

INSERT INTO admin(id_admin, id_user, no_telepon, email)
VALUES
('17264917230225', '17264917', '081234567890', 'admin1@asheen.com'),
('16356294230225', '16356294', '081234567890', 'admin2@asheen.com'),
('25374917230225', '25374917', '081098765432', 'gov1@gmail.com'),
('24516491230225', '24516491', '081098765432', 'gov2@gmail.com');