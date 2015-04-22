/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : lovepet

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-22 12:54:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for feed
-- ----------------------------
DROP TABLE IF EXISTS `feed`;
CREATE TABLE `feed` (
  `feed_id` int(11) NOT NULL COMMENT '话题编号',
  `user_id` int(11) NOT NULL COMMENT '发布话题的用户编号',
  `feed_content` text NOT NULL COMMENT '文字内容',
  `feed_photo` char(255) CHARACTER SET latin1 COLLATE latin1_bin DEFAULT NULL COMMENT '图片编号',
  `feed_original` int(11) DEFAULT NULL COMMENT '原始微博，\r\n为空则为原创微博',
  `feed_fav_num` int(11) NOT NULL COMMENT '收藏次数',
  `feed_reply_num` int(11) NOT NULL COMMENT '评论次数',
  `feed_repost_num` int(11) NOT NULL COMMENT '转发次数',
  `feed_submit_time` datetime NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`feed_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `feed_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态';

-- ----------------------------
-- Table structure for feed_fav
-- ----------------------------
DROP TABLE IF EXISTS `feed_fav`;
CREATE TABLE `feed_fav` (
  `ffav_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '收藏者用户编号',
  `feed_id` int(11) NOT NULL COMMENT '消息编号',
  `ffav_time` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`ffav_id`),
  KEY `user_id` (`user_id`),
  KEY `feed_fav_ibfk_2` (`feed_id`),
  CONSTRAINT `feed_fav_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `feed_fav_ibfk_2` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态收藏';

-- ----------------------------
-- Table structure for feed_reply
-- ----------------------------
DROP TABLE IF EXISTS `feed_reply`;
CREATE TABLE `feed_reply` (
  `freply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `feed_id` int(11) NOT NULL COMMENT '话题编号',
  `user_id` int(11) NOT NULL COMMENT '评论者编号',
  `freply_submit_time` datetime NOT NULL COMMENT '提交时间',
  `freply_content` text NOT NULL COMMENT '评论内容',
  PRIMARY KEY (`freply_id`),
  KEY `user_id` (`user_id`),
  KEY `feed_id` (`feed_id`),
  CONSTRAINT `feed_reply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `feed_reply_ibfk_2` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态回复';

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '发送者编号',
  `receive_user_id` int(11) NOT NULL COMMENT '接收者编号',
  `message_content` text NOT NULL COMMENT '私信内容',
  `message_datetime` datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`message_id`),
  KEY `user_id` (`user_id`),
  KEY `receive_user_id` (`receive_user_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`receive_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='私信';

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `pet_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '宠物编号',
  `user_id` int(11) NOT NULL COMMENT '宠物主人编号',
  `pet_name` varchar(255) NOT NULL COMMENT '宠物昵称',
  `pet_photo` varchar(255) DEFAULT NULL COMMENT '宠物图片',
  PRIMARY KEY (`pet_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `pet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宠物';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_email` varchar(255) NOT NULL COMMENT '邮箱',
  `user_pwd` varchar(255) NOT NULL COMMENT '密码',
  `user_alias` varchar(255) NOT NULL COMMENT '显示名',
  `user_privilege` int(11) NOT NULL COMMENT '用户权限\r\n才用二进制模式，从右倒左\r\n1 普通用户权限\r\n2 内容管理权限\r\n3 系统管理权限',
  `user_portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `user_sex` tinyint(2) NOT NULL COMMENT '性别:\r\n0为保密,\r\n1为男性,\r\n2为女性',
  `user_real_name` varchar(255) NOT NULL COMMENT '真实姓名',
  `user_phone` varchar(255) NOT NULL COMMENT '手机号',
  `user_profile` text COMMENT '个性签名',
  `user_follow_num` int(11) NOT NULL COMMENT '关注数量',
  `user_fans_num` int(11) NOT NULL COMMENT '粉丝数量',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户';

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow` (
  `follow_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关注的编号',
  `follow_fans_id` int(11) NOT NULL COMMENT '关注人id',
  `follow_user_id` int(11) NOT NULL COMMENT '被关注人id',
  `follow_time` datetime NOT NULL COMMENT '关注时间',
  PRIMARY KEY (`follow_id`),
  KEY `follow_fans_id` (`follow_fans_id`),
  KEY `follow_user_id` (`follow_user_id`),
  CONSTRAINT `user_follow_ibfk_1` FOREIGN KEY (`follow_fans_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_follow_ibfk_2` FOREIGN KEY (`follow_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注';
