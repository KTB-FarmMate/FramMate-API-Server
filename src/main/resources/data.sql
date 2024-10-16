-- member 테이블에 데이터 삽입
INSERT INTO `member` (`id`)
VALUES ('123e4567-e89b-12d3-a456-426614174000'),
       ('223e4567-e89b-12d3-a456-426614174001');

-- crop 테이블에 데이터 삽입
INSERT INTO `crop` (`id`, `name`)
VALUES (1, '콩'),
       (2, '쌀'),
       (3, '감자');

-- chat_room 테이블에 데이터 삽입 (올바른 외래 키를 참조)
INSERT INTO `chat_room` (`id`, `member_id`, `crop_id`, `created_at`, `updated_at`, `is_growing`, `address`)
VALUES (1, '123e4567-e89b-12d3-a456-426614174000', 1, '2024-01-01 10:00:00', '2024-01-01 10:00:00', 1, '서울시 강남구'),
       (2, '123e4567-e89b-12d3-a456-426614174000', 2, '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0, '서울시 서초구'),
       (3, '223e4567-e89b-12d3-a456-426614174001', 3, '2024-01-01 10:00:00', '2024-01-01 10:00:00', 1, '서울시 용산구');
