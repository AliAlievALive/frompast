--liquibase formatted sql

--changeset Almiev:1
alter table frompast.users
    add column last_auth timestamp