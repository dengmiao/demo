drop table t_coffee if exists;

create table t_coffee (
    id bigint auto_increment,
    create_date timestamp,
    update_date timestamp,
    name varchar(255),
    price bigint,
    primary key (id)
);