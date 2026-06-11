ALTER TABLE repository
ADD COLUMN language VARCHAR(100);

ALTER TABLE repository
ADD COLUMN framework VARCHAR(100);

ALTER TABLE repository
ADD COLUMN analyzed_at TIMESTAMP;

ALTER TABLE repository
ADD COLUMN build_tool VARCHAR(100);