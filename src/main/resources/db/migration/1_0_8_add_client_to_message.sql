--liquibase formatted sql

--changeset Almiev:1
alter table frompast.user_msg
    add column if not exists client_id bigint
        constraint fk_user_msg_client
            references frompast.client (id) on DELETE cascade;