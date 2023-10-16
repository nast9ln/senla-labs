create table coffer.image (
    id serial primary key ,
    advertisement_id int ,
    path bytea,
    is_deleted boolean not null default false
);