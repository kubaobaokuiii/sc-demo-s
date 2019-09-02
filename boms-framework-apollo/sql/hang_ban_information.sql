/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50024
Source Host           : localhost:3306
Source Database       : guoadmin

Target Server Type    : MYSQL
Target Server Version : 50024
File Encoding         : 65001

Date: 2019-04-18 14:03:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hang_ban_information
-- ----------------------------
DROP TABLE IF EXISTS `hang_ban_information`;
CREATE TABLE `hang_ban_information` (
  `book_tickets_time` varchar(255) default NULL,
  `hang_ban_capa` int(255) default NULL,
  `hang_ban_id` int(255) NOT NULL auto_increment COMMENT '航班id',
  `fight_id` int(255) default NULL COMMENT '飞机信息id',
  `id` int(255) default NULL COMMENT '用户id',
  PRIMARY KEY  (`hang_ban_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hang_ban_information
-- ----------------------------
INSERT INTO `hang_ban_information` VALUES (null, '30', '2', '3', '13');
INSERT INTO `hang_ban_information` VALUES (null, '30', '3', '3', '14');
INSERT INTO `hang_ban_information` VALUES (null, '30', '4', '7', '17');
INSERT INTO `hang_ban_information` VALUES (null, '30', '5', '8', '13');
INSERT INTO `hang_ban_information` VALUES (null, '30', '6', '10', '2');
INSERT INTO `hang_ban_information` VALUES (null, '30', '7', '13', '16');
