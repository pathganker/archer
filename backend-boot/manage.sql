/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : 127.0.0.1:3306
Source Database       : manage

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-03-14 01:06:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qfeng_article
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_article`;
CREATE TABLE `qfeng_article` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `edition` varchar(50) DEFAULT NULL COMMENT '文集',
  `user` varchar(50) DEFAULT NULL COMMENT '关联用户',
  `front_content` longtext COMMENT '展示内容',
  `backend_content` longtext COMMENT '编辑内容',
  `image` varchar(500) DEFAULT NULL COMMENT '封面图片',
  `profile` varchar(200) DEFAULT NULL COMMENT '概览',
  `is_publish` tinyint(1) DEFAULT '0' COMMENT '发布状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `Index_user` (`user`),
  KEY `Index_edition` (`edition`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qfeng_comment
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_comment`;
CREATE TABLE `qfeng_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` varchar(50) NOT NULL COMMENT '业务主键',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `article_id` varchar(50) DEFAULT NULL COMMENT '关联文章',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`,`uid`),
  UNIQUE KEY `Index_uid` (`uid`),
  KEY `Index_article` (`article_id`),
  KEY `Index_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qfeng_edition
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_edition`;
CREATE TABLE `qfeng_edition` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `Index_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qfeng_like
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_like`;
CREATE TABLE `qfeng_like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `article_id` varchar(50) DEFAULT NULL COMMENT '文章id',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_user` (`user_id`),
  KEY `Index_article` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qfeng_ref_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_ref_article_tag`;
CREATE TABLE `qfeng_ref_article_tag` (
  `id` bigint(20) NOT NULL COMMENT '自增主键',
  `tag_id` varchar(50) DEFAULT NULL COMMENT 'tagID',
  `tag_name` varchar(50) DEFAULT NULL COMMENT 'tag名称',
  `article_id` varchar(50) DEFAULT NULL COMMENT '文章id',
  `is_delete` varchar(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `Index_tag_id` (`tag_id`),
  KEY `Index_tag_name` (`tag_name`),
  KEY `Index_article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qfeng_reply
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_reply`;
CREATE TABLE `qfeng_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `comment_id` varchar(50) DEFAULT NULL COMMENT '评论id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `target_id` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `Index_comment` (`comment_id`),
  KEY `Index_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qfeng_tag
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_tag`;
CREATE TABLE `qfeng_tag` (
  `id` varchar(50) NOT NULL COMMENT 'tag主键',
  `tag_name` varchar(50) DEFAULT NULL COMMENT 'tag名称',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `Index_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qfeng_user
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_user`;
CREATE TABLE `qfeng_user` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(10000) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `Index_username` (`username`),
  KEY `Index_password` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qfeng_visit_record
-- ----------------------------
DROP TABLE IF EXISTS `qfeng_visit_record`;
CREATE TABLE `qfeng_visit_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `article_id` varchar(50) DEFAULT NULL COMMENT '文章id',
  `host` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `Index_article` (`article_id`),
  KEY `Index_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8;

ALTER TABLE `qfeng_article`
ADD COLUMN `preview`  varchar(500) NULL AFTER `modify_time`;
