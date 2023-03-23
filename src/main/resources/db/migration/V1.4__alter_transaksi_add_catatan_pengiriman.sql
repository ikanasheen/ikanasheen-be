ALTER TABLE fishery_db.transaksi
ADD COLUMN catatan_pengiriman VARCHAR(255),
ADD COLUMN tanggal_dikirim DATE,
ADD COLUMN tanggal_siap_diambil DATE;
