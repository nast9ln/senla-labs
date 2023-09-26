create table coffer.advertisement (
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
    main_image_id int,
    is_deleted boolean not null default false
);
