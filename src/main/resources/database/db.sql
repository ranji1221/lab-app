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

# news table 新闻表
drop table if exists news;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `infomation_source` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# notice table 通知公告表
drop table if exists notice;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `infomation_source` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# study table 科学教研表
drop table if exists study;
CREATE TABLE `study` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `infomation_source` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# system table 实验制度表
drop table if exists system;
CREATE TABLE `system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `infomation_source` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# banner table 幻灯片表
DROP TABLE IF EXISTS banner;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `banner-images` (`image_id`),
  CONSTRAINT `banner-images` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

# images table 图片表
DROP TABLE IF EXISTS images;
CREATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(255) NOT NULL,
  `img_addr` varchar(255) NOT NULL,
  `img_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

# device table 设备信息表
drop table if exists device;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `devicce_name` varchar(255) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `conid` varchar(255) NOT NULL,
  `num` int(11) NOT NULL,
  `roomnames` varchar(255) NOT NULL,
  `facid` varchar(255) DEFAULT NULL,
  `factime` varchar(255) DEFAULT NULL,
  `proid` varchar(255) DEFAULT NULL,
  `supid` varchar(255) DEFAULT NULL,
  `uratio` varchar(255) NOT NULL,
  `arate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# lab_information table 项目信息表
drop table if exists lab_information;
CREATE TABLE `lab_information` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `ltitle` varchar(255) DEFAULT NULL,
  `lfaculty` varchar(255) DEFAULT NULL,
  `ldevice` varchar(255) DEFAULT NULL,
  `lteacher` varchar(255) DEFAULT NULL,
  `ldate` date DEFAULT NULL,
  `ltime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# monitaring table 实验状态监控表
drop table if exists monitaring;
CREATE TABLE `monitaring` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `laboratory_name` varchar(255) DEFAULT NULL,
  `faculty` varchar(255) DEFAULT NULL,
  `experiment_name` varchar(255) DEFAULT NULL,
  `experiment_time` date DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# consume_custody table 保管领用表
DROP TABLE IF EXISTS consume_custody
CREATE TABLE `consume_custody` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `surplus_num` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '1：已归还2：未归还',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

#consume_inform table 基本资料表
DROP TABLE IF EXISTS consume_inform
CREATE TABLE `consume_inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `facid` varchar(255) DEFAULT NULL,
  `factime` date DEFAULT NULL,
  `proid` varchar(255) DEFAULT NULL,
  `supid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

#consume_norm table 管理标准表
DROP TABLE IF EXISTS consume_norm
CREATE TABLE `consume_norm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `information_source` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL COMMENT '2020-05-08',
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

#consume_purchase table 申请购置表
DROP TABLE IF EXISTS consume_purchase
CREATE TABLE `consume_purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `applicant` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
