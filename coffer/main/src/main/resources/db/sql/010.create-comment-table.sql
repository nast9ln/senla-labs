create table coffer.comment (
    id serial primary key ,
    advertisement_id int,
    person_id int,
    text varchar (3000),
    created_data timestamp,
    is_deleted boolean not null default false
);