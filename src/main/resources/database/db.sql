# user table    用户表
drop table if exists t_user;
create table t_user(
    id int primary key auto_increment,
    name varchar(100) unique not null ,
    password varchar(200) not null,
    enable int default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into t_user(name,password,enable) values('lisi','123456',1);
insert into t_user(name,password,enable) values('zhangsan','123456',1);

# role table    角色表
drop table if exists t_role;
create table t_role(
    id int primary key auto_increment,
    role_code varchar(100) not null unique,
    role_name varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into t_role(role_code,role_name) values('admin','管理员');
insert into t_role(role_code,role_name) values('user','普通用户');

# auth table    权限表
drop table if exists t_auth;
create table t_auth(
    id int primary key auto_increment,
    auth_code varchar(200) not null unique,
    auth_name varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into t_auth(auth_code,auth_name) values('user:view','查看用户');
insert into t_auth(auth_code,auth_name) values('user:add','添加用户');
insert into t_auth(auth_code,auth_name) values('user:delete','删除用户');
insert into t_auth(auth_code,auth_name) values('user:edit','编辑用户');

# role_auth table   角色权限关联表
drop table if exists t_role_auth;
create table t_role_auth(
    id int primary key auto_increment,
    role_id int,
    auth_id int
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into t_role_auth(role_id,auth_id) values(1,1);
insert into t_role_auth(role_id,auth_id) values(1,2);
insert into t_role_auth(role_id,auth_id) values(1,3);
insert into t_role_auth(role_id,auth_id) values(1,4);
insert into t_role_auth(role_id,auth_id) values(2,1);
insert into t_role_auth(role_id,auth_id) values(2,2);