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
    code varchar(100) not null unique,
    name varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into t_role(code,name) values('admin','管理员');
insert into t_role(code,name) values('user','普通用户');

# userRole table  用户角色表
drop table if exists t_user_role;
create table t_user_role(
    id int primary key auto_increment,
    user_id int,
    role_id int,
    unique key (user_id,role_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# auth table    权限表
drop table if exists t_auth;
create table t_auth(
    id int primary key auto_increment,
    type varchar(200),
    name varchar(200),
    code varchar(200) not null unique
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into t_auth(type,name,code) values('用户管理','查看用户','user:view');
insert into t_auth(type,name,code) values('用户管理','添加用户','user:add');
insert into t_auth(type,name,code) values('用户管理','删除用户','user:delete');
insert into t_auth(type,name,code) values('用户管理','编辑用户','user:edit');
insert into t_auth(type,name,code) values('项目管理','添加项目','project:add');
insert into t_auth(type,name,code) values('项目管理','查询项目','project:delete');
insert into t_auth(type,name,code) values('设备管理','添加设备','device:add');
insert into t_auth(type,name,code) values('公告管理','添加公告','notice:add');
insert into t_auth(type,name,code) values('公告管理','查看公告','notice:view');

# role_auth table   角色权限关联表
drop table if exists t_role_auth;
create table t_role_auth(
    id int primary key auto_increment,
    role_id int,
    auth_id int,
    unique key (role_id,auth_id)
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

# employee table 员工表 (使用户的信息更加的完善,和用户建立一对一的关系)
drop table if exists t_employee;
create table t_employee(
    id int primary key,
    realName varchar(100),  #姓名
    gender tinyint,         #性别
    birth  date,            #出生年日期
    address varchar(250),    #住址
    foreign key (id) references t_user(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_employee(id,realName,gender,birth,address) values(1,'王昭君',0,'2012-03-15','塞北高原岭上寒');
insert into t_employee(id,realName,gender,birth,address) values(2,'项羽',1,'2010-02-15','江东父老水上行');
