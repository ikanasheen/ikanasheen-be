ALTER TABLE fishery_db.ikan
MODIFY COLUMN harga_dasar numeric(18,0) DEFAULT 0 NOT NULL;

ALTER TABLE fishery_db.transaksi
MODIFY COLUMN harga_awal numeric(18,0) DEFAULT 0,
MODIFY COLUMN harga_nego numeric(18,0) DEFAULT 0,
MODIFY COLUMN harga_akhir numeric(18,0) DEFAULT 0;