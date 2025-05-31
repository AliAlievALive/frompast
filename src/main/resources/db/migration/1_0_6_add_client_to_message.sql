--liquibase formatted sql

--changeset Almiev:1
alter table frompast.user_msg
    add column client_id bigint