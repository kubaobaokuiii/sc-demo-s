/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50024
Source Host           : localhost:3306
Source Database       : guoadmin

Target Server Type    : MYSQL
Target Server Version : 50024
File Encoding         : 65001

Date: 2019-04-11 18:15:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fight_management
-- ----------------------------
DROP TABLE IF EXISTS `fight_management`;
CREATE TABLE `fight_management` (
  `remark` varchar(255) default NULL COMMENT '备注',
  `people` varchar(255) default NULL COMMENT '订票人数',
  `capacity` varchar(255) default NULL COMMENT '容量',
  `time` varchar(255) default NULL COMMENT '航班时间',
  `endtime` varchar(255) default NULL COMMENT '航班飞行结束时间',
  `starttime` varchar(255) default NULL COMMENT '航班飞行开始时间',
  `fight_id` int(255) NOT NULL auto_increment,
  `id` int(255) default NULL COMMENT '用户id,查看此次航班被哪些人所预定',
  `number` varchar(255) default NULL COMMENT '航班号',
  PRIMARY KEY  (`fight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fight_management
-- ----------------------------
INSERT INTO `fight_management` VALUES ('机票one', '33', '33', '2018年12月12日', '2018年12月12日', '2018年12月12日', '1', '16', 'k2223');
INSERT INTO `fight_management` VALUES ('机票two', '34', '34', '2018年12月13日', '2018年12月13日', '2018年12月11日', '3', '17', 'k22245');
INSERT INTO `fight_management` VALUES ('机票three', '34', '2', '1', '9:00', '8:00', '5', '18', 'k9909');
INSERT INTO `fight_management` VALUES ('机票four', '37', '7', '7', '7', '77', '7', '18', 'k677');
INSERT INTO `fight_management` VALUES ('机票five', '39', '9', '9', '9', '9', '8', '13', 'k999');
INSERT INTO `fight_management` VALUES ('机票six', '49', '9', '9', '9', '9', '10', '14', 'k9');
INSERT INTO `fight_management` VALUES ('机票seven', '57', '7', '7', '7', '7', '13', '2', 'k7');
INSERT INTO `fight_management` VALUES ('机票eight', '53', '3', '3', '3', '3', '15', null, 'k8888');
