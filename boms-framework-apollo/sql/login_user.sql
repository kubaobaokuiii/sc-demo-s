/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50024
Source Host           : localhost:3306
Source Database       : guoadmin

Target Server Type    : MYSQL
Target Server Version : 50024
File Encoding         : 65001

Date: 2019-04-18 14:03:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login_user
-- ----------------------------
DROP TABLE IF EXISTS `login_user`;
CREATE TABLE `login_user` (
  `adress` varchar(255) default NULL COMMENT '机票管理id',
  `sex` varchar(255) default NULL,
  `age` varchar(255) default NULL,
  `birthday` varchar(255) default NULL,
  `card` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `fight_id` int(255) default NULL COMMENT '机票管理id',
  `id` int(255) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_user
-- ----------------------------
INSERT INTO `login_user` VALUES ('2', '2', '2', '2', '2', 'admin', 'admin', '1', '2');
INSERT INTO `login_user` VALUES ('2', '2', '2', '2', '2', null, '1823', '8', '13');
INSERT INTO `login_user` VALUES ('xx', '女', '12', '1995.12.12', '1', null, '18232932', '3', '14');
INSERT INTO `login_user` VALUES ('xxx', '女', '23', '1988', '134', null, '1823293292', '5', '16');
INSERT INTO `login_user` VALUES ('4', '女', '143', '144', '1323', null, '182', '7', '17');
INSERT INTO `login_user` VALUES ('444', '男', '44', '444', '111', null, '13230428105', '8', '18');
INSERT INTO `login_user` VALUES ('xxx', '女', '13', '1996.12.12', '1311', null, '18232932923', null, '20');
INSERT INTO `login_user` VALUES ('11', '1', '11', '11', '43543543', null, '18232932923', null, '21');
