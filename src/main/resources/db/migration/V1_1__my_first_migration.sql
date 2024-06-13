CREATE TABLE rooms (
    id_room VARCHAR PRIMARY KEY,
    room_name VARCHAR NOT NULL
);

CREATE TABLE rooms_in_player (
    id_room VARCHAR NOT NULL CONSTRAINT gamesPlayers REFERENCES rooms (id_room) ON DELETE CASCADE,
    id_player VARCHAR
);

CREATE TYPE game_status AS ENUM ('READY_TO_START', 'IN_PROGRESS', 'FINISHED');

CREATE TABLE games (
    id_game VARCHAR PRIMARY KEY,
    id_room VARCHAR NOT NULL CONSTRAINT gamesRooms REFERENCES rooms (id_room) ON DELETE CASCADE,
    status game_status NOT NULL,
    id_question VARCHAR NOT NULL,
    question_number INT,
    question_count INT,
    total_score INT
);

CREATE TABLE questions (
    id_question VARCHAR PRIMARY KEY,
    text VARCHAR NOT NUll,
    id_correct_answer VARCHAR NOT NULL,
    question_score INT
);

CREATE TABLE questions_in_games (
    id_game VARCHAR CONSTRAINT gameQuestion REFERENCES games (id_game) ON DELETE CASCADE,
    id_question VARCHAR CONSTRAINT questionAnswer REFERENCES questions (id_question) ON DELETE CASCADE);


INSERT INTO questions (id_question, text, id_correct_answer, question_score) VALUES
('b2452679-1108-493b-ba51-79285c5ae900', 'Who is capybara?', 'd529c440-63c7-4f9e-a0e9-b38333208d8c', 1),
('91fff066-444c-4ec0-8a49-29be6c3f90fb', 'Who is beaver?', 'e1424a06-5292-47bc-872e-be7b442a207c', 2),
('062f720e-bad2-4802-a720-6d08f57cfc19', 'Who is bear?', '226156ae-55ee-4bf1-8d91-3de22f40a198', 3),
('7cf92935-5842-4736-8ae6-b4117c43b25b', 'Who is dog', '43eecba2-f695-48fb-bd27-85c49b9f497b', 3),
('b9e7fe40-3869-4e8f-9511-945dbd5db0be', 'Who is cat?', '02af62c4-7b69-40f0-a846-f342ea2ac813', 2),
('32671c9f-882f-4e30-9159-dc76cc825bc9', 'Who is snake?', 'b96c8069-cf4e-4b01-b4ae-89e5248ec23a',1);


CREATE TABLE answers (
    id_answer VARCHAR PRIMARY KEY,
    text VARCHAR NOT NUll,
    id_question VARCHAR CONSTRAINT questionAnswer REFERENCES questions (id_question) ON DELETE CASCADE
);

INSERT INTO answers (id_answer, text, id_question) VALUES
('d529c440-63c7-4f9e-a0e9-b38333208d8c', 'capybara', 'b2452679-1108-493b-ba51-79285c5ae900'),
('17e01c3b-80f1-4a10-b079-a0be86bbbab0','beaver','b2452679-1108-493b-ba51-79285c5ae900'),
('d92bdf70-f944-45cd-b983-f8a20bd8d5c0','bear','b2452679-1108-493b-ba51-79285c5ae900'),
('1e1f8404-5385-44cb-84d5-6487c4231c56','dog','b2452679-1108-493b-ba51-79285c5ae900'),


('da3feca0-fc91-44e2-a5b3-91efc29bad8c','snake','91fff066-444c-4ec0-8a49-29be6c3f90fb'),
('c845d255-4e64-424a-8b44-4a57ee16f23e','dog','91fff066-444c-4ec0-8a49-29be6c3f90fb'),
('e1424a06-5292-47bc-872e-be7b442a207c', 'beaver','91fff066-444c-4ec0-8a49-29be6c3f90fb'),
('6cc09858-ea5f-45a1-b054-e3d8bcbf13f3', 'cat','91fff066-444c-4ec0-8a49-29be6c3f90fb'),
('800d86b9-8e0b-40a1-b4a3-6c81a1c98949','dog','062f720e-bad2-4802-a720-6d08f57cfc19'),
('ab170e8f-62c6-4823-8199-34e446722daa','snake','062f720e-bad2-4802-a720-6d08f57cfc19'),
('ae629f5b-d457-43d2-a46c-b8c731db2ebb','beaver','062f720e-bad2-4802-a720-6d08f57cfc19'),
('226156ae-55ee-4bf1-8d91-3de22f40a198','bear','062f720e-bad2-4802-a720-6d08f57cfc19'),
('43eecba2-f695-48fb-bd27-85c49b9f497b','dog','7cf92935-5842-4736-8ae6-b4117c43b25b'),
('b87030b6-72b6-49c7-ba67-e92a0aede312','snake','7cf92935-5842-4736-8ae6-b4117c43b25b'),
('9a6dfada-b706-4fe1-bba5-e54d723067e3','capybara','7cf92935-5842-4736-8ae6-b4117c43b25b'),
('efe910f5-0c9d-4017-a344-9b0f2b7c9cb7','beaver','7cf92935-5842-4736-8ae6-b4117c43b25b'),
('592cf9aa-963f-43d8-9e80-c16a3153fa11','dog','b9e7fe40-3869-4e8f-9511-945dbd5db0be'),
('02af62c4-7b69-40f0-a846-f342ea2ac813','cat', 'b9e7fe40-3869-4e8f-9511-945dbd5db0be'),
('a76f619d-0d03-4a45-9caf-2fe57e924b4d','snake','b9e7fe40-3869-4e8f-9511-945dbd5db0be'),
('e39138a5-edde-446b-9935-79d68f9fa9a1','bear','b9e7fe40-3869-4e8f-9511-945dbd5db0be'),
('6e0c48ac-8e90-45eb-a9a2-dca2802e87ea','bear','32671c9f-882f-4e30-9159-dc76cc825bc9'),
('0cd06b8a-a2a9-43dc-9543-75386d7469fe','dog','32671c9f-882f-4e30-9159-dc76cc825bc9'),
('09dc6cbd-1a26-4545-8e0d-c2c1e5735674','cat','32671c9f-882f-4e30-9159-dc76cc825bc9'),
('b96c8069-cf4e-4b01-b4ae-89e5248ec23a','snake','32671c9f-882f-4e30-9159-dc76cc825bc9');
