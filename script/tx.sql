
CREATE DATABASE IF NOT EXISTS test_db DEFAULT CHARACTER SET = utf8mb4;

Use test_db;
-- ----------------------------
-- Table structure for tx_classmate
-- ----------------------------
DROP TABLE IF EXISTS tx_classmate;

create table if not exists tx_classmate(
    id integer auto_increment primary key not null ,
    link VARCHAR(32) comment '课程链接',
    price decimal(8,2) comment '价格',
    users  varchar(32) comment '报名人数或者购买人数',
    person_number int(11) comment '人数',
    agency varchar(32) comment '课程机构',
    title varchar(255) comment '标题',
    c_seed_id int(11)  comment '二级节点种子id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tx_meta_class
-- ----------------------------
DROP TABLE IF EXISTS tx_meta_class;

create table if not exists tx_meta_class(

    id integer auto_increment primary key  not null ,
    p_description varchar(32) not null comment '一级分类描述',
    c_description varchar(32) not null comment '二级分类描述',
    p_seed_id int(11) not null comment '父节点种子id',
    c_seed_id int(11) not null comment '子节点种子id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tx_meta_class add  unique  index pc(p_seed_id,c_seed_id);


INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (1, 'IT·互联网', '互联网产品', 1001, 2001);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (2, 'IT·互联网', '编程语言', 1001, 2002);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (3, 'IT·互联网', '前端开发', 1001, 2004);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (4, 'IT·互联网', '移动开发', 1001, 2003);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (5, 'IT·互联网', '网络与运维', 1001, 2005);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (6, 'IT·互联网', '游戏开发', 1001, 2008);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (7, 'IT·互联网', '软件测试', 1001, 2006);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (8, 'IT·互联网', '云计算大数据', 1001, 2007);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (9, 'IT·互联网', '硬件开发', 1001, 2043);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (10, 'IT·互联网', '认证考试', 1001, 2009);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (11, '设计·创作', '平面设计', 1002, 2011);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (12, '设计·创作', '设计软件', 1002, 2013);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (13, '设计·创作', '环境艺术设计', 1002, 2017);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (14, '设计·创作', '绘画创作', 1002, 2015);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (15, '设计·创作', '工业产品设计', 1002, 2018);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (16, '设计·创作', 'UI设计', 1002, 2012);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (17, '设计·创作', '游戏美术设计', 1002, 2014);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (18, '设计·创作', '影视设计', 1002, 2016);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (19, '设计·创作', '服装设计', 1002, 2019);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (20, '设计·创作', '其他', 1002, 2041);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (21, '电商·营销 ', '电商平台 ', 1007, 2010);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (22, '电商·营销 ', '跨境电商 ', 1007, 2050);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (23, '电商·营销 ', '社交电商', 1007, 2051);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (24, '电商·营销 ', '其他', 1007, 2052);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (25, '职业·考证', '公考求职 ', 1004, 2027);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (26, '职业·考证', '法学院', 1004, 2046);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (27, '职业·考证', '财会金融 ', 1004, 2028);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (28, '职业·考证', '医疗卫生', 1004, 2039);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (29, '职业·考证', '建筑工程', 1004, 2029);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (30, '职业·考证', '职业技能', 1004, 2044);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (31, '职业·考证', '农业生产 ', 1004, 2053);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (32, '升学·考研', '考研', 1005, 2031);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (33, '升学·考研', '大学', 1005, 2042);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (34, '升学·考研', '高中', 1005, 2032);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (35, '升学·考研', '初中', 1005, 2033);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (36, '升学·考研', '小学', 1005, 2034);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (37, '兴趣·生活 ', '母婴亲子', 1006, 2038);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (38, '兴趣·生活 ', '音乐乐器', 1006, 2047);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (39, '兴趣·生活 ', '文艺修养', 1006, 2037);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (40, '兴趣·生活 ', '生活百科', 1006, 2036);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (41, '兴趣·生活 ', '运动健康', 1006, 2049);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (42, '兴趣·生活 ', '投资理财', 1006, 2035);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (43, '语言·留学', '实用英语', 1003, 2020);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (44, '语言·留学', '出国留学', 1003, 2021);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (45, '语言·留学', '英语四六级', 1003, 2022);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (46, '语言·留学', '日语', 1003, 2023);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (47, '语言·留学', '韩语', 1003, 2024);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (48, '语言·留学', '小语种', 1003, 2025);
INSERT INTO tx_meta_class (id, p_description, c_description, p_seed_id, c_seed_id) VALUES (49, '腾讯课堂', '课堂', -1, -1);