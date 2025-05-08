--liquibase formatted sql

--changeset Almiev:1
create table if not exists client
(
    id                   bigserial primary key,
    full_name            varchar,
    firstname            varchar,
    lastname             varchar,
    mobile_phone         varchar,
    country              varchar,
    state                varchar,
    city                 varchar,
    street               varchar,
    postal_code          varchar,
    mail                 varchar
);