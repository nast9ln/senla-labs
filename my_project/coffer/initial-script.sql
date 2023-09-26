create schema coffer;
create table role (
    id SERIAL PRIMARY KEY  ,
    name varchar NOT NULL
);

create table person_role (
    person_id int,
    role_id int
);

create table person (
    id serial primary key,
    gender varchar not null ,
    first_name varchar not null ,
    last_name varchar not null ,
    birthday date not null ,
    city varchar not null ,
    phone varchar(11),
    email varchar not null ,
    password varchar not null ,
    avatar bytea,
    is_deleted boolean not null default false
);

create table advertisement (
    id serial primary key ,
    person_id int not null ,
    category_id int,
    top_param_id int ,
    created_data timestamp not null ,
    header varchar(56) not null ,
    cost int,
    city varchar not null ,
    description varchar(3000),
    status varchar not null ,
    main_picture_id int,
    is_deleted boolean not null default false
);

create table top_param (
    id serial primary key ,
    time_top_start timestamp not null ,
    time_in_top int not null ,
    is_top boolean not null default false
);

create table  category (
    id serial primary key ,
    name varchar
);

create table message (
    id serial primary key ,
    sender_id int,
    receiver_id int,
    text text,
    created_data timestamp,
    is_deleted boolean not null default false

);

create table image (
    id serial primary key ,
    ad_id int ,
    path bytea,
    is_deleted boolean not null default false

);

create table comment (
    id serial primary key ,
    ad_id int,
    user_id int,
    text varchar (3000),
    created_data timestamp,
    is_deleted boolean not null default false
);