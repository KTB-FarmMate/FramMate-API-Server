CREATE TABLE `member`
(
    `id`         CHAR(36)    NOT NULL, -- UUID를 CHAR(36)으로 저장
    `created_at` DATETIME(6) NOT NULL, -- createdAt 필드
    `updated_at` DATETIME(6) NOT NULL, -- updatedAt 필드
    PRIMARY KEY (`id`)                 -- 기본키를 테이블 내에서 설정
);

CREATE TABLE `crop`
(
    `id`   INT          NOT NULL AUTO_INCREMENT, -- id는 자동 증가
    `name` VARCHAR(100) NULL,                    -- crop name
    PRIMARY KEY (`id`)                           -- 기본키를 테이블 내에서 설정
);

CREATE TABLE `chat_room`
(
    `id`         CHAR(31)     NOT NULL,                   -- chat_room id는 CHAR(31)로 설정
    `member_id`  CHAR(36)     NOT NULL,                   -- member_id는 member 테이블의 id를 참조
    `crop_id`    INT          NOT NULL,                   -- crop_id는 crop 테이블의 id를 참조
    `created_at` DATETIME(6)  NOT NULL,                   -- created_at 필드
    `updated_at` DATETIME(6)  NOT NULL,                   -- updated_at 필드
    `planted_at` DATE         NOT NULL,                   -- planted_at 필드
    `address`    VARCHAR(255) NULL,                       -- 주소 필드
    PRIMARY KEY (`id`),                                   -- 기본키를 테이블 내에서 설정
    FOREIGN KEY (`member_id`) REFERENCES `member` (`id`), -- member 테이블과 외래키 관계
    FOREIGN KEY (`crop_id`) REFERENCES `crop` (`id`)      -- crop 테이블과 외래키 관계
);
