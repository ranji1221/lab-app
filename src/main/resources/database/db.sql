#创建数据库
DROP DATABASE IF EXISTS `lab`;
CREATE DATABASE `lab`;

# user table    用户表
drop table if exists t_user;
create table t_user(
    id int primary key auto_increment,
    name varchar(100) unique not null ,
    password varchar(200) not null,
    enable int default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into t_user(name,password,enable) values('admin','123456',1);
INSERT INTO `t_user` VALUES ('2', 'teacher', '123456', '1');
INSERT INTO `t_user` VALUES ('3', 'student', '123456', '1');

# role table    角色表
drop table if exists t_role;
create table t_role(
    id int primary key auto_increment,
    role_code varchar(100) not null unique,
    role_name varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_role` VALUES ('1', 'admin', '超级管理员');
INSERT INTO `t_role` VALUES ('2', 'laboratoryMgr', '实验室管理员');
INSERT INTO `t_role` VALUES ('3', 'manager', '实验室负责人');
INSERT INTO `t_role` VALUES ('4', 'teacher', '教师');
INSERT INTO `t_role` VALUES ('5', 'student', '学生');
INSERT INTO `t_role` VALUES ('6', 'majorHead', '系主任');

# user_role table  角色用户表
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2', '4');
INSERT INTO `t_user_role` VALUES ('3', '3', '5');

#arrange table 预约表
DROP TABLE IF EXISTS `arrange`;
CREATE TABLE `arrange` (
  `id` int NOT NULL AUTO_INCREMENT,
  `laboratory_id` varchar(255) DEFAULT NULL,
  `project_id` varchar(255) DEFAULT NULL,
  `num` int DEFAULT NULL,
  `arrange_time` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time_start` time DEFAULT NULL,
  `time_stop` time DEFAULT NULL,
  `responsibility` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
INSERT INTO `arrange` VALUES ('1', '2', '1', '20', '2020-09-11 18:59:23', '2020-09-14', '08:00:00', '10:00:00', '贺钰婷', '0');
INSERT INTO `arrange` VALUES ('2', '1', '2', '30', '2020-09-11 19:01:24', '2020-09-23', '09:00:00', '12:00:00', '李大宝', '0');
INSERT INTO `arrange` VALUES ('3', '1', '1', '20', '2020-09-11 19:17:58', '2020-09-21', '09:00:00', '12:00:00', '张纪中', '0');
INSERT INTO `arrange` VALUES ('4', '3', '3', '30', '2020-09-11 19:21:53', '2020-09-28', '09:00:00', '10:00:00', '郭雨桐', '0');
INSERT INTO `arrange` VALUES ('5', '5', '1', '10', '2020-09-11 19:59:48', '2020-09-10', '14:00:00', '16:00:00', '张彩彩', '2');
INSERT INTO `arrange` VALUES ('6', '1', '1', '2', '2020-09-11 20:05:12', '2020-09-11', '20:00:00', '22:00:00', '擎天', '1');

#audit table 用户审计表
DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `access_addr` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2726 DEFAULT CHARSET=utf8;
INSERT INTO `audit` VALUES ('1', 'nousername', '0:0:0:0:0:0:0:1', '/swagger-ui.html', '2020-09-10 15:18:24');
INSERT INTO `audit` VALUES ('2', 'nousername', '0:0:0:0:0:0:0:1', '/webjars/springfox-swagger-ui/springfox.css', '2020-09-10 15:18:24');
INSERT INTO `audit` VALUES ('3', 'nousername', '0:0:0:0:0:0:0:1', '/webjars/springfox-swagger-ui/swagger-ui.css', '2020-09-10 15:18:24');
INSERT INTO `audit` VALUES ('4', 'nousername', '0:0:0:0:0:0:0:1', '/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js', '2020-09-10 15:18:24');

#banner table 轮播图表
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `banner` VALUES ('1', '1');
INSERT INTO `banner` VALUES ('2', '2');
INSERT INTO `banner` VALUES ('3', '3');

#images table 轮播图图片表
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `img_name` varchar(255) NOT NULL,
  `img_addr` varchar(255) NOT NULL,
  `img_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `images` VALUES ('1', '轮播图1', 'D:\\lab-app\\upload\\banner\\banner1.png', '描述');
INSERT INTO `images` VALUES ('2', '轮播图2', 'D:\\lab-app\\upload\\banner\\banner2.png', '描述');
INSERT INTO `images` VALUES ('3', '轮播图3', 'D:\\lab-app\\upload\\banner\\banner3.png', '描述');

#consume_custody table 耗材保管领用表
DROP TABLE IF EXISTS `consume_custody`;
CREATE TABLE `consume_custody` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipient` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `arrange_project_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `consume_custody` VALUES ('1', '张益达', '2020-09-22', '1', '4');
INSERT INTO `consume_custody` VALUES ('2', '张晓霞', '2020-09-08', '3', '0');
INSERT INTO `consume_custody` VALUES ('3', '李继伟', '2020-09-14', '2', '0');

#consume_inform table 耗材基本信息表
DROP TABLE IF EXISTS `consume_inform`;
CREATE TABLE `consume_inform` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `num` int DEFAULT NULL,
  `facid` varchar(255) DEFAULT NULL,
  `factime` date DEFAULT NULL,
  `proid` varchar(255) DEFAULT NULL,
  `supid` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `unit_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
INSERT INTO `consume_inform` VALUES ('1', '唐山金盾理化瓷元皿发皿', ' 菲可帝睿', '2', '2324232', '2020-09-14', '78576', '1209752', '1', '个');
INSERT INTO `consume_inform` VALUES ('2', '瓷舟', ' 菲可帝睿', '5', '2324232', '2020-09-14', '78576', '1209752', '1', '个');
INSERT INTO `consume_inform` VALUES ('3', '唐山金盾理化瓷瓷坩埚架瓷坩埚架', ' 菲可帝睿', '5', '2324232', '2020-09-14', '78576', '1209752', '1', '个');
INSERT INTO `consume_inform` VALUES ('4', '布氏漏斗', ' 菲可帝睿', '195', '2324232', '2020-09-14', '78576', '1209752', '1', '个');
INSERT INTO `consume_inform` VALUES ('5', '上海楚柏水龙头塑料桶5L', '力辰科技（lichen）', '5', '2324232', '2020-09-14', '78576', '1209752', '5', '个');
INSERT INTO `consume_inform` VALUES ('6', '河北省永清县工酒精喷灯酒精喷灯', '力辰科技（lichen）', '5', '2324232', '2020-09-14', '78576', '1209752', '5', '个');
INSERT INTO `consume_inform` VALUES ('7', '河北球形接口夹接口夹冷凝管夹', '力辰科技（lichen）', '5', '2324232', '2020-09-14', '78576', '1209752', '5', '个');
INSERT INTO `consume_inform` VALUES ('8', '新泰科教十字夹固定夹顶丝夹、双顶丝夹', '力辰科技（lichen）', '5', '2324232', '2020-09-14', '78576', '1209752', '5', '个');
INSERT INTO `consume_inform` VALUES ('9', '手术剪', '力辰科技（lichen）', '10', '2324232', '2020-09-14', '78576', '1209752', '5', '个');
INSERT INTO `consume_inform` VALUES ('10', '河北微量药勺药刮药匙、刮铲', ' 洛克菲勒（LUO KE FEI LE）', '10', '2324232', '2020-09-14', '78576', '1209752', '5', '个');
INSERT INTO `consume_inform` VALUES ('11', '2096刮刀密封', ' 洛克菲勒（LUO KE FEI LE）', '10', '2324232', '2020-09-14', '78576', '1209752', '5', '个');
INSERT INTO `consume_inform` VALUES ('12', '培养基瓶，宽口系列', ' 新芝·（SCIENTZ）', '10', '2324232', '2020-09-14', '78576', '1209752', '7', '个');
INSERT INTO `consume_inform` VALUES ('13', '5mL36孔冷冻管盒/冷冻管盒', ' 新芝·（SCIENTZ）', '4', '2324232', '2020-09-14', '78576', '1209752', '7', '个');
INSERT INTO `consume_inform` VALUES ('14', '六通道培养载玻片', ' 新芝·（SCIENTZ）', '21', '2324232', '2020-09-14', '78576', '1209752', '2', '个');
INSERT INTO `consume_inform` VALUES ('15', '牛奶抗生素检测试剂条', ' 新芝·（SCIENTZ）', '4', '2324232', '2020-09-14', '78576', '1209752', '2', '个');
INSERT INTO `consume_inform` VALUES ('16', '棉签', ' 新芝·（SCIENTZ）', '14', '2324232', '2020-09-14', '78576', '1209752', '3', '个');
INSERT INTO `consume_inform` VALUES ('17', '五格温度试纸', ' 新芝·（SCIENTZ）', '4', '2324232', '2020-09-14', '78576', '1209752', '3', '个');
INSERT INTO `consume_inform` VALUES ('18', '氦气', '艾帝威', '5', '2324232', '2020-09-14', '11061565', '1209752', '4', '孔');
INSERT INTO `consume_inform` VALUES ('19', '氖气', '艾帝威', '5', '2324232', '2020-09-14', '11061565', '1209752', '4', '孔');
INSERT INTO `consume_inform` VALUES ('20', 'PH精密试纸|精密试纸|PH试纸', '艾帝威', '5', '2324232', '2020-09-14', '11061565', '1209752', '6', '孔');
INSERT INTO `consume_inform` VALUES ('21', '北京环迪长辰橡优质胶管橡胶管', '艾帝威', '5', '2324232', '2020-09-14', '11061565', '1209752', '8', '孔');
INSERT INTO `consume_inform` VALUES ('22', '天津市奥淇洛谱乳胶管橡皮管输血胶管、胶管', '艾帝威', '5', '2324232', '2020-09-14', '11061565', '1209752', '8', '孔');
INSERT INTO `consume_inform` VALUES ('23', '国药集团化学试单标记移液吸管（A级）', '艾帝威', '5', '2324232', '2020-09-14', '11061565', '1209752', '8', '孔');

#consume_norm table 耗材设备管理制度表
DROP TABLE IF EXISTS `consume_norm`;
CREATE TABLE `consume_norm` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `information_source` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL COMMENT '2020-05-08',
  `content` text,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `consume_norm` VALUES ('1', '耗材设备借还制度', '教务处', '擎天柱', '2020-09-23 00:00:00', '<p><span>一、任课教师因教学需要的仪器、材料等物品，由教师本人到仪器室办理借用手续，在借用仪器时要仔细检查和了解仪器的完好程度使用完毕，由教师本人将所借用的物品送还仪器室，由实验员检查验收后，注销借用手续。</span></p><p><span>二、仪器室要有仪器借还登记册，内容包括借还的时间，仪器数量、仪器完好情况，借用时领取人要签字，归还时实验员要登记。</span></p><p><span>三、校内有关部门或人员因非教学需要借用仪器，应由教务（导）主任批准签字后，才能办理借用手续。</span></p><p><span>四、非教育单位不得借用。兄弟学校之间因教学急需借用仪器时要凭单位介绍信并经主管校长批准签字后才能办理借用手续。</span></p><p><span>五、学生因课外科技活动需要借用教学仪器时，收辅导教师代为借用并负责按时归还。</span></p><p><span>六、教学仪器外借后应及时归还，每学期末必须将仪器全部交回实验室，对到期不还者，由批准人员负责追回。</span></p><p><span>七、归还的仪器如有损坏现象，借用人要说明原因，能够修理的待修好后再办理交还手续，不能修理的，待查明原因，按有关规定处理后再办交还手12注明处理意见。</span></p>', null);

#consume_purchase table 耗材申请购置表
DROP TABLE IF EXISTS `consume_purchase`;
CREATE TABLE `consume_purchase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `consume_id` int DEFAULT NULL,
  `num` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `applicant` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '0:申请购置中；1购置完成；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `consume_purchase` VALUES ('1', '4', '10', '2020-09-15', '董铠', '4');
INSERT INTO `consume_purchase` VALUES ('2', '16', '5', '2020-09-08', '东海', '4');
INSERT INTO `consume_purchase` VALUES ('3', '14', '5', '2020-09-15', '董铠', '4');
INSERT INTO `consume_purchase` VALUES ('4', '14', '2', '2020-09-16', '董铠', '4');
INSERT INTO `consume_purchase` VALUES ('5', '14', '5', '2020-09-15', '董铠', '4');
INSERT INTO `consume_purchase` VALUES ('6', '14', '5', '2020-09-15', '董铠', '4');
INSERT INTO `consume_purchase` VALUES ('7', '15', '2', '2020-09-14', '2', '0');

#consume_type table 耗材类型表
DROP TABLE IF EXISTS `consume_type`;
CREATE TABLE `consume_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
INSERT INTO `consume_type` VALUES ('1', '陶瓷制品');
INSERT INTO `consume_type` VALUES ('2', '实验器材');
INSERT INTO `consume_type` VALUES ('3', '棉质制品');
INSERT INTO `consume_type` VALUES ('4', '专用气体');
INSERT INTO `consume_type` VALUES ('5', '金属制品');
INSERT INTO `consume_type` VALUES ('6', '纸类制品');
INSERT INTO `consume_type` VALUES ('7', '生化耗材');
INSERT INTO `consume_type` VALUES ('8', '橡胶制品');

#device table 设备表
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int NOT NULL AUTO_INCREMENT,
  `factime` date DEFAULT NULL,
  `device_model_id` int DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '0正常 1上报维修  2上报报废 3维修进行中 4报废进行中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
INSERT INTO `device` VALUES ('1', '2020-09-16', '1', '7e144eb9-fcb4-4be7-9fee-7a0b6bcf61d8', '0');
INSERT INTO `device` VALUES ('2', '2020-09-16', '1', '9cc6ffed-31c8-4b71-aece-50208f96a3e3', '0');
INSERT INTO `device` VALUES ('3', '2020-09-16', '1', '5382fbd5-f459-4a9b-96e6-807deed9c15c', '0');
INSERT INTO `device` VALUES ('4', '2020-09-16', '1', '396ba00c-5ba5-4f94-9cc4-7ce06f1c34bc', '0');
INSERT INTO `device` VALUES ('5', '2020-09-16', '1', '3132a45c-7296-4dc3-aa74-60b8f184bc87', '4');
INSERT INTO `device` VALUES ('6', '2020-09-16', '1', '13cba1fa-53a2-4efa-91f5-63bf825d9e6e', '0');
INSERT INTO `device` VALUES ('7', '2020-09-16', '1', 'eb920d4c-afc1-4b23-82c6-dd87ab2e1fe2', '0');
INSERT INTO `device` VALUES ('8', '2020-09-16', '1', 'a927f563-bdd6-4bd1-bc0b-006491bbfc1c', '0');
INSERT INTO `device` VALUES ('9', '2020-09-16', '1', 'b0a8103b-cce9-41a7-a3f3-7ad82ee85bdb', '0');
INSERT INTO `device` VALUES ('10', '2020-09-16', '1', '1710fef8-86a3-4857-bf8e-56e9b87804f3', '0');
INSERT INTO `device` VALUES ('11', '2020-09-16', '1', '72bce475-c136-4643-a200-aaf8ad4bce7b', '0');
INSERT INTO `device` VALUES ('12', '2020-09-16', '1', 'b49ea909-72e1-4f12-9183-5ece72a430a8', '0');
INSERT INTO `device` VALUES ('13', '2020-09-16', '1', 'a6426052-c4dc-4161-93a6-2448a21b2cf7', '0');
INSERT INTO `device` VALUES ('14', '2020-09-16', '1', 'bebb9fc6-da97-41e4-8516-de3605094e77', '0');
INSERT INTO `device` VALUES ('15', '2020-09-16', '1', 'd0fa2401-d923-43a3-b7b2-8e789b03a69f', '0');
INSERT INTO `device` VALUES ('16', '2020-09-16', '1', '4efbe662-cfc8-479b-a30d-40fae5559473', '0');
INSERT INTO `device` VALUES ('17', '2020-09-16', '1', 'ba414db9-e6f6-4062-bee9-3ecd8c2d2304', '0');
INSERT INTO `device` VALUES ('18', '2020-09-16', '1', '6fc93744-03ba-4ac6-adbb-a02357d39a31', '0');
INSERT INTO `device` VALUES ('19', '2020-09-16', '1', '78771020-3336-42c3-a652-eaf6e477d380', '0');
INSERT INTO `device` VALUES ('20', '2020-09-16', '1', 'e8d2c238-4a5a-4a55-b5ee-0ca0ca623b9d', '0');
INSERT INTO `device` VALUES ('21', '2020-09-16', '1', '178277e9-65f8-4b1c-9813-1b0a9098aacd', '0');
INSERT INTO `device` VALUES ('22', '2020-09-16', '1', 'ce60b592-8139-4734-aa7d-51790c67ba6a', '0');
INSERT INTO `device` VALUES ('23', '2020-09-16', '1', '215afc95-53c3-4a10-883b-22d4cc795353', '0');
INSERT INTO `device` VALUES ('24', '2020-09-16', '1', 'd7e0b434-033c-49cb-9fcb-6dc8a04f1fde', '0');
INSERT INTO `device` VALUES ('25', '2020-09-16', '1', 'a4f10492-fce3-4a7a-bb6a-7caca7f20833', '0');
INSERT INTO `device` VALUES ('26', '2020-09-16', '1', 'a5188dbb-687b-4797-8155-4e50d0f8cd82', '0');
INSERT INTO `device` VALUES ('27', '2020-09-16', '1', '4367bd47-ed4f-4beb-93ff-846bd9e10f9b', '0');
INSERT INTO `device` VALUES ('28', '2020-09-16', '1', '27e0e01a-1343-4d74-b95e-2c1607a40ec1', '0');
INSERT INTO `device` VALUES ('29', '2020-09-16', '1', 'e9560391-39b8-4fa7-81bd-ecae3bb21888', '0');
INSERT INTO `device` VALUES ('30', '2020-09-16', '1', 'c005f6db-463f-4fc3-8b13-e68933396ac3', '0');
INSERT INTO `device` VALUES ('31', '2020-09-16', '1', '219cc2e6-ed70-4f3c-a0ef-af109f3d63c1', '0');
INSERT INTO `device` VALUES ('32', '2020-09-16', '1', '1d93b7e7-0e4c-4926-81a3-485b80dee25b', '0');
INSERT INTO `device` VALUES ('33', '2020-09-16', '1', '28f82bd9-5ab1-438d-969b-39a89acdc260', '0');
INSERT INTO `device` VALUES ('34', '2020-09-16', '1', 'e4c08bdf-5f52-4240-8630-e5cb8a5e8c97', '0');
INSERT INTO `device` VALUES ('35', '2020-09-16', '1', 'ff5f3bf3-865c-4612-b3b4-74a8cc16d5e4', '0');
INSERT INTO `device` VALUES ('36', '2020-09-16', '1', '4b504022-b897-442d-bb02-b786b8b984fa', '0');
INSERT INTO `device` VALUES ('37', '2020-09-16', '1', '50e258b6-7cda-4823-921d-d0f4ba614691', '0');
INSERT INTO `device` VALUES ('38', '2020-09-16', '1', '3727b3ee-89d4-42bc-87a6-e48a1bd631b8', '0');
INSERT INTO `device` VALUES ('39', '2020-09-16', '1', 'e53a6c5d-fb7c-47e9-bab4-30dbe7eb92df', '0');
INSERT INTO `device` VALUES ('40', '2020-09-16', '1', '953882f6-f511-4094-817f-ab488ac09e70', '0');
INSERT INTO `device` VALUES ('41', '2019-12-12', '2', '6b663061-384a-4164-b1fc-72e8d1491e44', '0');
INSERT INTO `device` VALUES ('42', '2019-12-12', '2', '4dd8814e-6743-4b3b-bfcd-a4969867319d', '0');
INSERT INTO `device` VALUES ('43', '2017-02-06', '3', 'eb57a22d-54ed-4f6c-97db-aa79d5ee1942', '0');
INSERT INTO `device` VALUES ('44', '2017-02-06', '3', '8743faed-65d5-4462-a625-719a60c0b647', '0');
INSERT INTO `device` VALUES ('45', '2017-02-06', '4', 'd9c2a654-ee6e-4a90-a2b9-dc938ada285a', '0');
INSERT INTO `device` VALUES ('46', '2017-02-06', '4', '0d82305b-8c42-4166-9295-bf09f2f9ea53', '0');
INSERT INTO `device` VALUES ('47', '2017-02-06', '5', '6c61c3d8-017f-4b41-8131-c65e28a81037', '0');
INSERT INTO `device` VALUES ('48', '2017-02-06', '5', '9f562be2-549d-4ff2-8ed9-335aa6990d6a', '0');
INSERT INTO `device` VALUES ('49', '2017-02-06', '6', '3c4318d0-a02c-4580-9cf3-9d80e422992c', '0');
INSERT INTO `device` VALUES ('50', '2017-02-06', '6', '0d34d5b5-6650-4e8d-8cb2-3e965477cfb8', '0');
INSERT INTO `device` VALUES ('51', '2017-02-06', '6', '981e74bf-eb3a-4ac4-8721-4c874c7daa62', '0');
INSERT INTO `device` VALUES ('52', '2017-02-06', '6', '3c469cc5-a43c-474a-8a13-52e8bfda3698', '0');
INSERT INTO `device` VALUES ('53', '2017-02-06', '6', '4ec31303-c70e-4d61-b32d-8678cd74a4b1', '0');
INSERT INTO `device` VALUES ('54', '2017-02-06', '6', '65abb264-66ff-4766-9982-05b12e777c33', '0');
INSERT INTO `device` VALUES ('55', '2017-02-06', '6', 'a4cac756-7c01-4298-899c-e8f6e0f2097d', '0');
INSERT INTO `device` VALUES ('56', '2017-02-06', '6', '27d8cf22-6cb4-486a-bc8a-26f2ad792890', '0');
INSERT INTO `device` VALUES ('57', '2017-02-06', '6', 'dfe00ace-dc0b-4afd-8b04-b295f5d4e73c', '0');
INSERT INTO `device` VALUES ('58', '2017-02-06', '6', 'a786549b-28d0-434c-8006-b0ae148d5924', '0');
INSERT INTO `device` VALUES ('59', '2017-02-06', '7', '65a371af-f0e1-470a-9734-2587d7ad6be9', '0');
INSERT INTO `device` VALUES ('60', '2017-02-06', '7', 'db8093c5-8ac8-4613-934e-2b1b58c1f327', '0');
INSERT INTO `device` VALUES ('61', '2017-02-06', '7', '0c3490c6-194d-4340-b433-d3f7ba38377f', '0');
INSERT INTO `device` VALUES ('62', '2017-02-06', '7', 'e1a0f10f-79c0-4b28-a4e4-e1f770842c9a', '0');
INSERT INTO `device` VALUES ('63', '2017-02-06', '7', '365b7877-d7e2-4cc9-9553-9be3ca45e421', '0');
INSERT INTO `device` VALUES ('64', '2017-02-06', '7', 'e4e6eec7-d06f-496b-b4aa-d6f12cec9827', '0');
INSERT INTO `device` VALUES ('65', '2017-02-06', '7', '4bb0f96b-c964-46b4-ad50-180fae58acf7', '0');
INSERT INTO `device` VALUES ('66', '2017-02-06', '7', 'e769d855-0d28-429d-bb0b-2f0723676829', '0');
INSERT INTO `device` VALUES ('67', '2017-02-06', '7', 'a0cea21f-91d0-4a42-813e-23d29957b067', '0');
INSERT INTO `device` VALUES ('68', '2017-02-06', '7', '065d672c-fbd9-4e05-ad97-ac6aaff332ef', '0');
INSERT INTO `device` VALUES ('69', '2017-02-06', '7', '9f9d7270-f2f5-4a5f-8f1e-cbc3b7bb4bcb', '0');
INSERT INTO `device` VALUES ('70', '2017-02-06', '7', '276ce15f-2c6d-42e8-807f-eb604531b9fc', '0');
INSERT INTO `device` VALUES ('71', '2017-02-06', '7', '2d55b870-7b1c-4fce-a2f1-540bd54f035b', '0');
INSERT INTO `device` VALUES ('72', '2017-02-06', '7', '64208cb8-c09d-4ef1-b07d-f80cc50461e8', '0');
INSERT INTO `device` VALUES ('73', '2017-02-06', '7', 'a1c5ba3a-ab0b-44ca-938c-6dd71bbba64b', '0');
INSERT INTO `device` VALUES ('74', '2017-02-06', '7', 'c21c8211-90ff-42d6-933a-b2050fc2a743', '0');
INSERT INTO `device` VALUES ('75', '2017-02-06', '7', 'a1d33e06-3f86-43e2-b112-402adcb7eeaf', '0');
INSERT INTO `device` VALUES ('76', '2017-02-06', '7', 'db0382f2-c5d9-4cd4-904a-6a26a350a41a', '0');
INSERT INTO `device` VALUES ('77', '2017-02-06', '7', 'ea2a9366-4bcb-46a9-9ac8-f8951697c9b2', '0');
INSERT INTO `device` VALUES ('78', '2017-02-06', '7', '607e5275-eacd-4a67-911a-d8fe2f677ee3', '0');
INSERT INTO `device` VALUES ('79', '2017-02-06', '7', '7857725a-9015-4877-b83b-5ee931159936', '0');
INSERT INTO `device` VALUES ('80', '2017-02-06', '7', '3e43438e-d4ae-4c89-b7bb-428d299756a7', '0');
INSERT INTO `device` VALUES ('81', '2017-02-06', '7', 'fe6308c2-a876-47e8-ad13-712fd05c34b2', '0');
INSERT INTO `device` VALUES ('82', '2017-02-06', '7', 'ecb3e7ca-ed08-4ee7-8557-ab4826780c05', '0');
INSERT INTO `device` VALUES ('83', '2017-02-06', '7', '5d2f3f0c-edb5-4d83-ae68-df383077a151', '0');
INSERT INTO `device` VALUES ('84', '2017-02-06', '7', '9ebcd93e-78b6-47c8-ac9e-a45cdcb19111', '0');
INSERT INTO `device` VALUES ('85', '2017-02-06', '7', '9baa5e4c-9182-4568-a81b-6a67915a566a', '0');
INSERT INTO `device` VALUES ('86', '2017-02-06', '7', 'efa94f64-22db-4d53-9e25-1a7cd4ce9ff7', '0');
INSERT INTO `device` VALUES ('87', '2017-02-06', '7', 'e21db3d0-65cd-42c7-89c1-cfc2eebed2a5', '0');
INSERT INTO `device` VALUES ('88', '2017-02-06', '7', '46eda125-8925-489b-8328-dbf6b0f0cc61', '0');
INSERT INTO `device` VALUES ('89', '2017-02-06', '8', '3e1ff25f-9d95-4768-9b45-4b00d462c677', '0');
INSERT INTO `device` VALUES ('90', '2017-02-06', '8', 'e01cb6f3-aaf0-4d1b-8a77-656b3b12e0c6', '0');
INSERT INTO `device` VALUES ('91', '2017-02-06', '8', 'b39333ed-a539-45a6-8428-8d6292947790', '0');
INSERT INTO `device` VALUES ('92', '2017-02-06', '8', 'd1fc65bd-85ca-42cc-9b64-be8109b1635f', '0');
INSERT INTO `device` VALUES ('93', '2017-02-06', '8', '3958e0fd-7dd6-49e9-8ac0-3249df38b2a9', '0');
INSERT INTO `device` VALUES ('94', '2017-02-06', '8', '7f00ed29-5c73-4693-b7c4-cbc273246d36', '0');
INSERT INTO `device` VALUES ('95', '2017-02-06', '8', '5bcc2591-89ba-4ea0-9fa1-c6559f90f7f5', '0');
INSERT INTO `device` VALUES ('96', '2017-02-06', '8', '436eab5c-7278-40ff-8d96-e22ed645d98d', '0');
INSERT INTO `device` VALUES ('97', '2017-02-06', '8', '93d23e6b-78fd-464b-82e3-586e528b0a22', '0');
INSERT INTO `device` VALUES ('98', '2017-02-06', '8', 'cd3b3225-ce74-4903-8848-d3cbcffb590a', '0');
INSERT INTO `device` VALUES ('99', '2017-02-06', '8', '5710e3bc-7624-47c7-8764-7ccf4e395cc0', '0');
INSERT INTO `device` VALUES ('100', '2017-02-06', '8', '714ef067-221f-44ca-b2c5-fe86d3dc77ab', '0');
INSERT INTO `device` VALUES ('101', '2017-02-06', '8', '22dbcc88-a026-466b-8697-e1cc54cdba1a', '0');
INSERT INTO `device` VALUES ('102', '2017-02-06', '8', 'c842bbd6-3caf-4de2-acaf-13c4c18165b8', '0');
INSERT INTO `device` VALUES ('103', '2017-02-06', '8', 'b9b18473-77ca-42e8-a543-a17b0a0fbb11', '0');
INSERT INTO `device` VALUES ('104', '2017-02-06', '8', '3b8c26b0-9796-48c0-9421-e81bf586137a', '0');

#device_model table 设备型号表
DROP TABLE IF EXISTS `device_model`;
CREATE TABLE `device_model` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_name` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `facid` varchar(255) DEFAULT NULL,
  `proid` varchar(255) DEFAULT NULL,
  `supid` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `lifetime` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  `unit_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
INSERT INTO `device_model` VALUES ('1', '精密ph计', '柯迪达CT-6021A', 'RG78201912129', '78576', '1209752', '1', '1000', '40', '个');
INSERT INTO `device_model` VALUES ('2', '数字贝克曼温度计', '希玛AT-380', 'RG45202003178', '78576', '1209752', '1', '1000', '2', '个');
INSERT INTO `device_model` VALUES ('3', '玻璃器皿', ' 信蓝铭', 'RG45202003178', '78576', '1305489', '1', '1000', '2', '个');
INSERT INTO `device_model` VALUES ('4', '药品柜', ' 迪尔', 'RG45202003178', '78576', '1305489', '2', '1000', '2', '个');
INSERT INTO `device_model` VALUES ('5', '三联水嘴', ' 菲可帝睿', 'QM15201809155', '78576', '1305489', '2', '1000', '2', '个');
INSERT INTO `device_model` VALUES ('6', '实验室玻璃干燥器', ' 菲可帝睿', 'QM15201809155', '18151', '11061565', '2', '1000', '10', '个');
INSERT INTO `device_model` VALUES ('7', '精密ph计', ' 菲可帝睿', 'QM15201809155', '18151', '11061565', '3', '1000', '30', '个');
INSERT INTO `device_model` VALUES ('8', '磁力搅拌器', ' 洛克菲勒（LUO KE FEI LE）', 'TH82201712088', '18151', '11061565', '4', '1000', '16', '个');

#device_type table 设备类型表
DROP TABLE IF EXISTS `device_type`;
CREATE TABLE `device_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `device_type` VALUES ('1', '物理学');
INSERT INTO `device_type` VALUES ('2', '化学');
INSERT INTO `device_type` VALUES ('3', '生物学');
INSERT INTO `device_type` VALUES ('4', 'IT');
INSERT INTO `device_type` VALUES ('5', '数学');
INSERT INTO `device_type` VALUES ('6', '医学');

#experiment_project table 实验项目表
DROP TABLE IF EXISTS `experiment_project`;
CREATE TABLE `experiment_project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `experiment_name` varchar(255) DEFAULT NULL,
  `experiment_target` varchar(255) DEFAULT NULL,
  `experiment_content` varchar(255) DEFAULT NULL,
  `experiment_process` varchar(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '0未完成，1已完成，2已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO `experiment_project` VALUES ('1', '单缝衍射实验', '1.观察单缝演示现象，了解其特点。2.测量单缝衍射时的相对强光分布。3.利用光强分布图形计算单缝宽度', '1.观察单缝衍射的衍射图形。2.测定单缝衍射的光强分布。3.利用光强分布图形计算单缝宽度', '<p>波长为λ的单色平行光垂直照射到单缝上，在接收屏上，将得到单缝衍射图样，即一组平行于狭缝的明暗相间条纹。当光照射到光电探头表面时在光电探头的上下两表面产生电势差△U，△U的大小与入射光强成线性关系。光电探头与光电流放大器连接形成回路，回路中电流的大小与△U成正比。因此，通过电流的大小就可以反映出入射到光电探头的光强大小</p>', '0');
INSERT INTO `experiment_project` VALUES ('2', '蒸馏和沸点的测定', '1.熟悉蒸馏法分离混合物的方法。2.掌握测定化合物沸点的方法。', '1.微量法测定物质沸点原理。2.蒸馏原理', '<p><font size=\"3\">1.酒精的蒸馏 2.微量法测沸点</font></p>', '0');
INSERT INTO `experiment_project` VALUES ('3', '重结晶及过滤', '1.学习重结晶提纯固态有机物的原理和方法 2.学习抽滤和热过滤的操作', '利用混合物中各组分在某种溶液中的溶解度不同，或在同一溶液中不同温度时溶解度不同而使他们分离', '<p><font size=\"3\">1.称取3g乙醚苯胺。放入250ml烧杯中，加入80ml水，加热至沸腾，若还未溶解可适量加入热水，搅拌，加热至沸腾。2.稍冷后加入适量活性炭于溶液中，煮沸5-10min，趁热抽滤。3.将滤液放入冰水中结晶，将所得结晶压平。再次抽滤，称量洁净质量m。</font></p>', '0');
INSERT INTO `experiment_project` VALUES ('4', '蛋白质和氨基酸的呈色反应', '1、了解构成蛋白质的基本结构单位及主要联接方。2、了解蛋白质和某些氨基酸的呈色反应原理。3、学习几种常用的鉴定蛋白质和氨基酸的方法', '1.双缩脲反应。2.䒡三酮反应。3.苯环的黄色反应。4.乙醛酸的反应。5.偶氮反应。6.醋酸铅放映。', '<p><font size=\"3\">1.双缩脲反应。2.䒡三酮反应。3.苯环的黄色反应。4.乙醛酸的反应。5.偶氮反应。6.醋酸铅放映。</font></p>', '0');
INSERT INTO `experiment_project` VALUES ('5', 'c++实验', '1.熟悉Visual studio 6.0集 成开发环境。2.学习新建和打开控制台应用程序的方法。3.通过运行示例程序了解程序开发过程。', '输入一摄氏温度，输出显示所转换的华氏温度。', '<p><font size=\"3\">编写代码，实现实验</font></p>', '0');

#laboratory table 实验室表
DROP TABLE IF EXISTS `laboratory`;
CREATE TABLE `laboratory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `laboratory_name` varchar(255) DEFAULT NULL,
  `faculty` varchar(255) DEFAULT NULL,
  `laboratory_responsibility` varchar(255) DEFAULT NULL,
  `img_src` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `laboratory` VALUES ('1', '化学基础实验室', '化学与生物工程学院', '张帆帆', '1');
INSERT INTO `laboratory` VALUES ('2', '生物工程实验室', '生科系', '秦老东', '2');
INSERT INTO `laboratory` VALUES ('3', '电子电工实践育人基地', '电子工程系', '贺擎天', '3');
INSERT INTO `laboratory` VALUES ('4', '电子科学与技术实验室', '电子工程系', '贺青青', '4');
INSERT INTO `laboratory` VALUES ('5', '数学实验室', '数学系', '杨凡凡', '5');
INSERT INTO `laboratory` VALUES ('6', '化学实验室', '化学化工系', '张凡凡', '6');
INSERT INTO `laboratory` VALUES ('7', '化学实验室', '化学化工系', '张凡凡', '7');


#laboratory_device table 实验室设备表
DROP TABLE IF EXISTS `laboratory_device`;
CREATE TABLE `laboratory_device` (
  `id` int NOT NULL AUTO_INCREMENT,
  `laboratory_id` int DEFAULT NULL,
  `device_id` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
INSERT INTO `laboratory_device` VALUES ('1', '1', '45', '0');
INSERT INTO `laboratory_device` VALUES ('2', '1', '46', '0');
INSERT INTO `laboratory_device` VALUES ('3', '1', '59', '0');
INSERT INTO `laboratory_device` VALUES ('4', '1', '60', '0');
INSERT INTO `laboratory_device` VALUES ('5', '2', '61', '0');
INSERT INTO `laboratory_device` VALUES ('6', '2', '62', '0');
INSERT INTO `laboratory_device` VALUES ('7', '2', '63', '0');
INSERT INTO `laboratory_device` VALUES ('8', '2', '64', '0');
INSERT INTO `laboratory_device` VALUES ('9', '2', '65', '0');
INSERT INTO `laboratory_device` VALUES ('10', '2', '66', '0');
INSERT INTO `laboratory_device` VALUES ('11', '2', '67', '0');
INSERT INTO `laboratory_device` VALUES ('12', '2', '68', '0');
INSERT INTO `laboratory_device` VALUES ('13', '2', '69', '0');
INSERT INTO `laboratory_device` VALUES ('14', '2', '70', '0');
INSERT INTO `laboratory_device` VALUES ('15', '3', '89', '0');
INSERT INTO `laboratory_device` VALUES ('16', '3', '90', '0');
INSERT INTO `laboratory_device` VALUES ('17', '4', '91', '0');
INSERT INTO `laboratory_device` VALUES ('18', '4', '92', '0');
INSERT INTO `laboratory_device` VALUES ('19', '4', '93', '0');
INSERT INTO `laboratory_device` VALUES ('20', '5', '43', '0');
INSERT INTO `laboratory_device` VALUES ('21', '5', '44', '0');
INSERT INTO `laboratory_device` VALUES ('22', '6', '47', '0');
INSERT INTO `laboratory_device` VALUES ('23', '6', '49', '0');
INSERT INTO `laboratory_device` VALUES ('24', '7', '1', '0');
INSERT INTO `laboratory_device` VALUES ('25', '7', '94', '0');
INSERT INTO `laboratory_device` VALUES ('26', '7', '71', '0');
INSERT INTO `laboratory_device` VALUES ('27', '7', '5', '0');


#laboratory_image table 实验室图片表
DROP TABLE IF EXISTS `laboratory_image`;
CREATE TABLE `laboratory_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `laboratory_image` VALUES ('1', 'D:\\lab-app\\upload\\2-2.png');
INSERT INTO `laboratory_image` VALUES ('2', 'D:\\lab-app\\upload\\2-3.png');
INSERT INTO `laboratory_image` VALUES ('3', 'D:\\lab-app\\upload\\1-3.png');
INSERT INTO `laboratory_image` VALUES ('4', 'D:\\lab-app\\upload\\class.jpg');
INSERT INTO `laboratory_image` VALUES ('5', 'D:\\lab-app\\upload\\2-2.png');
INSERT INTO `laboratory_image` VALUES ('6', 'D:\\lab-app\\upload\\banner3.png');
INSERT INTO `laboratory_image` VALUES ('7', 'D:\\lab-app\\upload\\banner3.png');

#menu table 菜单表
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pid` int DEFAULT NULL,
  `priority` int DEFAULT NULL COMMENT '优先级：越高越前',
  `access` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
INSERT INTO `menu` VALUES ('1', '设备', '0', '1', null);
INSERT INTO `menu` VALUES ('2', '耗材', '0', '2', null);
INSERT INTO `menu` VALUES ('3', '预约', '0', '3', null);
INSERT INTO `menu` VALUES ('4', '项目', '0', '4', null);
INSERT INTO `menu` VALUES ('5', '系统', '0', '5', null);
INSERT INTO `menu` VALUES ('6', '设备信息查看', '1', '1', '/device/device.html');
INSERT INTO `menu` VALUES ('7', '项目信息', '1', '2', '/device/lab-information.html');
INSERT INTO `menu` VALUES ('8', '实验状态监控', '1', '3', '/device/monitaring.html');
INSERT INTO `menu` VALUES ('9', '智能分析', '1', '4', '/device/device-information.html');
INSERT INTO `menu` VALUES ('10', '基本资料', '2', '1', '/consume/consume-inform.html');
INSERT INTO `menu` VALUES ('11', '申请购置', '2', '2', '/consume/consume-storage.html');
INSERT INTO `menu` VALUES ('12', '保管领用', '2', '3', '/consume/consume-custudy.html');
INSERT INTO `menu` VALUES ('13', '管理标准', '2', '4', '/consume/consume-norm.html');
INSERT INTO `menu` VALUES ('14', '预约安排', '3', '1', '/arrange/arrange-page.html');
INSERT INTO `menu` VALUES ('15', '实验项目', '4', '1', '/project/project-science.html');
INSERT INTO `menu` VALUES ('16', '教学资源', '4', '2', '/project/project-check.html');
INSERT INTO `menu` VALUES ('17', '密码修改', '5', '1', '/system/system-password.html');

#news table 新闻表
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `information_source` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `news` VALUES ('1', '校纪委召开第二十一次委员会议', '教务处', '王君', '2020-09-07', '<p><span>9月3日上午，校纪委召开第二十一次（2020年第3次）委员会议。会议学习了习近平总书记关于“节约粮食、制止餐饮浪费行为”重要指示批示精神，重温了习近平总书记《思政课是落实立德树人根本任务的关键课程》的重要讲话精神。会议还安排了对贯彻落实习近平总书记批示和讲话精神、巡视整改和疫情防控的监督检查工作。校纪委书记、监察专员张俊平同志要求纪检监察干部要提高政治站位，做到“两个维护”，深入一线，发挥“探头”作用，聚焦关键少数，在日常监督上集中发力，把监督落到实处，以严明的纪律确保各项工作落实落细落到位。会议还对其他事项进行了安排。</span></p><p><span><img src=\"http://192.168.0.117:8080/lab/newsimage/4\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/5\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/6\" alt=\"\"><br></span></p>');
INSERT INTO `news` VALUES ('2', '校党委书记王新淮等一行出席全国高职院校爱国主义教育培训基地合作', '教务处', '王君', '2020-09-08', '<p class=\"vsbcontent_start\">8月29日至30日，全国高职院校爱国主义教育培训基地合作联盟大会在遵义职业技术学院举行。作为联盟副理事长单位，我校党委书记王新淮等一行出席大会。来自全国30多所高职院校80多位专家学者现场参加了本次大会，全国广大高职院校线上观摩了大会实况。</p><p>会上，贵州省教育厅职成教处处长江疆代表省教育厅致辞，遵义职业技术学院党委书记李凌、我校党委书记王新淮先后主持了大会，联盟理事长、威海职业学院党委书记吴永刚作主旨报告，遵义职业技术学院院长颜永强、吉安职业技术学院相关负责人王霞作经验交流发言，《光明日报》职教版主编练玉春作大会发言，联盟秘书长、威海职业学院党委副书记李建刚作联盟工作报告。</p><p>李凌书记致辞指出，此次联盟大会的主要目的是：全面总结联盟成立以来工作开展情况，科学谋划下步重点工作，健全完善工作运行机制，全面推进新时代爱国主义教育往深里走、往实里走、往心里走，努力让全国高职院校爱国主义教育培训基地合作联盟成为新时代大力推动爱国主义教育的时代先行者、职教排头兵。</p><p>江疆处长讲话指出，爱国主义是中华民族民族精神的核心，爱国主义教育是思想的洗礼、精神的熏陶。贵州省各职业院校始终把爱国主义作为固本培元、凝心铸魂的宏伟工程，着力培养爱国之情、砥砺强国之志、实践报国之行。贵州将继续加强新时代爱国主义教育，不断增强“四个意识”、坚定“四个自信”、做到“两个维护”，使爱国主义成为贵州人民的坚定信念、精神力量和自觉行动。</p><p>吴永刚书记作主旨发言，从社会、学校和学生三个层面，指出职业教育重在教育，强在“德技并修”，抓思政是根本，爱国主义是思政教育的灵魂。</p><p>王新淮书记在主持时指出，全国高职院校爱国主义教育培训基地合作联盟会议在遵义召开，必将为全国高职院校爱国主义教育带来历史性转折与发展，并进一步推动全国高职院校爱国主义教育合作。</p><p>练玉春主编在发言时指出，历史是最好的爱国主义教育素材，传承好中国共产党强大的爱国主义教育传统，是摆在教育领域的紧迫课题。高职院校成立爱国主义教育培训基地联盟走在了全国教育前列，今后要以联盟为平台，进一步加强高职爱国主义教育的实践教学、现场教学，现场教学是最具有生命力和号召力的爱国主义教育方式和途径。</p><p>会上，遵义职业学院教师作了精彩的爱国主义教育红色快板表演。</p><p>会议召开期间，联盟理事会组织了重温国家民族和红色文化历史的长卷签名活动。50米长，0.99米宽的签名卷轴代表了华夏5000年的历史长河中，因为有了中国共产党99年光辉的奋斗历史，才让中国人民从站起来，到富起来、强起来，为实现中国梦而走上新的长征。参会代表庄重地把自己的名字签在甲午海战历史纪念馆旁，签在井冈山上、遵义会议会址旁、宝塔山下，签在百团大战纪念碑旁，让自己与历史融为一体，表达了强烈的爱国主义情怀。</p><p>参会35所学校代表还在遵义职业技术学院校园的“爱国林”里，共同植下了35株银杏树，纪念《新时代爱国主义教育实施纲要》的颁布及爱国主义教育培训基地合作联盟会议召开。</p><p>为深入贯彻落实中共中央、国务院印发的《新时代爱国主义教育实施纲要》，2019年底，23所高职院校在威海一致成立全国高职院校爱国主义教育培训基地合作联盟，我校被选为副理事长单位。联盟旨在共同搭建爱国主义教育培训合作平台、提升全国高职院校红色文化社会服务能力，以“合作、交流、服务、发展”为目标，充分发挥高职院校爱国主义教育和革命传统教育功能，打造爱国主义教育培训共同体，为中国特色社会主义事业培养更多全面发展的合格建设者和可靠接班人。</p><p><img src=\"http://192.168.0.117:8080/lab/newsimage/7\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/8\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/9\" alt=\"\"><br></p>');
INSERT INTO `news` VALUES ('3', '我校被评为“山西省创建文明校园先进学校”', '教务处', '秦桧', '2020-09-15', '<p class=\"vsbcontent_start\">日前，山西省精神文明建设指导委员会发布《山西省精神文明建设指导委员会关于表彰2018—2019年度山西省精神文明创建先进典型的决定》（晋文明委〔2020〕5号），我校被评为2018-2019年度“山西省创建文明校园先进学校”。</p><p>多年来，我校始终把文明校园创建工作作为一项基础性、全局性、长期性、长效性的工作来抓，以学习贯彻习近平新时代中国特色社会主义思想、培育践行社会主义核心价值观作为“主线”，贯穿于办学治校、教书育人全过程，融入学校教育事业发展历程，围绕“思想道德建设好、领导班子建设好、师德师风建设好、校园文化建设好、校园环境建设好、阵地建设管理好”的“六好目标”，抓实“六个坚持”，即坚持“思想引领铸信念、把舵领航掌大局、塑身修德践使命、文化滋养潜育人、环境熏陶涵品行、建设管控双把关”，文明校园创建工作取得了显著成效，全校师生员工政治立场理想信念坚定，思想境界道德品质升华，习近平新时代中国特色社会主义思想、社会主义核心价值观成为广大师生员工共同的价值追求和自觉行动，形成了健康积极、文明和谐、开拓创新、奋发有为的校园之风，推动了学校长足发展进步，连续多年被评为山西省文明单位标兵和山西省文明单位。</p><p>获此殊荣，是全校上下共同努力的结果。学校将以此次荣誉的获得为新的起点，更加重视文明校园建设工作，不忘育人初心、牢记树人使命，珍惜荣誉，再接再厉，持续提升文明校园的创建质量，奋力谱写文明校园创建工作新篇章。</p><p><img src=\"http://192.168.0.117:8080/lab/newsimage/10\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/11\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/12\" alt=\"\"><br></p>');
INSERT INTO `news` VALUES ('4', '追寻红色足迹、重温长征精神--记马院教师赴贵州参加社会实践研修活动', '教务处', '秦晖', '2020-09-15', '<p class=\"vsbcontent_start\">为深入学习贯彻习近平新时代中国特色社会主义思想，提升思想政治理论课教师队伍，马克思主义学院一行13人于8月21日至27日赴贵州参加由全国高校思想政治理论课教师研修基地（贵州师范大学）举办的“2020年高校思想政治理论课教师社会实践研修活动”。</p><p>根据培训安排，8月21日9时举行了开班仪式，并由著名长征史研究专家、贵州师范大学文学院副院长李俊教授作题为《红军长征在贵州》的专题报告。随后前往黔灵山，参观蒋介石关押张学良、杨虎城的麒麟洞、解放贵州革命烈士纪念碑、息烽集中营革命历史纪念馆接受革命传统教育，并在息烽集中营进行重温入党誓词的情境教学。接着参观遵义会议旧址，对遵义会议的意义和长征精神有了更加深入的领悟。在苟坝，思政课教师身穿红军装、提着马灯重走毛泽东当年走过的山间小道。最后前往茅台红军四渡赤水纪念园、贵阳孔学堂进行参观学习。</p><p>通过本次实践研修，更加坚定了思政课教师的共产主义理想信念，同时为思政课教学改革提供了丰富的素材，为全面提升我校思想政治理论课教学质量奠定了基础。</p><p><img src=\"http://192.168.0.117:8080/lab/newsimage/13\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/14\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/15\" alt=\"\"><br></p>');
INSERT INTO `news` VALUES ('5', '校党委理论学习中心组专题学习《习近平谈治国理政》第三卷', '教务处', '王俊', '2020-09-15', '<p class=\"vsbcontent_start\">8月27日上午，校党委理论学习中心组在办公楼二层会议室专题学习《习近平谈治国理政》第三卷。党委委员、纪委书记张俊平围绕《习近平谈治国理政》第三卷之“增强忧患意识，防范化解重大风险”专题进行了领学宣讲，中心组成员结合工作实际深入地开展了研讨交流。党委书记王新淮主持学习会。</p><p>张俊平在领学中详细阐释了《习近平谈治国理政》第三卷“增强忧患意识，防范化解重大风险”的精髓要义、内涵背景和时代要求，与大家交流了四点体会：一是要增强忧患意识，防范风险挑战；二是要坚持党对国家安全工作的绝对领导，坚持总体国家安全观；三是要坚持底线思维，着力防范化解重大风险；四是要发扬斗争精神，增强斗争本领，要练就政治慧眼，注重策略方法，积极投身实践。他指出，从忧患意识到国家安全，再到斗争精神和斗争本领，都着眼于国家发展、长治久安和民族复兴，是“以人民为中心”理念的集中体现，我们要认真学习，深刻领会，将理论收获与工作实践有机结合，贯彻好落实好指导好学校的具体工作，促进学校高质量发展。</p><p>杨晓明发言指出，一是要将政治建设摆在首位，党员领导干部要提高政治能力，善于从政治角度分析问题、解决问题；二是要树牢总体国家安全观，高度重视学生思想政治工作，不断完善思想政治工作体系，创新思想政治工作内容和形式，教育引导广大青年形成正确的世界观、人生观、价值观；三是领导干部要经受严格的思想淬炼、政治历练、实践锻炼，要坚持马克思主义在意识形态的指导地位，敢于斗争、善于斗争，在干部任用、职称评审等工作中加大对于政治素质的考核。</p><p>李锦元发言指出，通过学习进一步深入领会了习近平总书记的总体国家安全观的深刻内涵，国家安全涉及经济、政治、文化、社会、科技、外交等多方面，是安邦定国、人民幸福的重要基石，必须要全面贯彻落实总体国家安全观。在工作中一定要树立忧患意识，防范化解重大风险，做到防微杜渐，未雨绸缪，在学校工作实践中，要注意在教师队伍培养、学校专业设计、已有专业转型优化等方面早谋划、早预防，切实提高风险识别防控化解能力。</p><p>李富堂发言指出，要加强学校科技安全和网络安全工作，注重科研成果、科技应用、教学成果等的安全防范，全力防范化解科技和网络安全方面的风险。</p><p>赵丽生围绕树立忧患意识、提升领导干部能力水平提出了三点要求。一是全校领导干部都要树立忧患意识，要克服能力不足的危险，结合巡视整改、审计整改以及学校改革发展的要求，通过走出去开展红色文化学习和先进行业企业观摩，不断提升个人本领；二是在打造“双高校”和专业建设过程中要树立忧患意识，要立足山西转型发展要求，紧盯产业发展方向，深化产教融合校企合作，不断优化我校专业建设；三是在推动我校高质量创新发展进程中要树立忧患意识，要坚持“项目为王”，将服务大产业、大项目作为主攻方向，积极探索项目化教学，结合产业发展和企业需求，开展项目化教学改革，将产教融合校企合作真正落地落实。要充分挖掘年轻教师优势，为他们提供成长成才平台，努力打造在新业态新模式新产业新技术上拥有一定领先地位的标志性教学成果，为学校发展提供充足动力。</p><p>王新淮总结强调，要持续深入学习习近平总书记关于增强忧患意识，防范风险挑战的重要论述精神，进一步树牢“四个意识”，坚定“四个自信”，坚决做到“两个维护”。要全面贯彻落实总体国家安全观，坚持党对国家安全工作的绝对领导，要坚持底线思维，根据中央、省委有关要求研判本单位存在的风险隐患，在学校管理过程中要重视青年师生的思想政治工作，防范化解重大风险，要发扬斗争精神，讲求斗争艺术，坚持斗争原则，要有意识培养年轻干部的斗争本领。</p><p>对于进一步学习贯彻落实《习近平谈治国理政》第三卷，王新淮书记提出三点要求：一是要认真学好《习近平谈治国理政》第三卷内容，切实领会总书记关于“增强忧患意识，防范化解风险挑战”的精神实质和内涵，自觉把言行统一到总书记的讲话和党中央的决策部署上来；二是校领导班子成员要切实落实好意识形态工作责任制，履行好一岗双责，带好管好分管干部队伍，坚持底线思维，发扬斗争精神，切实做好各项工作；三是要把理论学习与工作实际紧密联系起来，提升自身和分管领域治理能力和水平，切实增强忧患意识，防范化解风险挑战，把学校发展改革中遇到的问题困难和巡视审计反馈的问题整改到位。</p><p><img src=\"http://192.168.0.117:8080/lab/newsimage/16\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/17\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/newsimage/18\" alt=\"\"><br></p>');

#news_image table 新闻图片表
DROP TABLE IF EXISTS `news_image`;
CREATE TABLE `news_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
INSERT INTO `news_image` VALUES ('1', 'D:\\lab-app\\upload\\1599705668356_41.png');
INSERT INTO `news_image` VALUES ('2', 'D:\\lab-app\\upload\\1599705673454_42.png');
INSERT INTO `news_image` VALUES ('3', 'D:\\lab-app\\upload\\1599705678065_43.png');
INSERT INTO `news_image` VALUES ('4', 'D:\\lab-app\\upload\\1599706170447_4DE7CA5040A37E358031441EC48_A6DA3662_2F523.jpg');
INSERT INTO `news_image` VALUES ('5', 'D:\\lab-app\\upload\\1599706175997_5F764A59A8C63C5ADBAF83D9EA9_BDB68855_322B1.jpg');
INSERT INTO `news_image` VALUES ('6', 'D:\\lab-app\\upload\\1599706182741_7C7040DD8053585987437C2279A_893AECC7_F379.jpg');
INSERT INTO `news_image` VALUES ('7', 'D:\\lab-app\\upload\\1599706408050_9DCE2514B463057CFEFFA0C6B45_311C5C1E_19B0B.jpg');
INSERT INTO `news_image` VALUES ('8', 'D:\\lab-app\\upload\\1599706413188_9DFD6330F41E480749CC06ABFE4_6A75D3EC_1153B.jpg');
INSERT INTO `news_image` VALUES ('9', 'D:\\lab-app\\upload\\1599706422983_056E05213E71996C92D25AF731F_FE5096B2_D76B.jpg');
INSERT INTO `news_image` VALUES ('10', 'D:\\lab-app\\upload\\1599706511585_561D1A025D1C8E5AAEFF9020D69_F9DEA9F4_20566.jpg');
INSERT INTO `news_image` VALUES ('11', 'D:\\lab-app\\upload\\1599706517946_800DDE90E283D87E33C1C0E235F_6A62353E_14B57.jpg');
INSERT INTO `news_image` VALUES ('12', 'D:\\lab-app\\upload\\1599706524427_98893BD260F6F86A02D11EDED96_2FD23168_2B057.jpg');
INSERT INTO `news_image` VALUES ('13', 'D:\\lab-app\\upload\\1599706670119_A944BAE7340545F85A8A5463B78_14BAB9F8_A8ED.jpg');
INSERT INTO `news_image` VALUES ('14', 'D:\\lab-app\\upload\\1599706679105_ABCFEE7901514564F73DFFF8F6C_6233CD72_1FDC0.jpg');
INSERT INTO `news_image` VALUES ('15', 'D:\\lab-app\\upload\\1599706686073_B4B27FC198E0E768D92B7D92CA4_035D34C7_11571.jpg');
INSERT INTO `news_image` VALUES ('16', 'D:\\lab-app\\upload\\1599706764479_C4FC185C93D322F5EB648814185_FDEDC1F8_132C9.jpg');
INSERT INTO `news_image` VALUES ('17', 'D:\\lab-app\\upload\\1599706774516_CDEFBCBBACA834AECC9CCD18E61_D90C5E4F_1901A.jpg');
INSERT INTO `news_image` VALUES ('18', 'D:\\lab-app\\upload\\1599706780681_D929DB7CE015E59DBF4FCD6B912_F54FA4AE_1E5C5.jpg');

#notice table 通知公告表
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `information_source` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `time` date NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO `notice` VALUES ('1', '最新化学设备借还制度', '教务处', '住着', '2020-09-07', '<p><span>一、任课教师因教学需要的仪器、材料等物品，由教师本人到仪器室办理借用手续，在借用仪器时要仔细检查和了解仪器的完好程度使用完毕，由教师本人将所借用的物品送还仪器室，由实验员检查验收后，注销借用手续。</span></p><p><span>二、仪器室要有仪器借还登记册，内容包括借还的时间，仪器数量、仪器完好情况，借用时领取人要签字，归还时实验员要登记。</span></p><p><span>三、校内有关部门或人员因非教学需要借用仪器，应由教务（导）主任批准签字后，才能办理借用手续。</span></p><p><span>四、非教育单位不得借用。兄弟学校之间因教学急需借用仪器时要凭单位介绍信并经主管校长批准签字后才能办理借用手续。</span></p><p><span>五、学生因课外科技活动需要借用教学仪器时，收辅导教师代为借用并负责按时归还。</span></p><p><span>六、教学仪器外借后应及时归还，每学期末必须将仪器全部交回实验室，对到期不还者，由批准人员负责追回。</span></p><p><span>七、归还的仪器如有损坏现象，借用人要说明原因，能够修理的待修好后再办理交还手续，不能修理的，待查明原因，按有关规定处理后再办交还手续并注明处理意见。</span></p>');
INSERT INTO `notice` VALUES ('2', '关于做好中秋节假前实验室安全检查工作的通知', '教务处', '李达康', '2020-09-23', '<p style=\"text-align: justify;\"><span>一、高度重视实验室安全工作，各二级学院确保实验室、实训车间安全责任落实到人，防范措施落实到位。</span></p><p style=\"text-align: justify;\"><span>二、放假之前，请各学院组织有关人员对所属实验室和实训车间进行一次全面的安全检查。认真检查消防设施器材，重点检查实验室水、电、气、门窗及压力容器的安全，防火防盗设施是否正常，以及线路是否存在安全隐患。在检查中如发现安全隐患，应立即上报北校区保卫处、后勤处和实验室管理中心。</span></p><p style=\"text-align: justify;\"><span>三、将各类仪器设备、材料、工具分类放好，远离热源、火源、和可燃物料，不可阻挡消防设备。危险化学品、易燃易爆和放射性物品等危险品一律锁入相应橱柜中，分类摆放，仔细核对领用记录和留存量，严防丢失和流出。</span></p><p style=\"text-align: justify;\"><span>四、假期如需使用实验室，或者电源设备需保持正常运转的，各学院应向实验管理中心提出申请和报备，按照“谁使用谁负责”的原则，切实做好实验室安全管理和防范工作。有学生做实验的实验室，必须有指导教师在场。对于需连续通电运行的设备及装置，须要有专人守护，不得擅离职守，实验结束后，及时关闭水、电、气及门窗。检查后确定无安全隐患的，做好断水、断电，门窗关闭工作。</span></p><p style=\"text-align: justify;\">五、<span>请各学院按照相关规章制度要求进行自查，以确保我校假期期间实验室安全。</span></p><div><span><br></span></div>');
INSERT INTO `notice` VALUES ('3', '关于进一步加强我校实验室安全管理的通知', '教务处', '李达康', '2020-09-08', '<p>一、强化责任，迅速开展实验室安全检查相关学院或部门领导要按“一岗双责”的要求，迅速部署开展本单位实验室安全检查工作，根据近期各项工作要求，认真制定检查方案，迅速开展检查整改，注重实效，坚决防止走过场。</p><p>二、规范制度，切实加强实验室安全管理相关学院或部门要加大对各级各类实验室危险化学品的管控力度，对涉及实验室危险化学品管理的重点部位和薄弱环节进行重点排查，确保危险化学品管理做到“四无一保”，即无被盗、无事故、无丢失、无违章、保安全。加强技防监控设施建设，以更严密的安全保卫措施，确保危险化学品的管理和使用总体受控。</p><p>三、加强教育，提升安全意识和应急能力相关学院或部门要从安全工作的实际出发，有针对性的加强实验室安全教育，切实把安全教育纳入到学校的教学科研内容之中，广泛利用宣传栏、微信公众号等媒介，开展形式多样的安全教育，不断提高广大师生的安全意识和防范自救能力，共同维护校园的安全稳定。</p><p><br></p>');
INSERT INTO `notice` VALUES ('4', '关于进-一步加强实验室管理的通知', '教务处', '李达康', '2020-09-09', '<p>1、中心实验室大门开放时间为每天7:00 至22:30， 每晚22: 30准时打铃，同时关闭实验室大门.</p><p>2、中心实验室申请使用(含:教学实习、参观学习、科研试验等)须填写《实验室开放预约审批表》，在履行审批手续后，到中心办公室登记后使用，其中:科研试验以设备为单位，每个课题组、每学期审批一次即可;</p><p>3、中心仪器设备预约使用时间为工作日周一至周五，上午8: 30-12:00，下午14: 00-17:30，申请人须持有效证件、仪器设备网上预约证明，到中心办公室登记后使用;</p><p>4、中心仪器设备在国家法定节假日、周末以及工作日其他时间段原则.上不对外开放，若确有紧急使用需求，申请人须提前填写《节假日仪器设备申请使用审批表》，在履行审批手续、做好安全承诺之后，届时到中心值班室登记使用;</p><p><br></p>');
INSERT INTO `notice` VALUES ('5', '关于加强实验室危险废弃液管理的通知', '教务处', '李达康', '2020-09-20', '<p><span>为了贯彻执行山东省人民政府办公厅印发的《山东省危险废物专项排查整治方案》（鲁政办字</span><span lang=\"EN-US\">[2019]58</span><span>号）文件精神，根据山东省教育厅印发《关于开展教育系统实验室危险废物专项检查和整改工作的通知》要求，按照《山东农业工程学院实验室危险化学品废弃物回收处置细则》（见附件）规定，请各学院近日安排人员到实验室管理中心实验科（北校区实验楼</span><span lang=\"EN-US\">106</span><span>室）领取危险化学品废液桶，现将有关事项通知如下：</span></p><p><span>一、指定专人具体负责</span></p><p><span>危险化学品废弃物产生单位（二级学院）应指定专人具体负责废弃物的分类收集及暂存的安全管理工作，并配合实验管理中心开展回收处置工作。</span></p><p><span>二、危险化学品废弃物分类</span></p><p><span>危险化学品废弃物是指被列入《国家危险废物名录》的化学废弃物，包括具有各种毒性、腐蚀性、易燃性、易爆性和化学反应性的化学废弃物。</span></p><p><span>三、危险化学品废弃物收集</span></p><p><span>各实验室应将产生的各类危险化学品废弃物暂时分类收集并合理存放，定期交危化品废弃物暂存仓库暂存。实验管理中心将定期联系具有资质的公司进行统一处置。</span></p>');

#project_consume table 项目耗材表
DROP TABLE IF EXISTS `project_consume`;
CREATE TABLE `project_consume` (
  `id` int NOT NULL AUTO_INCREMENT,
  `experiment_consume_id` int DEFAULT NULL,
  `arrange_project_id` int DEFAULT NULL,
  `consume_num` double(11,0) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '0正常数据，1已删除数据',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
INSERT INTO `project_consume` VALUES ('1', '15', '1', '3', '0');
INSERT INTO `project_consume` VALUES ('2', '3', '1', '1', '0');
INSERT INTO `project_consume` VALUES ('3', '8', '1', '1', '0');
INSERT INTO `project_consume` VALUES ('4', '2', '2', '2', '0');
INSERT INTO `project_consume` VALUES ('5', '8', '2', '2', '0');
INSERT INTO `project_consume` VALUES ('6', '7', '2', '1', '0');
INSERT INTO `project_consume` VALUES ('7', '14', '3', '2', '0');
INSERT INTO `project_consume` VALUES ('8', '7', '3', '1', '0');
INSERT INTO `project_consume` VALUES ('9', '15', '4', '3', '0');
INSERT INTO `project_consume` VALUES ('10', '8', '4', '1', '0');
INSERT INTO `project_consume` VALUES ('11', '14', '5', '1', '0');
INSERT INTO `project_consume` VALUES ('12', '14', '6', '1', '0');

#project_device table 项目设备表
DROP TABLE IF EXISTS `project_device`;
CREATE TABLE `project_device` (
  `id` int NOT NULL AUTO_INCREMENT,
  `experiment_device_id` int DEFAULT NULL,
  `arrange_project_id` int DEFAULT NULL,
  `device_num` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '0正常数据，1已删除数据',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `project_device` VALUES ('1', '7', '1', '1', '0');
INSERT INTO `project_device` VALUES ('2', '4', '2', '1', '0');
INSERT INTO `project_device` VALUES ('3', '7', '2', '1', '0');
INSERT INTO `project_device` VALUES ('4', '4', '3', '1', '0');
INSERT INTO `project_device` VALUES ('5', '7', '3', '1', '0');
INSERT INTO `project_device` VALUES ('6', '8', '4', '1', '0');
INSERT INTO `project_device` VALUES ('7', '3', '5', '1', '0');

#regime table 实验制度表
DROP TABLE IF EXISTS `regime`;
CREATE TABLE `regime` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `information_source` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `time` date NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO `regime` VALUES ('1', '学生实验守则', '教务处', '张帆', '2020-09-15', '<p><span style=\"text-align: justify;\">一、实验室必须保持安静、整洁。进入实验室后应按指定位置就坐，不得大声喧哗及自行摆弄仪器装置。</span></p><p><span style=\"text-align: justify;\">二、实验前，要弄清楚实验目的、要求、步骤及</span><a href=\"https://www.xuexila.com/yangsheng/zhuyishixiang/\" target=\"_blank\" style=\"text-align: justify;\"><u>注意事项</u></a><span style=\"text-align: justify;\">。</span></p><p><span style=\"text-align: justify;\">三、实验前应检查实验所需的仪器、药品、器材是否齐全、完好，如有缺漏、损坏应报告老师。</span></p><p><span style=\"text-align: justify;\">四、共用仪器用后立即放回原处;各组仪器未经老师许可，不得随意移动。</span></p><p><span style=\"text-align: justify;\">五、实验时必须持严谨的科学态度，严格按照实验步骤进行操作。要仔细观察现象，如实做好记录，积极思考，分析实验结果，按规定写好实验报告。实验中碰到疑难问题，应向指导老师请教;如发生意外，要及时报告。</span></p><p><span style=\"text-align: justify;\">六、爱护仪器设备，爱惜药品、材料。在实验中损坏仪器、器具应主动向老师报告。凡不按操作规程而损坏的仪器均应赔偿;对故意损坏仪器的，除照价赔偿外，还要视情节轻重，给予必要的处分。</span></p><p><span style=\"text-align: justify;\">七、废液、废纸、火柴梗及玻璃片等物应倒入废液缸或垃圾箱中，不得随地乱抛或倒入水槽中。</span></p><p><span style=\"text-align: justify;\">八、实验完毕，应整理仪器装置，清洗器皿，搞好卫生，关闭电源、水源，经老师检查同意后，才能离开实验室。</span></p>');
INSERT INTO `regime` VALUES ('2', '实验室管理守则', '教务处', '范范', '2020-09-15', '<p><span style=\"text-align: justify;\">一、仪器要统一要求分类、编号、入账。要建立总帐、分类帐、低值易耗帐。仪器存放要定橱定位，做到帐、物、卡相符。</span></p><p><span style=\"text-align: justify;\">二、调拨仪器或自购仪器、器材、药品，必须在半个月内开箱验收，并及时入帐入橱，如发现有质量问题应及时和有关单位联系。</span></p><p><span style=\"text-align: justify;\">三、仪器要存放在干燥、通风、安全的室内，做好防火、防盗、防尘、防蛀、防霉以及避光、避磁等工作。损坏的仪器应及时</span><a href=\"https://www.xuexila.com/shenghuo/xiuli/\" target=\"_blank\" style=\"text-align: justify;\"><u>修理</u></a><span style=\"text-align: justify;\">，使仪器设备处于完好状态。</span></p><p><span style=\"text-align: justify;\">四、</span><a href=\"https://www.xuexila.com/xuexifangfa/huaxue/\" target=\"_blank\" style=\"text-align: justify;\"><u>化学</u></a><span style=\"text-align: justify;\">药品要单独存放在专用的安全柜中。</span></p><p><span style=\"text-align: justify;\">五、实验人员如有变动，应办理移交手续。</span></p><p><span style=\"text-align: justify;\">六、仪器的借还赔偿，请自觉遵守《借还赔偿制度》。</span></p>');
INSERT INTO `regime` VALUES ('3', '实验室工作人员职责', '教务处', '张小帆', '2020-09-21', '<p><span style=\"text-align: justify;\">一、管理人员应树立为</span><a href=\"https://www.xuexila.com/news/jiaoyu/\" target=\"_blank\" style=\"text-align: justify;\"><u>教育</u></a><span style=\"text-align: justify;\">服务的思想，热爱本职工作，努力学习、钻研业务，熟悉本</span><a href=\"https://www.xuexila.com/xuexifangfa/xueke/\" target=\"_blank\" style=\"text-align: justify;\"><u>学科</u></a><span style=\"text-align: justify;\">教材，熟悉各类仪器的性能和保管知识，掌握各种仪器的</span><a href=\"https://www.xuexila.com/fangfa/shiyong/\" target=\"_blank\" style=\"text-align: justify;\"><u>使用方法</u></a><span style=\"text-align: justify;\">和维修技能。</span></p><p><span style=\"text-align: justify;\">二、加强对仪器室和实验室的全部物资的管理工作，各类仪器、器材及药品要帐、卡、物相符。努力做到：常规管理制度化，仪器存放规范化，仪器保管科学化，各项记录经常化，档案资料系列化。</span></p><p><span style=\"text-align: justify;\">三、新到仪器设备、器材，药品应在半月内验收，登帐入橱。发现仪器质量有问题或数量有差错应及时汇报处理。</span></p><p><span style=\"text-align: justify;\">四、准备好实验室所需的仪器、器材、药品，协助任课老师指导学生。实验完毕清点仪器，检查仪器的完好情况，发现问题，应查明原因，按有关规定认真处理。</span></p><p><span style=\"text-align: justify;\">五、坚持勤俭办学原则，积极自制教具，充实教学、实验设备，并做好仪器设备的维修保养工作。</span></p><p><span style=\"text-align: justify;\">六、创造条件，开放实验室，协助任课老师组织学生开展课外</span><a href=\"https://www.xuexila.com/aihao/\" target=\"_blank\" style=\"text-align: justify;\"><u>兴趣</u></a><span style=\"text-align: justify;\">小组活动。</span></p><p><span style=\"text-align: justify;\">七、做好仪器室和实验室的安全防范工作，掌握发生事故的应急处理方法。经常保持室内外整洁，使仪器设备处于完好待用状态。</span></p><p><span style=\"text-align: justify;\">八、每学期结束时，应清点一次仪器、药品和实验材料，根据教学需要，编制仪器设备购置计划，向学校领导和上级有关部门书面汇报工作，总结</span><a href=\"https://www.xuexila.com/fanwen/jingyan/\" target=\"_blank\" style=\"text-align: justify;\"><u>经验</u></a><span style=\"text-align: justify;\">教训，改进管理方法，努力提高实验室工作水平和工作效率。</span></p>');
INSERT INTO `regime` VALUES ('4', '借还赔偿制度', '教务处', '张帆帆', '2020-09-28', '<p><span style=\"text-align: justify;\">一、一切仪器的领用、借出和归还均需办理手续，填写实验通知单或借用报告单，并严查仪器的完好情况。</span></p><p><span style=\"text-align: justify;\">二、教职工借用非教学用的器材，需经学校教导主任批准后才能借出，时间一般不超过一周。不论何种仪器，任何人不得以任何理由长期占用。</span></p><p><span style=\"text-align: justify;\">三、为了保证教学需要，教学仪器一般不予外借。外单位借用，必须经校长室批准，并办理手续，时间不超过一周。</span></p><p><span style=\"text-align: justify;\">四、凡确认不能修理的仪器，经长期使用后材料老化或精度、效果达不到实验要求的仪器及正常损耗的器材，要按照规定办理有关手续，报学校领导批准后核销。</span></p><p><span style=\"text-align: justify;\">五、教师在教学过程中属正常损坏的仪器，应及时填写损坏报告单;凡属操作错误而损坏的，原则上当事人赔偿。</span></p><p><span style=\"text-align: justify;\">六、教职工借用的仪器、器材因保管不善或使用不当而遗失或损坏的，要照价赔偿。</span></p><p><span style=\"text-align: justify;\">七、学生实验中因操作不慎而损坏的仪器应酌情赔偿;由于嬉弄而损坏的仪器除照价赔偿外，还要根据情节轻重，给予教育处理。</span></p><p><span style=\"text-align: justify;\">八、对保管或采购不当，造成仪器设备损坏、丢失、积压的当事人，要依情节轻重给予适当的行政处分和经济赔偿。</span></p>');
INSERT INTO `regime` VALUES ('5', '仪器室维修保养制度', '教务处', '张小小', '2020-09-08', '<p><span style=\"text-align: justify;\">一、管理人员应熟悉仪器室各类仪器的性能和保管知识，掌握各类仪器的使用方法和维修技能。</span></p><p><span style=\"text-align: justify;\">二、教育学生爱护仪器设备。学生实验完毕，及时清点，检查仪器的完好情况，发现问题应查明原因，按规定认真处理。</span></p><p><span style=\"text-align: justify;\">三、管理人员要定时对实验仪器进行维护保养，保证仪器设备处于完好待用状态，对维护保养工作应作还记录。</span></p><p><span style=\"text-align: justify;\">四、对部分不能及时维修的仪器应分开存放，报请总务部门处理或请专人进行维修。</span></p><p><span style=\"text-align: justify;\">五、对确认不能维修的仪器，应及时汇报校长室，经同意后进行核销、报废。</span></p><p><span style=\"text-align: justify;\">六、管理人员应坚持勤俭办学的原则，对已核销、报废的仪器进行适当处理;如有可能，应作为自制教具材料，达到再利用的目的。</span></p>');

#report_repair table 保修表
DROP TABLE IF EXISTS `report_repair`;
CREATE TABLE `report_repair` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_id` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `report_repair` VALUES ('1', '1', '使用异常', '2020-09-15', '4');

#resource_doc table doc资源表
DROP TABLE IF EXISTS `resource_doc`;
CREATE TABLE `resource_doc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `resource_doc` VALUES ('1', 'D:\\lab-app\\upload\\resourcedoc\\董铠 前端工程师 .docx', '董铠 前端工程师 .docx');
INSERT INTO `resource_doc` VALUES ('2', 'D:\\lab-app\\upload\\resourcedoc\\董铠 前端工程师 .docx', '董铠 前端工程师 .docx');
INSERT INTO `resource_doc` VALUES ('3', 'D:\\lab-app\\upload\\resourcedoc\\董铠 前端工程师 .docx', '董铠 前端工程师 .docx');
INSERT INTO `resource_doc` VALUES ('4', 'D:\\lab-app\\upload\\resourcedoc\\人才管理.docx', '人才管理.docx');

#resource_pdf table pdf资源表
DROP TABLE IF EXISTS `resource_pdf`;
CREATE TABLE `resource_pdf` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `resource_pdf` VALUES ('1', 'D:\\lab-app\\upload\\resourcedoc\\人才管理.pdf', '人才管理.pdf');

#scrap table 报废表
DROP TABLE IF EXISTS `scrap`;
CREATE TABLE `scrap` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_id` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO `scrap` VALUES ('5', '45', '坏了', '2020-09-22', '4');

#student_score table 学生成绩表
DROP TABLE IF EXISTS `student_score`;
CREATE TABLE `student_score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_id` int DEFAULT NULL,
  `teacher_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `student_score` VALUES ('1', '6', '2', '3', '89');

#study table 教学科研
DROP TABLE IF EXISTS `study`;
CREATE TABLE `study` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `information_source` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `time` date NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO `study` VALUES ('1', '提升原始创新能力，构筑基础科研高地', '教务处', '擎天柱', '2020-09-15', '<p>未来中国的科学研究更加强调原始性、前瞻性、引领性创新，注重新思想、新方法、新原理、新知识的源头储备。高校应充分发挥自身学科、科技、人才、信息等方面的综合优势，通过科学的顶层设计与资源配置，推动各类创新要素的深度融合，使高校成为知识创新和技术创新的策源地。</p><p>一要大力建设高水平的基础科研平台基地。积极推进国家实验室、国家重点实验室、学科交叉国家研究中心等高水平平台基地的培育与建设。充分发挥高水平平台的集聚效应，依托平台汇聚优质创新资源，承担重大科研任务，产生有影响力的原创成果。主动对接国家基础研究布局，在国家重大科技基础设施、国家科技创新中心等建设中发挥更大作用。</p><p>二要大力培育高水平的基础科研人才团队。改革完善人才育、引、留机制，依托高层次人才引进和培养计划，培养汇聚一批具有国际影响力的科技创新高端人才，组建若干具有重要国际影响的协同创新团队。建立对创新团队进行稳定支持的倾斜政策，鼓励潜心开展持续的基础科学研究与探索。进一步加强人才梯队建设，大力培养中青年和后备科技人才，保证研究投入与成果产生的连续性。</p><p><img src=\"http://192.168.0.117:8080/lab/studyimage/8\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/9\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/10\" alt=\"\"><br></p>');
INSERT INTO `study` VALUES ('2', '深化体制机制改革，创优基础科研环境', '教务处', '威震天', '2020-09-16', '<p>高校有独特的制度环境与校园文化，这是开展科学研究、产出创新成果的特殊土壤。在不断加强基础科研工作的过程中，高校应积极建设与高水平基础科研工作相适应的制度体系，努力营造浓厚的学术氛围和保护创新、宽容失败的创新环境，充分激发科研人员的创新精神和创新潜力。</p><p>一是推进科研组织模式创新。积极构建人才、团队、平台、项目、成果五位一体的科技创新保障体系，推动形成资源共享、学科交叉、人才流动、技术合作、政策激励的创新环境。大力促进高校与企业、创客等各类创新主体协作融通，促进源头创新、技术研发、产业化应用的紧密结合。</p><p>二是深化科研项目和经费管理改革。尊重科学研究规律，立足高校实情，进一步完善科研项目组织，把握项目申报、评审与决策等环节，做好有组织科研。简化科研项目和预算审批流程，落实科研人员的经费使用自主权，切实做到“让经费为人的创造性活动服务”。</p><p>三是完善科技分类评价机制改革。结合基础科学研究特点和规律，兼顾好质量和数量、绩效和潜能以及不同学科的特点，均衡考量创新质量和学术贡献，设定科学的基础研究成果分类标准、评价指标和考核标准，并将分类评价结果与考核评聘、晋升晋级、资源配置挂钩。在科学设计评价标准和评价程序的同时，积极营造公开公平公正的评价环境，为基础研究与原创研究创新者提供应有的荣誉和回报。</p><p><img src=\"http://192.168.0.117:8080/lab/studyimage/11\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/12\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/13\" alt=\"\"><br></p>');
INSERT INTO `study` VALUES ('3', '立足根本任务，提升人才培养质量', '教务处', '张凡凡', '2020-09-30', '<p>创新实力的竞争，归根到底还是人才竞争。高校作为人才第一资源和科学技术第一生产力的结合点，应当围绕立德树人根本任务，充分发挥各类创新要素的育人功能，培养造就能够发挥创新能力、解决复杂问题、应对全球性挑战的新型人才，为国家提供一流的基础科研工作者与后备人才队伍。</p><p>一要推进科教融合。注重把科学研究的最新成果贯穿于教学的各个环节，构建与学科前沿理论和行业先进技术接轨的教学内容体系。持续探索科研、学科、人才相融合的实验平台建设机制和模式，将丰富的科学研究资源转化为人才培养优势。</p><p>二要深化产学研协同创新。深化拔尖创新人才培养模式改革，充分发挥高校的学科优势和企业的实践优势，围绕前沿科学问题与重大工程需求进行科研与工程实践。将企业生产研发全过程、工程项目生命全周期等作为学生学习环境，从而实现专业教育与行业基础科研需求的有效对接。</p><p>三要突出创新精神创新能力培养。加强思维引导与启迪，鼓励学生深入思考科学问题并提出解决方案，鼓励学生特别是研究生在国家重大科研与重大工程项目中凝练研究方向，不断提高自身创新能力。将创新创业教育进一步融入人才培养体系，贯穿人才培养全过程。在校园中大力弘扬科学精神与创新文化，扶持和鼓励学生成立学术创新类社团，提高创新创业类竞赛的参与度和影响力，不断激发学生科研创新的热情。</p><p>总之，加强基础科学研究，是高校的机遇、责任与使命。我们要做好顶层设计，瞄准国家重大需求和学科前沿做好部署，统筹好基础科学研究、平台建设、人才培养，持之以恒，久久为功，产出更多的原创性成果和创新型人才。</p><p><img src=\"http://192.168.0.117:8080/lab/studyimage/14\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/15\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/16\" alt=\"\"><br></p>');
INSERT INTO `study` VALUES ('4', '科学引深思 教研促成长——科学组教研活动报道', '教务处', '赵佳佳', '2020-09-15', '<p>结合《中国学生核心素养发展框架》和学科素养发展重点，针对当前科学课堂教学的问题，进一步开展有效科学教学的研究，不断提高科学教学活动的质量，促进学生的全面发展。近日，江北中心小学科学教研组开展了以“深度探究 快乐分享”为主题的教学研讨活动。</p><p align=\"left\">组内教师各自学习相关理论并分享，探讨了各年级段落实重点及在日常科学教学中落实策略。同时陈超颖老师执教了六年级下册《物质发生了什么变化》一课。经过她的精心谋划和具有特色的活动开展，使六年级学生感受到了科学学习的魅力，如何区分物理变化和化学变化，陈老师和学生们在课堂里交流互动，“点拨”教学有张有弛，巧妙的观察实验安排，利用智慧课堂软件有效地开展科学探究活动，都使得学生们学习主体性得到充分体现。课后，组内老师对这一教研课进行了互动点评，探讨教学活动中的不足之处，总结有效的教学方式方法，充分发挥了教研的真正作用。</p><p>本次教研活动，立足教育教学实践的研讨，不仅为教师提供了学习、研讨和交流的平台，使自己在科学领域的教学能力得到了锻炼，也强化了科学组良好的教研风气，进一步提高了教师组织指导科学活动的能力，有效地促进了教师的专业成长。</p><p><img src=\"http://192.168.0.117:8080/lab/studyimage/17\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/18\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/19\" alt=\"\"><br></p>');
INSERT INTO `study` VALUES ('5', '科学研讨，共同成长——科学教研组活动报道', '教务处', '擎天柱', '2020-09-23', '<p><span>为切实提升科学教师的课堂教学水平和专业素养，进一步增强校际间的互动协作，实现科学教学资源的共创共享，2019年10月18日下午，塔山镇四年级科学教研组在闫庄小学开展了本学期第二次科学教研活动，区教研员刘辉主任，教育中心校赵浩校长、彭永主任参加了此次活动。</span></p><p><span>此次教研活动由刘伟老师主持，活动开始，首先由刘伟老师向大家介绍本次研讨的内容，单元简析，各课的知识点，重点装置和实验观察，强调科学课一定要学生动手做实验，在实验中获得知识。接着刘老师结合四年级教材第二单元第四课《不同物质在水中的溶解能力》，从教师如何备课，如何准备好实验材料，以及课堂上如何让学生通过读书了解实验步骤，动手实验并做好实验记录四个方面谈了自己的教学经验，并重点进行模拟课堂。之后，老师们进行积极的研讨交流，提出自己的见解和疑惑。许晴老师结合自己的课堂教学经历提出了不同见解，并上台演示。老师们的精彩发言，将教研活动推向高潮。研讨交流后，紧接着进行抽签二次模拟，定教案定课件。</span></p><p><span>最后，刘辉主任进行点评，肯定了刘伟老师的做法，指出科学教学要发挥集体的力量，大家要针对教材中出现的难点、疑点进行商讨，再结合自己的实际情况，在研讨的基础上进行二次备课，提高集体备课的质量。&nbsp;&nbsp;</span></p><p><span><img src=\"http://192.168.0.117:8080/lab/studyimage/20\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/21\" alt=\"\"><img src=\"http://192.168.0.117:8080/lab/studyimage/22\" alt=\"\"><br></span></p>');

#study_image table 教学科研图片表
DROP TABLE IF EXISTS `study_image`;
CREATE TABLE `study_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
INSERT INTO `study_image` VALUES ('8', 'D:\\lab-app\\upload\\1599709761238_E750049DEB88A98E04736A69BBC_D8E8F06C_134EF.jpg');
INSERT INTO `study_image` VALUES ('9', 'D:\\lab-app\\upload\\1599709766445_E6A1AF00C976BE5463A52C3D812_8618FD5D_E026.jpg');
INSERT INTO `study_image` VALUES ('10', 'D:\\lab-app\\upload\\1599709772598_D929DB7CE015E59DBF4FCD6B912_F54FA4AE_1E5C5.jpg');
INSERT INTO `study_image` VALUES ('11', 'D:\\lab-app\\upload\\1599709858362_CDEFBCBBACA834AECC9CCD18E61_D90C5E4F_1901A.jpg');
INSERT INTO `study_image` VALUES ('12', 'D:\\lab-app\\upload\\1599709865651_C4FC185C93D322F5EB648814185_FDEDC1F8_132C9.jpg');
INSERT INTO `study_image` VALUES ('13', 'D:\\lab-app\\upload\\1599709871883_B4B27FC198E0E768D92B7D92CA4_035D34C7_11571.jpg');
INSERT INTO `study_image` VALUES ('14', 'D:\\lab-app\\upload\\1599710133999_ABCFEE7901514564F73DFFF8F6C_6233CD72_1FDC0.jpg');
INSERT INTO `study_image` VALUES ('15', 'D:\\lab-app\\upload\\1599710139359_A944BAE7340545F85A8A5463B78_14BAB9F8_A8ED.jpg');
INSERT INTO `study_image` VALUES ('16', 'D:\\lab-app\\upload\\1599710145580_98893BD260F6F86A02D11EDED96_2FD23168_2B057.jpg');
INSERT INTO `study_image` VALUES ('17', 'D:\\lab-app\\upload\\1599710215094_98893BD260F6F86A02D11EDED96_2FD23168_2B057.jpg');
INSERT INTO `study_image` VALUES ('18', 'D:\\lab-app\\upload\\1599710219329_800DDE90E283D87E33C1C0E235F_6A62353E_14B57.jpg');
INSERT INTO `study_image` VALUES ('19', 'D:\\lab-app\\upload\\1599710223479_561D1A025D1C8E5AAEFF9020D69_F9DEA9F4_20566.jpg');
INSERT INTO `study_image` VALUES ('20', 'D:\\lab-app\\upload\\1599710516962_7C7040DD8053585987437C2279A_893AECC7_F379.jpg');
INSERT INTO `study_image` VALUES ('21', 'D:\\lab-app\\upload\\1599710523451_4DE7CA5040A37E358031441EC48_A6DA3662_2F523.jpg');
INSERT INTO `study_image` VALUES ('22', 'D:\\lab-app\\upload\\1599710529344_9DCE2514B463057CFEFFA0C6B45_311C5C1E_19B0B.jpg');

#t_user_basic table 用户基本资料表
DROP TABLE IF EXISTS `t_user_basic`;
CREATE TABLE `t_user_basic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL COMMENT '1男 2女',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机',
  `e_mail` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `t_user_basic` VALUES ('1', '1', '1', '18335592986', '18335592986@139.com', '');














































