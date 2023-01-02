#创建数据库
CREATE DATABASE IF NOT EXISTS `voting`;
USE voting;

#创建用户表
create table users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);
create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

#创建投票数据表
CREATE TABLE `topic`
(
    `tid`      int          NOT NULL AUTO_INCREMENT COMMENT '题目ID',
    `username` varchar(50)  NULL COMMENT '用户名',
    `title`    varchar(255) NOT NULL COMMENT '题目标题',
    `time`     timestamp    NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
    `enable`   boolean      NULL COMMENT '结算状态',
    PRIMARY KEY (`tid`)
);
CREATE TABLE `choice`
(
    `cid`     int           NOT NULL AUTO_INCREMENT COMMENT '选项ID',
    `tid`     int           NOT NULL COMMENT '题目ID',
    `content` varchar(255)  NOT NULL COMMENT '选项内容',
    `picture` varchar(1020) NULL COMMENT '图片URL',
    `total`   int           NULL COMMENT '选项统计',
    PRIMARY KEY (`cid`)
);
CREATE TABLE `record`
(
    `rid` int NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `cid` int NOT NULL COMMENT '选项ID',
    `vid` int NOT NULL COMMENT '游客ID',
    PRIMARY KEY (`rid`)
);
CREATE TABLE `visitor`
(
    `vid`  int          NOT NULL AUTO_INCREMENT COMMENT '游客ID',
    `ip`   varchar(255) NULL COMMENT 'IP地址',
    `time` timestamp    NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登入时间',
    PRIMARY KEY (`vid`)
);