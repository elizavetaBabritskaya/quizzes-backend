CREATE TABLE player_in_game (
    id_game VARCHAR NOT NULL CONSTRAINT players_in_game REFERENCES games (id_game) ON DELETE CASCADE,
    id_player VARCHAR,
    id_question VARCHAR NOT NULL,
    question_number INT,
    total_score INT
);

ALTER TABLE games DROP COLUMN id_question;
ALTER TABLE games DROP COLUMN question_number;
ALTER TABLE games DROP COLUMN total_score;

ALTER TABLE games ADD COLUMN start_date_time VARCHAR;
ALTER TABLE games ADD COLUMN time_limit VARCHAR;