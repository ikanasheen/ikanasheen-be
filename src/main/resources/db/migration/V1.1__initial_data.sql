INSERT INTO role(id_role, nama_role)
VALUES
('admin', 'admin aplikasi'),
('dinas', 'dinas perikanan Kepulauan Aru'),
('nelayan', 'nelayan di Kepulauan Aru'),
('pembeli', 'pembeli ikan');

INSERT INTO user(id_user, id_role, username, password, status)
VALUES
('admin7264917', 'admin', 'admin1', 'password', 'ACTIVE'),
('admin6356294', 'admin', 'admin2', 'password', 'ACTIVE'),
('dinas5374917', 'dinas', 'gov1', 'password', 'ACTIVE'),
('dinas4516491', 'dinas', 'gov2', 'password', 'ACTIVE');

INSERT INTO admin(id_admin, id_user, no_telepon, email)
VALUES
('admin7264917230225', 'admin7264917', '081234567890', 'admin1@asheen.com'),
('admin6356294230225', 'admin6356294', '081234567890', 'admin2@asheen.com');

INSERT INTO dinas_perikanan(id_dinas_perikanan, id_user, no_telepon, email)
VALUES
('dinas5374917230225', 'dinas5374917', '081098765432', 'gov1@gmail.com'),
('dinas4516491230225', 'dinas4516491', '081098765432', 'gov2@gmail.com');