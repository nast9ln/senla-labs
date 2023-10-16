create table coffer.message (
    id serial primary key ,
    sender_id int,
    advertisement_id int,
    text text,
    created_data timestamp,
    is_deleted boolean not null default false
);