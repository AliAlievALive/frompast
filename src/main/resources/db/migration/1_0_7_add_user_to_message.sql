--liquibase formatted sql

--changeset Almiev:1
alter table frompast.user_msg
    add column if not exists user_id bigint
        constraint fk_user_msg_users
            references frompast.users (id) on DELETE cascade;