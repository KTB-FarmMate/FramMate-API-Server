-- member 테이블에 데이터 삽입
INSERT INTO member (id)
VALUES ('123e4567-e89b-12d3-a456-426614174000'),
       ('223e4567-e89b-12d3-a456-426614174001'),
       ('323e4567-e89b-12d3-a456-426614174002'),
       ('423e4567-e89b-12d3-a456-426614174003'),
       ('523e4567-e89b-12d3-a456-426614174004'),
       ('623e4567-e89b-12d3-a456-426614174005'),
       ('723e4567-e89b-12d3-a456-426614174006'),
       ('823e4567-e89b-12d3-a456-426614174007'),
       ('923e4567-e89b-12d3-a456-426614174008'),
       ('a23e4567-e89b-12d3-a456-426614174009');

-- crop 테이블에 데이터 삽입
INSERT INTO crop (id, name)
VALUES (1, '콩'),
       (2, '쌀'),
       (3, '감자');

-- chat_room 테이블에 데이터 삽입 (올바른 외래 키를 참조)
INSERT INTO chat_room (id, member_id, crop_id, created_at, updated_at, is_growing, address)
VALUES ('thread_Q2NO6D1Jt8Stc9g8ds7LfOY6', '123e4567-e89b-12d3-a456-426614174000', 1, '2024-01-01 10:00:00',
        '2024-01-01 10:00:00', 1, '서울시 강남구'),
       ('thread_wfioqDZGgMNVt80pn6LQH6IR', '123e4567-e89b-12d3-a456-426614174000', 2, '2024-01-01 10:00:00',
        '2024-01-01 10:00:00', 0, '서울시 서초구'),
       ('thread_abcdqDZGgMNVt80pn6LQH6IR', '223e4567-e89b-12d3-a456-426614174001', 3, '2024-01-01 10:00:00',
        '2024-01-01 10:00:00', 1, '서울시 용산구');