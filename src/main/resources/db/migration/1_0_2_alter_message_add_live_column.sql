--liquibase formatted sql

--changeset Almiev:1
alter table frompast.user_msg
    add column schedule_type varchar not null default 'YEAR',
    add column waiting_time int not null default 1