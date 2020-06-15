# user table
drop table if exists t_user;
create table t_user(
    id int primary key auto_increment,
    name varchar(100) unique not null ,
    password varchar(200) not null,
    enable int default 0
);