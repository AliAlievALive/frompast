--liquibase formatted sql

--changeset Almiev:1
alter table frompast.client
    add column if not exists user_id bigint
        constraint fk_client_user
            references frompast.users (id) on DELETE cascade;