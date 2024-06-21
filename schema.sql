#Creación de la bb.dd.
create schema if not exists proyectoIntegrado;
use proyectoIntegrado;

create table if not exists user
(
    email    varchar(255)                  not null,
    password varchar(255)                  null,
    role     enum ('ROLE_OFF', 'ROLE_CUS') null,
    primary key (email)
);

create table if not exists customer
(
    birth_date   datetime(6)  not null,
    id           bigint auto_increment
        primary key,
    address      varchar(255) not null,
    name         varchar(255) not null,
    phone_number varchar(255) not null,
    surname      varchar(255) not null,
    user_email   varchar(255) null,
    constraint UK_hho535192qm8r8xchi17sqlfn
        unique (user_email),
    constraint FK33ty8iwpkni4gv1mpq8vdpo5u
        foreign key (user_email) references user (email)
);

create table if not exists offerer
(
    birth_date   datetime(6)  not null,
    id           bigint auto_increment
        primary key,
    address      varchar(255) not null,
    formation    varchar(255) null,
    name         varchar(255) not null,
    phone_number varchar(255) not null,
    surname      varchar(255) not null,
    user_email   varchar(255) null,
    constraint UK_b8xa5c9foprmy9yfl4riaj7g5
        unique (user_email),
    constraint FKr6f7sly9xsi2x71fv73ysmspq
        foreign key (user_email) references user (email)
);

create table if not exists activity
(
    available_quota       int          not null,
    max_quota             int          not null,
    price                 float        not null,
    date                  datetime(6)  not null,
    id                    bigint auto_increment
        primary key,
    offerer_id            bigint       null,
    description           varchar(255) not null,
    duration              varchar(255) not null,
    location              varchar(255) not null,
    name                  varchar(255) not null,
    provided_mats         varchar(255) null,
    recommended_formation varchar(255) null,
    required_mats         varchar(255) null,
    constraint FK55co3xganwfqopw29urr7tq8i
        foreign key (offerer_id) references offerer (id)
);

create table if not exists request
(
    customer_id bigint       null,
    date        datetime(6)  not null,
    id          bigint auto_increment
        primary key,
    description varchar(255) not null,
    location    varchar(255) not null,
    name        varchar(255) not null,
    constraint FK6wuyy6femh1tl1jxmw1ilrs6b
        foreign key (customer_id) references customer (id)
);

