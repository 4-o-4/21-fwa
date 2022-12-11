drop table if exists "user" cascade;

CREATE TABLE "user" (
    id          serial primary key,
    firstname   varchar(20) not null,
    lastname    varchar(20) not null,
    phone       varchar(20) not null,
    password    varchar
);
