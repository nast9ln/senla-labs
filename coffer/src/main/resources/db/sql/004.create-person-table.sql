create table coffer.person (
    id serial primary key not null,
    gender varchar not null ,
    first_name varchar not null ,
    last_name varchar not null ,
    birthday date not null ,
    city varchar not null ,
    phone varchar(11),
    email varchar not null ,
    password varchar not null ,
    is_deleted boolean not null default false
);
