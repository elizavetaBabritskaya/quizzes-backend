ALTER TABLE users ADD COLUMN player_id VARCHAR;

UPDATE users SET player_id = 'b4b51de1-e6c4-4fdb-bb77-55f8f3c12f9e' WHERE email = 'admin@example.com';
UPDATE users SET player_id = 'b3b0b0c0-a2de-476a-80d8-0ac935deeeb5' WHERE email = 'user@example.com';