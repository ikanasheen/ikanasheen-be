CREATE TABLE IF NOT EXISTS dokumen(
    id_dokumen VARCHAR(25) NOT NULL,
    nama_dokumen VARCHAR(255) NOT NULL,
    nama_service VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    CONSTRAINT pkey_dokumen PRIMARY KEY (id_dokumen)
);

ALTER TABLE fishery_db.bantuan_tersedia
DROP COLUMN format_proposal,
ADD COLUMN id_dokumen VARCHAR(25) NOT NULL,
ADD CONSTRAINT fk_dokumen_bantuan_tersedia FOREIGN KEY (id_dokumen) REFERENCES fishery_db.dokumen(id_dokumen);

ALTER TABLE fishery_db.proposal_bantuan
DROP COLUMN file,
ADD COLUMN id_dokumen VARCHAR(25) NOT NULL,
ADD CONSTRAINT fk_dokumen_proposal_bantuan FOREIGN KEY (id_dokumen) REFERENCES fishery_db.dokumen(id_dokumen);
