ALTER TABLE repository
ADD COLUMN local_path TEXT;

ALTER TABLE repository
ADD COLUMN last_synced_at TIMESTAMP;

ALTER TABLE repository
ADD COLUMN sync_error TEXT;

