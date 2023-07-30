drop table cities, users;

create table cities
(
    id serial8,
    name varchar,
    index int8,
    primary key (id)
);

create table users
(
    id serial8,
    city_id int8,
    first_name varchar,
    last_name varchar,
    login varchar,
    password varchar,
    primary key (id),
    foreign key (city_id) references cities(id)
);
