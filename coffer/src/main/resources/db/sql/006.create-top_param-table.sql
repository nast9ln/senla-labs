create table coffer.top_param (
    id serial primary key ,
    time_top_start timestamp not null ,
    time_in_top int not null ,
    is_top boolean not null default false
);