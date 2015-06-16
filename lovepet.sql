/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : lovepet

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-06-16 16:19:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dating
-- ----------------------------
DROP TABLE IF EXISTS `dating`;
CREATE TABLE `dating` (
  `dating_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '话题编号',
  `user_id` int(11) NOT NULL COMMENT '发布话题的用户编号',
  `dating_submit_time` datetime NOT NULL COMMENT '发布时间',
  `dating_content` text NOT NULL COMMENT '文字内容',
  `dating_pet` text NOT NULL COMMENT '图片编号',
  `dating_location` text NOT NULL COMMENT '图片编号',
  `dating_time` datetime NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`dating_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `dating_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='动态';

-- ----------------------------
-- Records of dating
-- ----------------------------
INSERT INTO `dating` VALUES ('1', '2', '2015-05-07 00:57:57', '我要去遛狗，有人去嘛', '腊肠', '北京交通大学', '2015-05-07 00:57:57');
INSERT INTO `dating` VALUES ('2', '2', '2015-05-07 01:50:37', '只约狗不约炮', '哈士奇', '天安门', '2015-05-07 01:50:37');
INSERT INTO `dating` VALUES ('3', '3', '2015-05-07 02:13:43', '求配种', '萨摩耶', '西直门', '2015-05-07 02:13:43');
INSERT INTO `dating` VALUES ('4', '2', '2015-06-16 11:29:15', 'test1', 'test2', 'test3', '2015-01-01 07:01:08');

-- ----------------------------
-- Table structure for feed
-- ----------------------------
DROP TABLE IF EXISTS `feed`;
CREATE TABLE `feed` (
  `feed_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '话题编号',
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='动态';

-- ----------------------------
-- Records of feed
-- ----------------------------
INSERT INTO `feed` VALUES ('1', '2', '话说。小公主今天正式注册了官方的出生证明。。。一家仨人名字全都这么长', '/upload/feed/20150511193851.jpg', null, '3', '0', '3', '2015-05-07 00:57:57');
INSERT INTO `feed` VALUES ('2', '2', '像射手这样，善忘，心大也挺好的。因为对世界永远都有美好的向往，永远积极向上。但你得明白，射手不是真的心大，因为心大背后的意义是要有更强的心脏去支持和承担这一切。', null, null, '0', '0', '3', '2015-05-07 01:50:37');
INSERT INTO `feed` VALUES ('3', '3', '111', null, null, '0', '0', '0', '2015-05-07 02:13:43');
INSERT INTO `feed` VALUES ('4', '4', '支持', null, '2', '0', '0', '0', '2015-05-07 02:13:57');
INSERT INTO `feed` VALUES ('5', '2', '支持', null, '2', '0', '0', '0', '2015-05-11 10:33:26');
INSERT INTO `feed` VALUES ('6', '2', '撒大事', null, null, '0', '0', '0', '2015-05-12 16:41:11');
INSERT INTO `feed` VALUES ('7', '2', '是', null, '1', '0', '0', '0', '2015-05-12 16:43:33');
INSERT INTO `feed` VALUES ('8', '2', '131', null, '1', '0', '3', '0', '2015-05-12 16:45:48');
INSERT INTO `feed` VALUES ('9', '2', '╮(╯_╰)╭', null, null, '0', '1', '0', '2015-05-12 16:49:20');
INSERT INTO `feed` VALUES ('10', '2', '╮(╯_╰)╭', null, null, '0', '0', '1', '2015-05-15 21:37:36');
INSERT INTO `feed` VALUES ('11', '2', 'test', null, '1', '0', '0', '0', '2015-05-15 21:56:27');
INSERT INTO `feed` VALUES ('12', '2', 'test2', null, '2', '0', '0', '0', '2015-05-15 21:57:42');
INSERT INTO `feed` VALUES ('13', '2', 'test1111', null, '10', '0', '0', '0', '2015-05-16 08:35:04');
INSERT INTO `feed` VALUES ('14', '5', 'first', null, null, '0', '0', '0', '2015-05-19 11:10:18');

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
-- Records of feed_fav
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='动态回复';

-- ----------------------------
-- Records of feed_reply
-- ----------------------------
INSERT INTO `feed_reply` VALUES ('1', '8', '2', '2015-05-13 00:54:58', 'test');
INSERT INTO `feed_reply` VALUES ('2', '8', '3', '2015-05-13 00:57:00', '╮(╯_╰)╭');
INSERT INTO `feed_reply` VALUES ('3', '8', '4', '2015-05-13 16:44:22', 'test');
INSERT INTO `feed_reply` VALUES ('4', '9', '2', '2015-05-15 20:42:37', '啦啦');

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
-- Records of message
-- ----------------------------

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
-- Records of pet
-- ----------------------------

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
  `user_feed_num` int(11) NOT NULL COMMENT '动态数量',
  `user_follow_num` int(11) NOT NULL COMMENT '关注数量',
  `user_fans_num` int(11) NOT NULL COMMENT '粉丝数量',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin@admin.com', 'ee10c315eba2c75b403ea99136f5b48d', '管理员', '7', null, '0', '', '', null, '0', '0', '0');
INSERT INTO `user` VALUES ('2', 'j3l11234', '297259024@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'j3l11234', '7', '/upload/portrait/j3l11234.png', '1', '蒋圣', '', '哈哈哈', '0', '1', '1');
INSERT INTO `user` VALUES ('3', 'ymy', 'ymy@123.com', 'e10adc3949ba59abbe56e057f20f883e', 'ymy', '1', '/upload/portrait/ymy.jpg', '2', '杨梦月', '', null, '0', '0', '0');
INSERT INTO `user` VALUES ('4', 'ym', 'ym@123.com', 'e10adc3949ba59abbe56e057f20f883e', 'ym', '1', '/upload/portrait/ymy.jpg', '2', '衣萌', '', '', '0', '0', '0');
INSERT INTO `user` VALUES ('5', 'hyq', 'ym@123.com', 'e10adc3949ba59abbe56e057f20f883e', 'hyq', '1', null, '0', '韩永琪', '', null, '0', '0', '0');
INSERT INTO `user` VALUES ('6', 'yqy', 'ym@123.com', 'e10adc3949ba59abbe56e057f20f883e', 'yqy', '1', null, '0', '尹琦云', '', null, '0', '0', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户关注';

-- ----------------------------
-- Records of user_follow
-- ----------------------------
INSERT INTO `user_follow` VALUES ('1', '2', '3', '2015-05-01 03:07:21');
INSERT INTO `user_follow` VALUES ('2', '2', '4', '2015-05-01 03:07:21');
