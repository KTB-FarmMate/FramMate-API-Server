CREATE TABLE `member`
(
    `id` CHAR(36) NOT NULL
);

CREATE TABLE `crop`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NULL
);

CREATE TABLE `chat_room`
(
    `id`         CHAR(31)     NOT NULL,
    `member_id`  CHAR(36)     NOT NULL,
    `crop_id`    INT          NOT NULL,
    `created_at` DATETIME(6)  NOT NULL,
    `updated_at` DATETIME(6)  NOT NULL,
    `planted_at` DATE    NOT NULL,
    `address`    VARCHAR(255) NULL
);

ALTER TABLE `member`
    ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (`id`);

ALTER TABLE `crop`
    ADD CONSTRAINT `PK_CROP` PRIMARY KEY (`id`);

ALTER TABLE `chat_room`
    ADD CONSTRAINT `PK_CHAT_ROOM` PRIMARY KEY (`id`);

ALTER TABLE `chat_room`
    ADD CONSTRAINT `FK_member_TO_chat_room_1` FOREIGN KEY (`member_id`)
        REFERENCES `member` (`id`);

ALTER TABLE `chat_room`
    ADD CONSTRAINT `FK_crop_TO_chat_room_1` FOREIGN KEY (`crop_id`)
        REFERENCES `crop` (`id`);
