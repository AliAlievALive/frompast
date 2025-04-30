--liquibase formatted sql

--changeset Almiev:1
create table if not exists users
(
    id                   bigserial primary key,
    distinguished_name   varchar   not null unique,
    common_name          varchar,
    canonical_name       varchar,
    guid                 varchar   not null unique,
    user_principal_name  varchar,
    display_name         varchar,
    full_name            varchar,
    firstname            varchar,
    lastname             varchar,
    other_name           varchar,
    initials             varchar,
    telephone_number     varchar,
    home_phone           varchar,
    mobile_phone         varchar,
    country              varchar,
    state                varchar,
    city                 varchar,
    street               varchar,
    postal_code          varchar,
    company              varchar,
    organization         varchar,
    division             varchar,
    department           varchar,
    office               varchar,
    manager              varchar,
    employee_id          varchar,
    employee_number      varchar,
    mail                 varchar,
    mail_nickname        varchar,
    sam_account_name     varchar,
    office_phone         varchar,
    ip_phone             varchar,
    title                varchar,
    user_account_control varchar,
    enabled              varchar,
    is_active            boolean   not null default true,
    sync_date            timestamp not null default current_timestamp
);

--changeset Almiev:2
CREATE TABLE IF NOT EXISTS user_msg
(
    id      bigserial primary key,
    msg_txt varchar(700) not null,
    file_id bigint,
    user_id bigint
);

--changeset Almiev:3
CREATE TABLE IF NOT EXISTS user_file
(
    id         bigserial primary key,
    user_id    bigint,
    message_id bigint,
    url        varchar(700) not null,
    save_date  timestamp    not null
);

--changeset Almiev:4
ALTER TABLE user_msg
    ADD CONSTRAINT fk_msg_user
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

--changeset Almiev:5
ALTER TABLE user_msg
    ADD CONSTRAINT fk_msg_file
        FOREIGN KEY (file_id) REFERENCES user_file (id) ON DELETE CASCADE;

--changeset Almiev:6
ALTER TABLE user_file
    ADD CONSTRAINT fk_file_user
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

--changeset Almiev:7
ALTER TABLE user_file
    ADD CONSTRAINT fk_file_msg
        FOREIGN KEY (message_id) REFERENCES user_msg (id) ON DELETE CASCADE;