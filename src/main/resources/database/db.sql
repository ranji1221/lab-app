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

# slide 轮播图表
create table t_slide(
    id int primary key auto_increment,
    name varchar(100),
    url varchar(250)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# news 新闻表
create table t_news(
    id int primary key auto_increment,
    title varchar(300),
    content text,
    create_time timestamp,
    last_time timestamp

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# news_image 新闻图片表
create table t_news_image(
    id int primary key auto_increment,
    title varchar(200),
    url varchar(500)    #这个字段其实是保存的图片的真实存储位置，这个字段名应叫为path,我在这里就不更改了
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# article 文章表
create table t_article(
    id int primary key auto_increment,
    title varchar(300),
    summary varchar(2048),
    thumbnail_url varchar(500),      # 缩略图访问路径，缩略图单独上传到某个目录下返回相应的访问路径
    content longtext,               # 文章的内容，内容中的图片以base64存储到数据中
    publisher varchar(100),
    create_time timestamp,
    last_time timestamp
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# article_thumbnail  文章缩略图表
create table t_article_thumbnail(
    id int primary key auto_increment,
    title varchar(200),
    path varchar(500)    #这个字段其实是保存的图片的真实存储位置
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

