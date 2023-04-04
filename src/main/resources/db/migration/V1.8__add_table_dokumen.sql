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

ALTER TABLE fishery_db.bantuan_tersedia
DROP COLUMN format_proposal,
ADD COLUMN id int NOT NULL,
ADD CONSTRAINT fk_dokumen_bantuan_tersedia FOREIGN KEY (id) REFERENCES fishery_db.dokumen(id);

ALTER TABLE fishery_db.proposal_bantuan
DROP COLUMN file,
ADD COLUMN id int NOT NULL,
ADD CONSTRAINT fk_dokumen_proposal_bantuan FOREIGN KEY (id) REFERENCES fishery_db.dokumen(id);
