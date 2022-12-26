drop table if exists "user" cascade;
drop table if exists image cascade;

CREATE TABLE "user" (
    id          serial primary key,
    firstname   varchar(20) not null,
    lastname    varchar(20) not null,
    phone       varchar(20) not null,
    password    varchar
);

CREATE TABLE image (
    owner       integer not null,
    filename    varchar,
    file        varchar,
    mime        varchar(20) not null,
    filesize    integer not null
);
