/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80029
Source Host           : localhost:3306
Source Database       : asurplus-vue

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2022-10-17 13:58:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dev_info
-- ----------------------------
DROP TABLE IF EXISTS `dev_info`;
CREATE TABLE `dev_info` (
  `dev_id` int unsigned NOT NULL,
  `dev_name` varchar(255) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `port` int DEFAULT NULL,
  `bu_id` int DEFAULT NULL,
  `fl_id` int DEFAULT NULL,
  `ro_id` int DEFAULT NULL,
  `del_flag` tinyint DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_user` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`dev_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dev_info
-- ----------------------------
INSERT INTO `dev_info` VALUES ('1', '', 'localhost', '9090', '1', '1', '1', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
INSERT INTO `dev_info` VALUES ('2', '', 'localhost', '9090', '1', '1', '2', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
INSERT INTO `dev_info` VALUES ('3', '', 'localhost', '9090', '1', '1', '3', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
INSERT INTO `dev_info` VALUES ('4', '', 'localhost', '9090', '1', '2', '1', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
INSERT INTO `dev_info` VALUES ('5', '', 'localhost', '9090', '1', '2', '2', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
INSERT INTO `dev_info` VALUES ('6', '', 'localhost', '9090', '1', '3', '1', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
INSERT INTO `dev_info` VALUES ('7', '', 'localhost', '9090', '2', '1', '1', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
INSERT INTO `dev_info` VALUES ('8', '', 'localhost', '9090', '2', '2', '1', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
INSERT INTO `dev_info` VALUES ('9', '', 'localhost', '9090', '3', '1', '1', '0', '', '1', '2022-10-17 10:53:11', '1', '2022-10-17 10:53:17');
