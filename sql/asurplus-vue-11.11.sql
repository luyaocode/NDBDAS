/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80029
Source Host           : localhost:3306
Source Database       : asurplus-vue

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2022-11-11 10:56:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cmd_dict
-- ----------------------------
DROP TABLE IF EXISTS `cmd_dict`;
CREATE TABLE `cmd_dict` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `cmd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `resp_form` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of cmd_dict
-- ----------------------------
INSERT INTO `cmd_dict` VALUES ('1', 'rand 0000 0006 01 03 7534 0001', 'rand 0000 0006 01 03 06 08bb 08bb 08bb', '读电压块，地址30004=0x7541，数量1；响应格式：2x3=6字节数据，假设三相电压都是223.5V，2235=0x08bb');
INSERT INTO `cmd_dict` VALUES ('2', 'rand 0000 0006 01 03 7538 0001', 'rand 0000 0006 01 03 06 0000 0000 0000', '读电流块');
INSERT INTO `cmd_dict` VALUES ('3', 'rand 0000 0006 01 03 753d 0001', 'rand 0000 0006 01 03 08 0000 0000 0000 0000', '读有功功率数据块，地址30018=0x753d，数量1；响应格式：2x4=8字节');
INSERT INTO `cmd_dict` VALUES ('4', 'rand 0000 0006 01 03 7542 0001', 'rand 0000 0006 01 03 08 0000 0000 0000 0000', '读无功功率数据块');
INSERT INTO `cmd_dict` VALUES ('5', 'rand 0000 0006 01 03 7547 0001', 'rand 0000 0006 01 03 08 0000 0000 0000 0000', '读视在功率数据块');
INSERT INTO `cmd_dict` VALUES ('6', 'rand 0000 0006 01 03 754C 0001', 'rand 0000 0006 01 03 08 0000 0000 0000 0000', '读功率因数数据块');
INSERT INTO `cmd_dict` VALUES ('7', 'rand 0000 0006 01 03 7531 0001', 'rand 0000 0006 01 03 08 0000', '读A相电压');
INSERT INTO `cmd_dict` VALUES ('8', 'rand 0000 0006 01 03 7532 0001', null, '读B相电压');
INSERT INTO `cmd_dict` VALUES ('9', 'rand 0000 0006 01 03 7533 0001', null, '读C相电压');
INSERT INTO `cmd_dict` VALUES ('10', 'rand 0000 0006 01 03 7535 0001', null, '读A相电流');
INSERT INTO `cmd_dict` VALUES ('11', 'rand 0000 0006 01 03 7536 0001', null, '读A相电流');
INSERT INTO `cmd_dict` VALUES ('12', 'rand 0000 0006 01 03 7537 0001', null, '读A相电流');
INSERT INTO `cmd_dict` VALUES ('13', 'rand 0000 0006 01 03 7539 0001', null, '读总有功功率');
INSERT INTO `cmd_dict` VALUES ('14', 'rand 0000 0006 01 03 753a 0001', null, '读A相有功功率');
INSERT INTO `cmd_dict` VALUES ('15', 'rand 0000 0006 01 03 753b 0001', null, '读B相有功功率');
INSERT INTO `cmd_dict` VALUES ('16', 'rand 0000 0006 01 03 753c 0001', null, '读C相有功功率');

-- ----------------------------
-- Table structure for dev_epara_info_t1
-- ----------------------------
DROP TABLE IF EXISTS `dev_epara_info_t1`;
CREATE TABLE `dev_epara_info_t1` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `dev_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备编号',
  `dev_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备名称',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint DEFAULT '0',
  `del_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `freq` float DEFAULT NULL,
  `total_pow` float DEFAULT NULL,
  `volt_a` float DEFAULT NULL,
  `volt_b` float DEFAULT NULL,
  `volt_c` float DEFAULT NULL,
  `curr_a` float DEFAULT NULL,
  `curr_b` float DEFAULT NULL,
  `curr_c` float DEFAULT NULL,
  `acti_pow_a` float DEFAULT NULL,
  `acti_pow_b` float DEFAULT NULL,
  `acti_pow_c` float DEFAULT NULL,
  `watt_pow_a` float DEFAULT NULL,
  `watt_pow_b` float DEFAULT NULL,
  `watt_pow_c` float DEFAULT NULL,
  `appr_pow_a` float DEFAULT NULL,
  `appr_pow_b` float DEFAULT NULL,
  `appr_pow_c` float DEFAULT NULL,
  `volt_pha_a` float DEFAULT NULL,
  `volt_pha_b` float DEFAULT NULL,
  `volt_pha_c` float DEFAULT NULL,
  `curr_pha_a` float DEFAULT NULL,
  `curr_pha_b` float DEFAULT NULL,
  `curr_pha_c` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4734 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dev_epara_info_t1
-- ----------------------------

-- ----------------------------
-- Table structure for dev_epara_info_temp
-- ----------------------------
DROP TABLE IF EXISTS `dev_epara_info_temp`;
CREATE TABLE `dev_epara_info_temp` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `dev_id` varchar(64) DEFAULT NULL COMMENT '设备编号',
  `dev_name` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `freq` float DEFAULT NULL,
  `volt_a` float DEFAULT NULL,
  `volt_b` float DEFAULT NULL,
  `volt_c` float DEFAULT NULL,
  `curr_a` float DEFAULT NULL,
  `curr_b` float DEFAULT NULL,
  `curr_c` float DEFAULT NULL,
  `acti_pow_a` float DEFAULT NULL,
  `acti_pow_b` float DEFAULT NULL,
  `acti_pow_c` float DEFAULT NULL,
  `acti_pow_total` float DEFAULT NULL,
  `watt_pow_a` float DEFAULT NULL,
  `watt_pow_b` float DEFAULT NULL,
  `watt_pow_c` float DEFAULT NULL,
  `watt_pow_total` float DEFAULT NULL,
  `appr_pow_a` float DEFAULT NULL,
  `appr_pow_b` float DEFAULT NULL,
  `appr_pow_c` float DEFAULT NULL,
  `appr_pow_total` float DEFAULT NULL,
  `pow_fac_a` float DEFAULT NULL,
  `pow_fac_b` float DEFAULT NULL,
  `pow_fac_c` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4840 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dev_epara_info_temp
-- ----------------------------

-- ----------------------------
-- Table structure for dev_info
-- ----------------------------
DROP TABLE IF EXISTS `dev_info`;
CREATE TABLE `dev_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `dev_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `loc_id` int NOT NULL DEFAULT '0',
  `dev_name` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `del_flag` tinyint NOT NULL DEFAULT '0',
  `remark` varchar(255) DEFAULT NULL,
  `create_user` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dev_info
-- ----------------------------
INSERT INTO `dev_info` VALUES ('67', '0x10', '11', 'test', '1', '0', 'testtest', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('68', '0x11', '11', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('69', '0x12', '11', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('70', '0x13', '12', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('71', '0x14', '12', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('72', '0x15', '12', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('73', '0x16', '13', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('74', '0x17', '13', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('75', '0x18', '13', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('76', '0x19', '14', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:12:56');
INSERT INTO `dev_info` VALUES ('77', '0x1A', '14', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:13:00');
INSERT INTO `dev_info` VALUES ('78', '0x1B', '14', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:13:00');
INSERT INTO `dev_info` VALUES ('79', '0x1C', '1', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:13:00');
INSERT INTO `dev_info` VALUES ('80', '0x1D', '2', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:13:00');
INSERT INTO `dev_info` VALUES ('81', '0x1E', '3', 'test', '1', '0', 'test', '1', '2022-11-03 13:03:43', '1', '2022-11-04 13:13:00');

-- ----------------------------
-- Table structure for dev_loc_info
-- ----------------------------
DROP TABLE IF EXISTS `dev_loc_info`;
CREATE TABLE `dev_loc_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `pid` varchar(16) DEFAULT NULL,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sort` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dev_loc_info
-- ----------------------------
INSERT INTO `dev_loc_info` VALUES ('1', '0', '一栋', '1');
INSERT INTO `dev_loc_info` VALUES ('2', '0', '二栋', '2');
INSERT INTO `dev_loc_info` VALUES ('3', '0', '三栋', '3');
INSERT INTO `dev_loc_info` VALUES ('4', '0', '四栋', '4');
INSERT INTO `dev_loc_info` VALUES ('5', '0', '五栋', '5');
INSERT INTO `dev_loc_info` VALUES ('6', '1', '一栋一层', '1');
INSERT INTO `dev_loc_info` VALUES ('7', '1', '一栋二层', '2');
INSERT INTO `dev_loc_info` VALUES ('8', '1', '一栋三层', '3');
INSERT INTO `dev_loc_info` VALUES ('9', '1', '一栋四层', '4');
INSERT INTO `dev_loc_info` VALUES ('10', '1', '一栋五层', '5');
INSERT INTO `dev_loc_info` VALUES ('11', '6', '一栋一层一号', '1');
INSERT INTO `dev_loc_info` VALUES ('12', '6', '一栋一层二号', '2');
INSERT INTO `dev_loc_info` VALUES ('13', '6', '一栋一层三号', '3');
INSERT INTO `dev_loc_info` VALUES ('14', '6', '一栋一层四号', '4');
INSERT INTO `dev_loc_info` VALUES ('15', '6', '一栋一层五号', '5');

-- ----------------------------
-- Table structure for gateway_info
-- ----------------------------
DROP TABLE IF EXISTS `gateway_info`;
CREATE TABLE `gateway_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '网关主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '网关名称',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '网关网络地址',
  `port` int DEFAULT NULL COMMENT '网关端口号',
  `type` tinyint DEFAULT '0' COMMENT '系统内置（0-是 1-否）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0-未删除 1-已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gateway_info
-- ----------------------------
INSERT INTO `gateway_info` VALUES ('1', '本地网关', 'localhost', '9090', '1', '本机测试用', '1', '2022-09-22 10:38:39', '0', '2022-09-22 10:38:39', '0');
INSERT INTO `gateway_info` VALUES ('2', '测试', '192.168.1.200', '4196', '0', '联机测试用', '0', '2022-09-22 10:38:39', '0', '2022-09-22 10:38:39', '0');
INSERT INTO `gateway_info` VALUES ('4', '测试', '1', '1', '0', '1', '1', '2022-11-04 16:49:31', '0', '2022-11-04 16:49:31', '0');

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `table_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '其它生成选项',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='代码生成业务表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `column_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` int DEFAULT '0' COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT '0' COMMENT '排序',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='代码生成业务表字段';

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `app_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'APP名称',
  `version_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本名称',
  `version_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '版本号',
  `version_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本描述',
  `is_force` tinyint DEFAULT '0' COMMENT '是否强制更新（0-否1-是）',
  `ios` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '【IOS】下载地址',
  `android` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '【Android】下载地址',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='app版本信息';

-- ----------------------------
-- Records of sys_app
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_info`;
CREATE TABLE `sys_dept_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `pid` int DEFAULT '0' COMMENT '上级部门',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `link_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `sort` int DEFAULT '0' COMMENT '排序（值越大，越靠前）',
  `status` tinyint DEFAULT '0' COMMENT '状态（0--正常1--停用）',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='部门信息表';

-- ----------------------------
-- Records of sys_dept_info
-- ----------------------------
INSERT INTO `sys_dept_info` VALUES ('1', '0', '技术部', '张三', '15685963325', '2', '0', '0', '2020-11-24 16:19:16', '1', '2020-12-05 20:01:46', '0');
INSERT INTO `sys_dept_info` VALUES ('2', '1', '项目一组', '李四', '13588895626', '3', '0', '0', '2020-11-24 16:19:40', '1', '2020-12-05 20:01:46', '0');
INSERT INTO `sys_dept_info` VALUES ('3', '1', '项目二组', '王五', '15888889654', '2', '0', '0', '2020-11-24 16:20:01', '0', '2020-12-05 20:01:46', '0');
INSERT INTO `sys_dept_info` VALUES ('4', '0', '人事部', '赵六', '16233355665', '1', '0', '0', '2020-11-24 17:11:46', '0', '2020-11-25 15:03:19', '0');
INSERT INTO `sys_dept_info` VALUES ('5', '4', '人事一部', '王七', '15266668895', '1', '0', '0', '2020-11-24 17:12:10', '0', '2020-11-25 15:03:19', '0');
INSERT INTO `sys_dept_info` VALUES ('6', '4', '人事二部', '李好', '14566333589', '2', '0', '0', '2020-11-24 17:12:39', '0', '2022-10-20 14:29:51', '0');
INSERT INTO `sys_dept_info` VALUES ('7', '2', '项目成员一', '粒粒', '18588889999', '0', '0', '1', '2020-11-25 14:28:31', '1', '2020-12-05 20:01:46', '0');
INSERT INTO `sys_dept_info` VALUES ('8', '3', '项目成员二', '老领', '15866633355', '1', '0', '1', '2020-11-25 14:31:17', '1', '2020-12-05 20:01:46', '0');
INSERT INTO `sys_dept_info` VALUES ('9', '1', '项目三组', '威威', '15622266656', '1', '0', '1', '2020-11-25 15:25:29', '1', '2020-12-05 20:01:46', '0');
INSERT INTO `sys_dept_info` VALUES ('10', '1', '项目四组', '咳咳', '18555666669', '0', '0', '1', '2020-11-25 15:25:44', '1', '2020-12-05 20:01:46', '0');
INSERT INTO `sys_dept_info` VALUES ('11', '0', '销售部', '连聊', '18885668676', '3', '0', '1', '2020-11-25 15:26:09', '0', '2021-07-12 13:27:02', '0');
INSERT INTO `sys_dept_info` VALUES ('12', '11', '销售一部', '普片', '14778786877', '0', '0', '1', '2020-11-25 15:26:27', '0', '2021-07-12 13:27:02', '0');
INSERT INTO `sys_dept_info` VALUES ('13', '0', '经理部', null, null, '1', '0', '1', '2021-07-20 10:18:05', '0', '2021-07-20 10:18:05', '0');
INSERT INTO `sys_dept_info` VALUES ('14', '13', '经理一部', '', '', '1', '0', '1', '2021-07-20 10:18:16', '1', '2021-07-20 10:34:24', '0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `remake` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `create_user` int DEFAULT '0' COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0，正常，1已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='字典管理';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', 'status', '数据状态', '', '1', '2021-07-18 22:22:20', '1', '2021-07-18 23:17:15', '0');
INSERT INTO `sys_dict` VALUES ('2', 'sex', '用户性别', '', '1', '2021-07-18 23:16:58', '1', '2021-07-18 23:16:58', '0');
INSERT INTO `sys_dict` VALUES ('3', 'visible', '菜单是否显示', '', '1', '2021-07-18 23:18:48', '1', '2021-07-18 23:18:48', '0');
INSERT INTO `sys_dict` VALUES ('4', 'is_success', '是否成功', null, '1', '2021-07-20 11:45:03', '0', '2021-07-20 11:45:03', '0');
INSERT INTO `sys_dict` VALUES ('5', 'is_run', '是否启动', '', '1', '2021-07-22 21:24:22', '1', '2021-07-22 21:24:22', '0');
INSERT INTO `sys_dict` VALUES ('6', 'oper_type', '操作日志--操作类型', '', '1', '2021-07-26 11:50:45', '1', '2021-07-26 11:50:45', '0');
INSERT INTO `sys_dict` VALUES ('7', 'is_builtIn', '是否内置', null, '1', '2021-07-26 17:07:11', '0', '2021-07-26 17:07:11', '0');
INSERT INTO `sys_dict` VALUES ('8', 'is_force', '是否强制更新', null, '1', '2021-07-26 21:57:30', '0', '2021-07-26 21:57:30', '0');

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典编码',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编号',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `list_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '键值样式',
  `remake` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='字典配置';

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES ('1', 'status', '0', '正常', 'primary', null, '1', '2021-07-18 23:16:35', '0', '2021-07-18 23:17:21');
INSERT INTO `sys_dict_detail` VALUES ('2', 'status', '1', '停用', 'danger', '', '1', '2021-07-18 23:16:43', '1', '2021-07-18 23:17:22');
INSERT INTO `sys_dict_detail` VALUES ('3', 'sex', '0', '保密', 'info', null, '1', '2021-07-18 23:17:38', '0', '2021-07-18 23:17:38');
INSERT INTO `sys_dict_detail` VALUES ('4', 'sex', '1', '男', 'primary', '', '1', '2021-07-18 23:17:43', '1', '2021-07-18 23:17:43');
INSERT INTO `sys_dict_detail` VALUES ('5', 'sex', '2', '女', 'warning', null, '1', '2021-07-18 23:17:56', '0', '2021-07-18 23:17:56');
INSERT INTO `sys_dict_detail` VALUES ('6', 'visible', '0', '显示', 'primary', null, '1', '2021-07-18 23:18:56', '0', '2021-07-18 23:18:56');
INSERT INTO `sys_dict_detail` VALUES ('7', 'visible', '1', '隐藏', 'warning', null, '1', '2021-07-18 23:19:03', '0', '2021-07-18 23:19:03');
INSERT INTO `sys_dict_detail` VALUES ('8', 'is_success', '0', '成功', 'primary', null, '1', '2021-07-20 11:45:14', '0', '2021-07-20 11:45:14');
INSERT INTO `sys_dict_detail` VALUES ('9', 'is_success', '1', '失败', 'danger', '', '1', '2021-07-20 11:45:19', '1', '2021-07-20 11:45:19');
INSERT INTO `sys_dict_detail` VALUES ('10', 'is_run', '0', '启动', 'primary', null, '1', '2021-07-22 21:24:42', '0', '2021-07-22 21:24:42');
INSERT INTO `sys_dict_detail` VALUES ('11', 'is_run', '1', '停止', 'warning', null, '1', '2021-07-22 21:24:52', '0', '2021-07-22 21:24:52');
INSERT INTO `sys_dict_detail` VALUES ('12', 'oper_type', '0', '其他', 'info', '', '1', '2021-07-26 11:51:39', '1', '2021-07-26 12:31:23');
INSERT INTO `sys_dict_detail` VALUES ('13', 'oper_type', '1', '新增', 'primary', null, '1', '2021-07-26 11:51:51', '0', '2021-07-26 12:31:27');
INSERT INTO `sys_dict_detail` VALUES ('14', 'oper_type', '2', '修改', 'success', '', '1', '2021-07-26 11:52:08', '1', '2021-07-26 12:31:29');
INSERT INTO `sys_dict_detail` VALUES ('15', 'oper_type', '3', '删除', 'danger', '', '1', '2021-07-26 11:52:30', '1', '2021-07-26 12:31:29');
INSERT INTO `sys_dict_detail` VALUES ('16', 'oper_type', '4', '授权', 'primary', '', '1', '2021-07-26 11:52:58', '1', '2021-07-26 12:31:30');
INSERT INTO `sys_dict_detail` VALUES ('17', 'oper_type', '5', '导出', 'warning', '', '1', '2021-07-26 11:53:20', '1', '2021-07-26 12:31:30');
INSERT INTO `sys_dict_detail` VALUES ('18', 'oper_type', '6', '导入', 'warning', null, '1', '2021-07-26 11:53:42', '0', '2021-07-26 12:31:31');
INSERT INTO `sys_dict_detail` VALUES ('19', 'oper_type', '7', '强退', 'danger', null, '1', '2021-07-26 11:53:55', '0', '2021-07-26 12:31:31');
INSERT INTO `sys_dict_detail` VALUES ('20', 'oper_type', '8', '生成代码', 'info', null, '1', '2021-07-26 11:54:06', '0', '2021-07-26 12:31:32');
INSERT INTO `sys_dict_detail` VALUES ('21', 'oper_type', '9', '清空数据', 'danger', null, '1', '2021-07-26 11:54:28', '0', '2021-07-26 12:31:33');
INSERT INTO `sys_dict_detail` VALUES ('22', 'is_builtIn', '0', '是', 'primary', null, '1', '2021-07-26 17:07:28', '0', '2021-07-26 17:07:28');
INSERT INTO `sys_dict_detail` VALUES ('23', 'is_builtIn', '1', '否', 'info', null, '1', '2021-07-26 17:07:33', '0', '2021-07-26 17:07:33');
INSERT INTO `sys_dict_detail` VALUES ('24', 'is_force', '0', '否', 'info', null, '1', '2021-07-26 21:57:39', '0', '2021-07-26 21:57:39');
INSERT INTO `sys_dict_detail` VALUES ('25', 'is_force', '1', '是', 'primary', null, '1', '2021-07-26 21:57:46', '0', '2021-07-26 21:57:46');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户',
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地理位置',
  `browser` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器',
  `os` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作系统',
  `device` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备',
  `remark` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `status` tinyint DEFAULT '0' COMMENT '状态（0--成功1--失败）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=447 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='登录日志';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('110', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-02 15:36:34');
INSERT INTO `sys_login_log` VALUES ('111', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-02 17:32:35');
INSERT INTO `sys_login_log` VALUES ('112', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-02 17:33:50');
INSERT INTO `sys_login_log` VALUES ('113', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-03 14:40:56');
INSERT INTO `sys_login_log` VALUES ('114', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-03 14:41:07');
INSERT INTO `sys_login_log` VALUES ('115', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-03 14:53:34');
INSERT INTO `sys_login_log` VALUES ('116', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-03 14:55:55');
INSERT INTO `sys_login_log` VALUES ('117', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-03 15:52:03');
INSERT INTO `sys_login_log` VALUES ('118', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-03 15:52:18');
INSERT INTO `sys_login_log` VALUES ('119', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-03 15:56:59');
INSERT INTO `sys_login_log` VALUES ('120', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-03 16:08:12');
INSERT INTO `sys_login_log` VALUES ('121', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '被顶下线', '0', '2022-08-04 00:02:10');
INSERT INTO `sys_login_log` VALUES ('122', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '登录成功', '0', '2022-08-04 00:02:10');
INSERT INTO `sys_login_log` VALUES ('123', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 09:05:52');
INSERT INTO `sys_login_log` VALUES ('124', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-05 09:05:52');
INSERT INTO `sys_login_log` VALUES ('125', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '被顶下线', '0', '2022-08-05 09:07:15');
INSERT INTO `sys_login_log` VALUES ('126', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '登录成功', '0', '2022-08-05 09:07:15');
INSERT INTO `sys_login_log` VALUES ('127', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-05 09:10:15');
INSERT INTO `sys_login_log` VALUES ('128', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 09:10:15');
INSERT INTO `sys_login_log` VALUES ('129', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 09:27:07');
INSERT INTO `sys_login_log` VALUES ('130', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-05 09:27:07');
INSERT INTO `sys_login_log` VALUES ('131', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 10:00:20');
INSERT INTO `sys_login_log` VALUES ('132', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 10:02:19');
INSERT INTO `sys_login_log` VALUES ('133', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 10:17:32');
INSERT INTO `sys_login_log` VALUES ('134', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-05 10:29:25');
INSERT INTO `sys_login_log` VALUES ('135', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 10:29:25');
INSERT INTO `sys_login_log` VALUES ('136', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Firefox 10', 'Linux', 'PC', '被顶下线', '0', '2022-08-05 10:30:35');
INSERT INTO `sys_login_log` VALUES ('137', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Firefox 10', 'Linux', 'PC', '登录成功', '0', '2022-08-05 10:30:35');
INSERT INTO `sys_login_log` VALUES ('138', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-05 10:33:56');
INSERT INTO `sys_login_log` VALUES ('139', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 10:33:56');
INSERT INTO `sys_login_log` VALUES ('140', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Firefox 10', 'Linux', 'PC', '被顶下线', '0', '2022-08-05 10:50:46');
INSERT INTO `sys_login_log` VALUES ('141', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Firefox 10', 'Linux', 'PC', '登录成功', '0', '2022-08-05 10:50:46');
INSERT INTO `sys_login_log` VALUES ('142', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-05 10:55:18');
INSERT INTO `sys_login_log` VALUES ('143', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 10:55:18');
INSERT INTO `sys_login_log` VALUES ('144', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-05 11:02:54');
INSERT INTO `sys_login_log` VALUES ('145', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 11:02:57');
INSERT INTO `sys_login_log` VALUES ('146', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-05 11:23:24');
INSERT INTO `sys_login_log` VALUES ('147', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-05 13:43:45');
INSERT INTO `sys_login_log` VALUES ('148', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-08 09:23:31');
INSERT INTO `sys_login_log` VALUES ('149', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-08 09:23:31');
INSERT INTO `sys_login_log` VALUES ('150', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-08 09:27:14');
INSERT INTO `sys_login_log` VALUES ('151', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-08 09:34:30');
INSERT INTO `sys_login_log` VALUES ('152', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-08 09:38:31');
INSERT INTO `sys_login_log` VALUES ('153', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-08 09:38:31');
INSERT INTO `sys_login_log` VALUES ('154', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-09 15:47:43');
INSERT INTO `sys_login_log` VALUES ('155', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-09 15:47:43');
INSERT INTO `sys_login_log` VALUES ('156', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-09 16:33:22');
INSERT INTO `sys_login_log` VALUES ('157', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-09 16:33:39');
INSERT INTO `sys_login_log` VALUES ('158', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-10 14:33:51');
INSERT INTO `sys_login_log` VALUES ('159', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-10 14:33:56');
INSERT INTO `sys_login_log` VALUES ('160', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-10 15:32:53');
INSERT INTO `sys_login_log` VALUES ('161', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-10 15:32:57');
INSERT INTO `sys_login_log` VALUES ('162', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-10 15:38:29');
INSERT INTO `sys_login_log` VALUES ('163', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-10 15:38:29');
INSERT INTO `sys_login_log` VALUES ('164', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-10 16:25:17');
INSERT INTO `sys_login_log` VALUES ('165', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-10 16:25:20');
INSERT INTO `sys_login_log` VALUES ('166', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-10 18:45:51');
INSERT INTO `sys_login_log` VALUES ('167', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-10 18:45:55');
INSERT INTO `sys_login_log` VALUES ('168', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-10 18:48:10');
INSERT INTO `sys_login_log` VALUES ('169', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-10 18:48:10');
INSERT INTO `sys_login_log` VALUES ('170', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-10 20:26:49');
INSERT INTO `sys_login_log` VALUES ('171', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-10 20:26:56');
INSERT INTO `sys_login_log` VALUES ('172', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 09:16:05');
INSERT INTO `sys_login_log` VALUES ('173', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 09:16:14');
INSERT INTO `sys_login_log` VALUES ('174', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-11 09:25:58');
INSERT INTO `sys_login_log` VALUES ('175', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 09:25:58');
INSERT INTO `sys_login_log` VALUES ('176', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-11 10:13:05');
INSERT INTO `sys_login_log` VALUES ('177', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 10:13:05');
INSERT INTO `sys_login_log` VALUES ('178', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '登录成功', '0', '2022-08-11 11:14:05');
INSERT INTO `sys_login_log` VALUES ('179', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '被顶下线', '0', '2022-08-11 11:14:05');
INSERT INTO `sys_login_log` VALUES ('180', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-11 11:16:32');
INSERT INTO `sys_login_log` VALUES ('181', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 11:16:32');
INSERT INTO `sys_login_log` VALUES ('182', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '被顶下线', '0', '2022-08-11 11:42:54');
INSERT INTO `sys_login_log` VALUES ('183', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '登录成功', '0', '2022-08-11 11:42:54');
INSERT INTO `sys_login_log` VALUES ('184', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-11 14:25:29');
INSERT INTO `sys_login_log` VALUES ('185', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 14:25:29');
INSERT INTO `sys_login_log` VALUES ('186', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 16:19:53');
INSERT INTO `sys_login_log` VALUES ('187', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 16:19:57');
INSERT INTO `sys_login_log` VALUES ('188', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 16:35:32');
INSERT INTO `sys_login_log` VALUES ('189', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 16:35:39');
INSERT INTO `sys_login_log` VALUES ('190', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 19:35:08');
INSERT INTO `sys_login_log` VALUES ('191', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 19:35:38');
INSERT INTO `sys_login_log` VALUES ('192', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:03:13');
INSERT INTO `sys_login_log` VALUES ('193', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:03:18');
INSERT INTO `sys_login_log` VALUES ('194', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:04:04');
INSERT INTO `sys_login_log` VALUES ('195', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:04:07');
INSERT INTO `sys_login_log` VALUES ('196', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:06:46');
INSERT INTO `sys_login_log` VALUES ('197', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:06:53');
INSERT INTO `sys_login_log` VALUES ('198', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:18:39');
INSERT INTO `sys_login_log` VALUES ('199', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:18:42');
INSERT INTO `sys_login_log` VALUES ('200', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:20:30');
INSERT INTO `sys_login_log` VALUES ('201', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:20:33');
INSERT INTO `sys_login_log` VALUES ('202', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:23:02');
INSERT INTO `sys_login_log` VALUES ('203', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:23:09');
INSERT INTO `sys_login_log` VALUES ('204', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:24:55');
INSERT INTO `sys_login_log` VALUES ('205', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:25:02');
INSERT INTO `sys_login_log` VALUES ('206', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:31:47');
INSERT INTO `sys_login_log` VALUES ('207', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:31:51');
INSERT INTO `sys_login_log` VALUES ('208', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-11 20:36:23');
INSERT INTO `sys_login_log` VALUES ('209', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:36:27');
INSERT INTO `sys_login_log` VALUES ('210', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-11 20:37:03');
INSERT INTO `sys_login_log` VALUES ('211', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:37:03');
INSERT INTO `sys_login_log` VALUES ('212', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-11 20:37:56');
INSERT INTO `sys_login_log` VALUES ('213', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-11 20:37:56');
INSERT INTO `sys_login_log` VALUES ('214', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-12 10:14:04');
INSERT INTO `sys_login_log` VALUES ('215', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-12 10:14:09');
INSERT INTO `sys_login_log` VALUES ('216', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Chrome 8', 'Windows 7', 'PC', '被顶下线', '0', '2022-08-12 10:15:06');
INSERT INTO `sys_login_log` VALUES ('217', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Chrome 8', 'Windows 7', 'PC', '登录成功', '0', '2022-08-12 10:15:06');
INSERT INTO `sys_login_log` VALUES ('218', 'admin', '10.1.10.13', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-12 10:20:17');
INSERT INTO `sys_login_log` VALUES ('219', 'admin', '10.1.10.13', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-12 10:20:17');
INSERT INTO `sys_login_log` VALUES ('220', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-12 10:33:03');
INSERT INTO `sys_login_log` VALUES ('221', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-12 10:33:03');
INSERT INTO `sys_login_log` VALUES ('222', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-12 13:29:30');
INSERT INTO `sys_login_log` VALUES ('223', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-12 13:29:30');
INSERT INTO `sys_login_log` VALUES ('224', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-12 16:05:56');
INSERT INTO `sys_login_log` VALUES ('225', 'admin', '10.1.10.33', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-12 16:05:59');
INSERT INTO `sys_login_log` VALUES ('226', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-15 09:23:56');
INSERT INTO `sys_login_log` VALUES ('227', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-15 09:23:56');
INSERT INTO `sys_login_log` VALUES ('228', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-15 14:11:11');
INSERT INTO `sys_login_log` VALUES ('229', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-15 14:11:15');
INSERT INTO `sys_login_log` VALUES ('230', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-15 15:29:55');
INSERT INTO `sys_login_log` VALUES ('231', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-15 15:30:04');
INSERT INTO `sys_login_log` VALUES ('232', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-15 15:32:03');
INSERT INTO `sys_login_log` VALUES ('233', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-15 15:32:10');
INSERT INTO `sys_login_log` VALUES ('234', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-15 16:23:40');
INSERT INTO `sys_login_log` VALUES ('235', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-15 16:23:52');
INSERT INTO `sys_login_log` VALUES ('236', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-16 17:53:27');
INSERT INTO `sys_login_log` VALUES ('237', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-16 17:53:27');
INSERT INTO `sys_login_log` VALUES ('238', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-26 14:30:27');
INSERT INTO `sys_login_log` VALUES ('239', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-26 16:14:40');
INSERT INTO `sys_login_log` VALUES ('240', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-26 16:14:54');
INSERT INTO `sys_login_log` VALUES ('241', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-31 13:53:44');
INSERT INTO `sys_login_log` VALUES ('242', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-31 13:59:56');
INSERT INTO `sys_login_log` VALUES ('243', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '被顶下线', '0', '2022-08-31 14:05:57');
INSERT INTO `sys_login_log` VALUES ('244', 'admin', '10.1.10.24', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '登录成功', '0', '2022-08-31 14:05:57');
INSERT INTO `sys_login_log` VALUES ('245', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-08-31 14:50:32');
INSERT INTO `sys_login_log` VALUES ('246', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-31 14:50:32');
INSERT INTO `sys_login_log` VALUES ('247', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-31 17:30:31');
INSERT INTO `sys_login_log` VALUES ('248', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-31 17:30:36');
INSERT INTO `sys_login_log` VALUES ('249', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-08-31 18:31:39');
INSERT INTO `sys_login_log` VALUES ('250', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-08-31 18:31:43');
INSERT INTO `sys_login_log` VALUES ('251', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-01 15:27:40');
INSERT INTO `sys_login_log` VALUES ('252', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-01 15:27:40');
INSERT INTO `sys_login_log` VALUES ('253', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-01 16:54:23');
INSERT INTO `sys_login_log` VALUES ('254', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-01 16:54:31');
INSERT INTO `sys_login_log` VALUES ('255', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-01 20:07:43');
INSERT INTO `sys_login_log` VALUES ('256', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-01 20:07:54');
INSERT INTO `sys_login_log` VALUES ('257', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-02 13:41:15');
INSERT INTO `sys_login_log` VALUES ('258', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-02 13:41:15');
INSERT INTO `sys_login_log` VALUES ('259', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-02 16:29:24');
INSERT INTO `sys_login_log` VALUES ('260', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-02 16:32:42');
INSERT INTO `sys_login_log` VALUES ('261', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-02 17:10:57');
INSERT INTO `sys_login_log` VALUES ('262', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-02 17:10:57');
INSERT INTO `sys_login_log` VALUES ('263', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-05 09:34:38');
INSERT INTO `sys_login_log` VALUES ('264', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-05 09:34:38');
INSERT INTO `sys_login_log` VALUES ('265', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-05 15:55:46');
INSERT INTO `sys_login_log` VALUES ('266', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-07 17:24:22');
INSERT INTO `sys_login_log` VALUES ('267', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-07 21:05:13');
INSERT INTO `sys_login_log` VALUES ('268', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-07 21:07:38');
INSERT INTO `sys_login_log` VALUES ('269', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-08 09:42:47');
INSERT INTO `sys_login_log` VALUES ('270', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-08 09:42:47');
INSERT INTO `sys_login_log` VALUES ('271', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-08 13:55:12');
INSERT INTO `sys_login_log` VALUES ('272', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-08 13:56:08');
INSERT INTO `sys_login_log` VALUES ('273', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-08 16:34:29');
INSERT INTO `sys_login_log` VALUES ('274', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-08 16:34:33');
INSERT INTO `sys_login_log` VALUES ('275', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-08 17:21:25');
INSERT INTO `sys_login_log` VALUES ('276', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-08 17:21:30');
INSERT INTO `sys_login_log` VALUES ('277', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-09 09:33:02');
INSERT INTO `sys_login_log` VALUES ('278', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-09 09:33:02');
INSERT INTO `sys_login_log` VALUES ('279', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-09 16:10:58');
INSERT INTO `sys_login_log` VALUES ('280', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-09 16:10:58');
INSERT INTO `sys_login_log` VALUES ('281', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-09 17:03:02');
INSERT INTO `sys_login_log` VALUES ('282', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-09 17:03:05');
INSERT INTO `sys_login_log` VALUES ('283', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-13 08:53:00');
INSERT INTO `sys_login_log` VALUES ('284', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-13 09:34:06');
INSERT INTO `sys_login_log` VALUES ('285', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-13 09:34:10');
INSERT INTO `sys_login_log` VALUES ('286', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-14 09:04:39');
INSERT INTO `sys_login_log` VALUES ('287', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-14 09:04:39');
INSERT INTO `sys_login_log` VALUES ('288', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-14 09:45:23');
INSERT INTO `sys_login_log` VALUES ('289', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-14 09:45:27');
INSERT INTO `sys_login_log` VALUES ('290', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-14 13:24:41');
INSERT INTO `sys_login_log` VALUES ('291', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-14 13:24:46');
INSERT INTO `sys_login_log` VALUES ('292', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-15 11:09:01');
INSERT INTO `sys_login_log` VALUES ('293', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-15 11:09:01');
INSERT INTO `sys_login_log` VALUES ('294', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-15 12:45:50');
INSERT INTO `sys_login_log` VALUES ('295', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-15 13:56:40');
INSERT INTO `sys_login_log` VALUES ('296', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-16 09:19:21');
INSERT INTO `sys_login_log` VALUES ('297', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-16 09:19:21');
INSERT INTO `sys_login_log` VALUES ('298', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-16 10:51:53');
INSERT INTO `sys_login_log` VALUES ('299', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-16 10:51:56');
INSERT INTO `sys_login_log` VALUES ('300', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-16 11:28:37');
INSERT INTO `sys_login_log` VALUES ('301', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-16 11:28:43');
INSERT INTO `sys_login_log` VALUES ('302', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-16 13:50:10');
INSERT INTO `sys_login_log` VALUES ('303', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-16 13:50:14');
INSERT INTO `sys_login_log` VALUES ('304', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-16 14:58:29');
INSERT INTO `sys_login_log` VALUES ('305', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-16 14:58:29');
INSERT INTO `sys_login_log` VALUES ('306', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-16 16:33:42');
INSERT INTO `sys_login_log` VALUES ('307', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-16 16:33:49');
INSERT INTO `sys_login_log` VALUES ('308', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-19 11:07:53');
INSERT INTO `sys_login_log` VALUES ('309', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-19 11:07:53');
INSERT INTO `sys_login_log` VALUES ('310', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-20 17:34:34');
INSERT INTO `sys_login_log` VALUES ('311', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-20 17:34:40');
INSERT INTO `sys_login_log` VALUES ('312', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-21 08:59:32');
INSERT INTO `sys_login_log` VALUES ('313', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-21 08:59:40');
INSERT INTO `sys_login_log` VALUES ('314', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-21 09:45:55');
INSERT INTO `sys_login_log` VALUES ('315', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-21 09:46:04');
INSERT INTO `sys_login_log` VALUES ('316', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-21 09:52:37');
INSERT INTO `sys_login_log` VALUES ('317', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-21 09:52:41');
INSERT INTO `sys_login_log` VALUES ('318', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-21 13:47:44');
INSERT INTO `sys_login_log` VALUES ('319', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-21 13:47:48');
INSERT INTO `sys_login_log` VALUES ('320', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-22 09:14:08');
INSERT INTO `sys_login_log` VALUES ('321', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-22 09:38:46');
INSERT INTO `sys_login_log` VALUES ('322', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-22 10:21:45');
INSERT INTO `sys_login_log` VALUES ('323', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-22 10:21:49');
INSERT INTO `sys_login_log` VALUES ('324', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-22 10:24:39');
INSERT INTO `sys_login_log` VALUES ('325', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-22 10:24:42');
INSERT INTO `sys_login_log` VALUES ('326', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-23 08:27:59');
INSERT INTO `sys_login_log` VALUES ('327', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-23 08:28:04');
INSERT INTO `sys_login_log` VALUES ('328', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-23 09:05:17');
INSERT INTO `sys_login_log` VALUES ('329', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-23 09:05:43');
INSERT INTO `sys_login_log` VALUES ('330', 'admin', '10.1.10.77', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-23 09:42:17');
INSERT INTO `sys_login_log` VALUES ('331', 'admin', '10.1.10.77', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-23 09:42:17');
INSERT INTO `sys_login_log` VALUES ('332', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-23 09:44:56');
INSERT INTO `sys_login_log` VALUES ('333', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-23 09:44:56');
INSERT INTO `sys_login_log` VALUES ('334', 'admin', '10.1.10.10', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '被顶下线', '0', '2022-09-23 10:14:21');
INSERT INTO `sys_login_log` VALUES ('335', 'admin', '10.1.10.10', '内网IP，无法获取位置', 'Chrome Mobile', 'Android 1.x', 'Phone', '登录成功', '0', '2022-09-23 10:14:21');
INSERT INTO `sys_login_log` VALUES ('336', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-23 10:17:53');
INSERT INTO `sys_login_log` VALUES ('337', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-23 10:17:53');
INSERT INTO `sys_login_log` VALUES ('338', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-23 15:59:47');
INSERT INTO `sys_login_log` VALUES ('339', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-23 15:59:52');
INSERT INTO `sys_login_log` VALUES ('340', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-26 14:11:30');
INSERT INTO `sys_login_log` VALUES ('341', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-26 14:11:49');
INSERT INTO `sys_login_log` VALUES ('342', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-27 08:47:15');
INSERT INTO `sys_login_log` VALUES ('343', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-27 08:47:15');
INSERT INTO `sys_login_log` VALUES ('344', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-27 10:05:40');
INSERT INTO `sys_login_log` VALUES ('345', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-27 10:05:40');
INSERT INTO `sys_login_log` VALUES ('346', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-09-27 10:05:56');
INSERT INTO `sys_login_log` VALUES ('347', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-27 10:05:56');
INSERT INTO `sys_login_log` VALUES ('348', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-09-27 16:46:26');
INSERT INTO `sys_login_log` VALUES ('349', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-09-27 16:46:30');
INSERT INTO `sys_login_log` VALUES ('350', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-09 16:03:05');
INSERT INTO `sys_login_log` VALUES ('351', 'admin', '10.1.11.87', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-10 09:19:00');
INSERT INTO `sys_login_log` VALUES ('352', 'admin', '10.1.11.87', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-10 09:19:00');
INSERT INTO `sys_login_log` VALUES ('353', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-11 19:02:57');
INSERT INTO `sys_login_log` VALUES ('354', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-11 19:02:57');
INSERT INTO `sys_login_log` VALUES ('355', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-15 17:39:27');
INSERT INTO `sys_login_log` VALUES ('356', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-15 17:45:49');
INSERT INTO `sys_login_log` VALUES ('357', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-15 17:45:49');
INSERT INTO `sys_login_log` VALUES ('358', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-17 09:54:05');
INSERT INTO `sys_login_log` VALUES ('359', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-17 09:54:05');
INSERT INTO `sys_login_log` VALUES ('360', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-17 10:17:37');
INSERT INTO `sys_login_log` VALUES ('361', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-17 10:17:41');
INSERT INTO `sys_login_log` VALUES ('362', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-17 10:18:16');
INSERT INTO `sys_login_log` VALUES ('363', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-17 10:18:20');
INSERT INTO `sys_login_log` VALUES ('364', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-17 10:29:50');
INSERT INTO `sys_login_log` VALUES ('365', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-17 10:29:55');
INSERT INTO `sys_login_log` VALUES ('366', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-17 14:00:08');
INSERT INTO `sys_login_log` VALUES ('367', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-17 14:00:16');
INSERT INTO `sys_login_log` VALUES ('368', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-17 18:48:03');
INSERT INTO `sys_login_log` VALUES ('369', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-18 09:56:50');
INSERT INTO `sys_login_log` VALUES ('370', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-18 11:05:08');
INSERT INTO `sys_login_log` VALUES ('371', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-18 11:05:11');
INSERT INTO `sys_login_log` VALUES ('372', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-18 14:42:40');
INSERT INTO `sys_login_log` VALUES ('373', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-18 14:42:46');
INSERT INTO `sys_login_log` VALUES ('374', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-19 12:18:59');
INSERT INTO `sys_login_log` VALUES ('375', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-19 12:19:03');
INSERT INTO `sys_login_log` VALUES ('376', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-19 12:40:03');
INSERT INTO `sys_login_log` VALUES ('377', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-19 12:40:03');
INSERT INTO `sys_login_log` VALUES ('378', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-19 13:35:00');
INSERT INTO `sys_login_log` VALUES ('379', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-19 13:35:03');
INSERT INTO `sys_login_log` VALUES ('380', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-19 14:59:46');
INSERT INTO `sys_login_log` VALUES ('381', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-19 14:59:46');
INSERT INTO `sys_login_log` VALUES ('382', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-19 16:24:02');
INSERT INTO `sys_login_log` VALUES ('383', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-19 16:24:05');
INSERT INTO `sys_login_log` VALUES ('384', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-20 11:10:04');
INSERT INTO `sys_login_log` VALUES ('385', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-20 11:10:04');
INSERT INTO `sys_login_log` VALUES ('386', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-20 14:04:17');
INSERT INTO `sys_login_log` VALUES ('387', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-20 14:04:22');
INSERT INTO `sys_login_log` VALUES ('388', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-20 14:07:18');
INSERT INTO `sys_login_log` VALUES ('389', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-20 14:07:18');
INSERT INTO `sys_login_log` VALUES ('390', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-20 14:11:04');
INSERT INTO `sys_login_log` VALUES ('391', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-20 14:11:04');
INSERT INTO `sys_login_log` VALUES ('392', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-20 15:15:58');
INSERT INTO `sys_login_log` VALUES ('393', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-20 15:16:01');
INSERT INTO `sys_login_log` VALUES ('394', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-20 20:47:57');
INSERT INTO `sys_login_log` VALUES ('395', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-20 20:48:03');
INSERT INTO `sys_login_log` VALUES ('396', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-21 09:46:36');
INSERT INTO `sys_login_log` VALUES ('397', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-21 09:46:36');
INSERT INTO `sys_login_log` VALUES ('398', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-21 12:10:27');
INSERT INTO `sys_login_log` VALUES ('399', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-21 12:10:33');
INSERT INTO `sys_login_log` VALUES ('400', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-21 15:30:27');
INSERT INTO `sys_login_log` VALUES ('401', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '账户或密码不正确', '1', '2022-10-21 15:30:30');
INSERT INTO `sys_login_log` VALUES ('402', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-21 15:30:42');
INSERT INTO `sys_login_log` VALUES ('403', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-21 15:31:45');
INSERT INTO `sys_login_log` VALUES ('404', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-21 15:31:55');
INSERT INTO `sys_login_log` VALUES ('405', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-27 09:49:23');
INSERT INTO `sys_login_log` VALUES ('406', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-27 14:43:10');
INSERT INTO `sys_login_log` VALUES ('407', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-27 14:43:14');
INSERT INTO `sys_login_log` VALUES ('408', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-27 15:53:06');
INSERT INTO `sys_login_log` VALUES ('409', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-27 15:53:09');
INSERT INTO `sys_login_log` VALUES ('410', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-28 09:22:57');
INSERT INTO `sys_login_log` VALUES ('411', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-10-28 09:22:57');
INSERT INTO `sys_login_log` VALUES ('412', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-28 10:02:11');
INSERT INTO `sys_login_log` VALUES ('413', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-28 10:02:14');
INSERT INTO `sys_login_log` VALUES ('414', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-28 11:08:10');
INSERT INTO `sys_login_log` VALUES ('415', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-28 11:08:14');
INSERT INTO `sys_login_log` VALUES ('416', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-28 12:22:28');
INSERT INTO `sys_login_log` VALUES ('417', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-28 12:22:32');
INSERT INTO `sys_login_log` VALUES ('418', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-10-28 14:36:28');
INSERT INTO `sys_login_log` VALUES ('419', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-10-28 14:36:33');
INSERT INTO `sys_login_log` VALUES ('420', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-01 16:06:48');
INSERT INTO `sys_login_log` VALUES ('421', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-01 19:04:54');
INSERT INTO `sys_login_log` VALUES ('422', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-01 19:07:10');
INSERT INTO `sys_login_log` VALUES ('423', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-11-02 09:53:30');
INSERT INTO `sys_login_log` VALUES ('424', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-02 09:53:30');
INSERT INTO `sys_login_log` VALUES ('425', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-02 14:22:45');
INSERT INTO `sys_login_log` VALUES ('426', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-02 14:22:50');
INSERT INTO `sys_login_log` VALUES ('427', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-02 16:31:22');
INSERT INTO `sys_login_log` VALUES ('428', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-02 16:31:26');
INSERT INTO `sys_login_log` VALUES ('429', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-02 16:46:17');
INSERT INTO `sys_login_log` VALUES ('430', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-02 16:46:20');
INSERT INTO `sys_login_log` VALUES ('431', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-02 21:45:26');
INSERT INTO `sys_login_log` VALUES ('432', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-02 21:45:32');
INSERT INTO `sys_login_log` VALUES ('433', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-03 09:39:23');
INSERT INTO `sys_login_log` VALUES ('434', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-03 09:39:28');
INSERT INTO `sys_login_log` VALUES ('435', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-03 11:11:09');
INSERT INTO `sys_login_log` VALUES ('436', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-03 11:11:18');
INSERT INTO `sys_login_log` VALUES ('437', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-03 11:51:50');
INSERT INTO `sys_login_log` VALUES ('438', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-03 11:51:55');
INSERT INTO `sys_login_log` VALUES ('439', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-11-04 12:48:29');
INSERT INTO `sys_login_log` VALUES ('440', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-04 12:48:29');
INSERT INTO `sys_login_log` VALUES ('441', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-04 15:59:56');
INSERT INTO `sys_login_log` VALUES ('442', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-04 16:00:01');
INSERT INTO `sys_login_log` VALUES ('443', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '退出登录', '0', '2022-11-04 16:32:03');
INSERT INTO `sys_login_log` VALUES ('444', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-04 16:32:08');
INSERT INTO `sys_login_log` VALUES ('445', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '登录成功', '0', '2022-11-07 15:23:00');
INSERT INTO `sys_login_log` VALUES ('446', 'admin', '127.0.0.1', '内网IP，无法获取位置', 'Chrome 10', 'Windows 10', 'PC', '被顶下线', '0', '2022-11-07 15:23:00');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '模块标题',
  `type` tinyint DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `fun` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求方式',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '主机地址',
  `location` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作地点',
  `param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
  `status` tinyint DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '错误消息',
  `spend_time` int DEFAULT '0' COMMENT '耗时',
  `oper_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作人员',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=412 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('55', '定时任务', '2', 'com.asurplus.monitor.controller.SysQuartzInfoController.updateStatus()', 'PUT', '/monitor/sys-quartz-info/updateStatus', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '25', 'admin', '2022-08-02 15:38:22');
INSERT INTO `sys_oper_log` VALUES ('56', '定时任务', '2', 'com.asurplus.monitor.controller.SysQuartzInfoController.updateStatus()', 'PUT', '/monitor/sys-quartz-info/updateStatus', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '15', 'admin', '2022-08-02 15:38:28');
INSERT INTO `sys_oper_log` VALUES ('57', '菜单管理', '1', 'com.asurplus.system.controller.SysPermissionInfoController.add()', 'POST', '/system/sys-permission-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '47', 'admin', '2022-08-10 20:25:35');
INSERT INTO `sys_oper_log` VALUES ('58', '菜单管理', '2', 'com.asurplus.system.controller.SysPermissionInfoController.update()', 'PUT', '/system/sys-permission-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '16', 'admin', '2022-08-10 20:26:00');
INSERT INTO `sys_oper_log` VALUES ('59', '菜单管理', '3', 'com.asurplus.system.controller.SysPermissionInfoController.delete()', 'DELETE', '/system/sys-permission-info/40105', '127.0.0.1', '内网IP，无法获取位置', '{id=40105}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '29', 'admin', '2022-08-10 20:27:08');
INSERT INTO `sys_oper_log` VALUES ('60', '角色管理', '3', 'com.asurplus.system.controller.SysRoleInfoController.delete()', 'DELETE', '/system/sys-role-info/2', '127.0.0.1', '内网IP，无法获取位置', '{ids=2}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '75', 'admin', '2022-09-09 17:17:34');
INSERT INTO `sys_oper_log` VALUES ('61', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '53', 'admin', '2022-09-09 17:20:23');
INSERT INTO `sys_oper_log` VALUES ('62', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '15', 'admin', '2022-09-09 17:23:09');
INSERT INTO `sys_oper_log` VALUES ('63', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '14', 'admin', '2022-09-09 17:26:47');
INSERT INTO `sys_oper_log` VALUES ('64', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '16', 'admin', '2022-09-09 17:27:16');
INSERT INTO `sys_oper_log` VALUES ('65', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '15', 'admin', '2022-09-09 17:27:25');
INSERT INTO `sys_oper_log` VALUES ('66', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '13', 'admin', '2022-09-09 17:27:34');
INSERT INTO `sys_oper_log` VALUES ('67', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '13', 'admin', '2022-09-09 17:27:52');
INSERT INTO `sys_oper_log` VALUES ('68', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '13', 'admin', '2022-09-09 17:28:00');
INSERT INTO `sys_oper_log` VALUES ('69', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该角色名称或标识已经存在\",\"code\":500}', '0', '', '4', 'admin', '2022-09-09 17:28:09');
INSERT INTO `sys_oper_log` VALUES ('70', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该角色名称或标识已经存在\",\"code\":500}', '0', '', '4', 'admin', '2022-09-09 17:28:11');
INSERT INTO `sys_oper_log` VALUES ('71', '角色管理', '1', 'com.asurplus.system.controller.SysRoleInfoController.add()', 'POST', '/system/sys-role-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '12', 'admin', '2022-09-09 17:28:14');
INSERT INTO `sys_oper_log` VALUES ('72', '菜单管理', '3', 'com.asurplus.system.controller.SysPermissionInfoController.delete()', 'DELETE', '/system/sys-permission-info/601', '127.0.0.1', '内网IP，无法获取位置', '{id=601}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '76', 'admin', '2022-09-22 10:10:00');
INSERT INTO `sys_oper_log` VALUES ('73', '菜单管理', '3', 'com.asurplus.system.controller.SysPermissionInfoController.delete()', 'DELETE', '/system/sys-permission-info/602', '127.0.0.1', '内网IP，无法获取位置', '{id=602}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-09-22 10:10:02');
INSERT INTO `sys_oper_log` VALUES ('74', '参数配置', '2', 'com.asurplus.config.controller.SysParamController.edit()', 'PUT', '/config/sys-param/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '75', 'admin', '2022-09-22 14:31:58');
INSERT INTO `sys_oper_log` VALUES ('75', '用户管理', '5', 'com.asurplus.system.controller.SysUserInfoController.exportXls()', 'GET', '/system/sys-user-info/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '323', 'admin', '2022-10-18 16:03:18');
INSERT INTO `sys_oper_log` VALUES ('76', '用户管理', '3', 'com.asurplus.system.controller.SysUserInfoController.delete()', 'DELETE', '/system/sys-user-info/3', '127.0.0.1', '内网IP，无法获取位置', '{ids=3}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '39', 'admin', '2022-10-19 13:47:08');
INSERT INTO `sys_oper_log` VALUES ('77', '用户管理', '3', 'com.asurplus.system.controller.SysUserInfoController.delete()', 'DELETE', '/system/sys-user-info/3', '127.0.0.1', '内网IP，无法获取位置', '{ids=3}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-19 13:47:27');
INSERT INTO `sys_oper_log` VALUES ('78', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '4', 'admin', '2022-10-19 13:47:59');
INSERT INTO `sys_oper_log` VALUES ('79', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '4', 'admin', '2022-10-19 13:48:00');
INSERT INTO `sys_oper_log` VALUES ('80', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '3', 'admin', '2022-10-19 13:48:07');
INSERT INTO `sys_oper_log` VALUES ('81', '用户管理', '5', 'com.asurplus.system.controller.SysUserInfoController.exportXls()', 'GET', '/system/sys-user-info/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '243', 'admin', '2022-10-19 15:30:55');
INSERT INTO `sys_oper_log` VALUES ('82', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may exist in com/asurplus/device/mapper/DevInfoMapper.java (best guess)\r\n### The error may involve com.asurplus.device.mapper.DevInfoMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO dev_info    VALUES\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1', '102', 'admin', '2022-10-27 18:35:51');
INSERT INTO `sys_oper_log` VALUES ('83', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may exist in com/asurplus/device/mapper/DevInfoMapper.java (best guess)\r\n### The error may involve com.asurplus.device.mapper.DevInfoMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO dev_info    VALUES\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1', '73', 'admin', '2022-10-27 18:39:42');
INSERT INTO `sys_oper_log` VALUES ('84', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '52', 'admin', '2022-10-27 18:44:07');
INSERT INTO `sys_oper_log` VALUES ('85', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '45', 'admin', '2022-10-27 19:01:14');
INSERT INTO `sys_oper_log` VALUES ('86', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '7', 'admin', '2022-10-27 19:01:44');
INSERT INTO `sys_oper_log` VALUES ('87', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '6', 'admin', '2022-10-27 19:03:19');
INSERT INTO `sys_oper_log` VALUES ('88', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '66', 'admin', '2022-10-27 19:05:40');
INSERT INTO `sys_oper_log` VALUES ('89', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '7', 'admin', '2022-10-27 19:08:26');
INSERT INTO `sys_oper_log` VALUES ('90', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '8', 'admin', '2022-10-27 19:08:52');
INSERT INTO `sys_oper_log` VALUES ('91', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '7', 'admin', '2022-10-27 19:10:00');
INSERT INTO `sys_oper_log` VALUES ('92', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '6', 'admin', '2022-10-27 19:10:49');
INSERT INTO `sys_oper_log` VALUES ('93', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '53', 'admin', '2022-10-27 19:14:52');
INSERT INTO `sys_oper_log` VALUES ('94', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '2', 'admin', '2022-10-27 19:17:39');
INSERT INTO `sys_oper_log` VALUES ('95', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '15', 'admin', '2022-10-27 19:17:45');
INSERT INTO `sys_oper_log` VALUES ('96', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-27 19:18:37');
INSERT INTO `sys_oper_log` VALUES ('97', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-10-27 19:22:07');
INSERT INTO `sys_oper_log` VALUES ('98', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '21', 'admin', '2022-10-27 19:23:21');
INSERT INTO `sys_oper_log` VALUES ('99', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-27 19:23:46');
INSERT INTO `sys_oper_log` VALUES ('100', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-27 19:23:58');
INSERT INTO `sys_oper_log` VALUES ('101', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-27 19:25:47');
INSERT INTO `sys_oper_log` VALUES ('102', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-27 19:26:02');
INSERT INTO `sys_oper_log` VALUES ('103', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-10-27 19:26:21');
INSERT INTO `sys_oper_log` VALUES ('104', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-27 19:27:01');
INSERT INTO `sys_oper_log` VALUES ('105', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-10-27 19:27:16');
INSERT INTO `sys_oper_log` VALUES ('106', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-10-27 19:32:29');
INSERT INTO `sys_oper_log` VALUES ('107', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-27 19:33:07');
INSERT INTO `sys_oper_log` VALUES ('108', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-10-27 19:33:18');
INSERT INTO `sys_oper_log` VALUES ('109', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '0', 'admin', '2022-10-27 19:35:34');
INSERT INTO `sys_oper_log` VALUES ('110', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '4', 'admin', '2022-10-27 19:35:55');
INSERT INTO `sys_oper_log` VALUES ('111', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '0', 'admin', '2022-10-27 19:35:57');
INSERT INTO `sys_oper_log` VALUES ('112', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '5', 'admin', '2022-10-27 19:35:57');
INSERT INTO `sys_oper_log` VALUES ('113', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '0', 'admin', '2022-10-27 19:35:58');
INSERT INTO `sys_oper_log` VALUES ('114', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":1}', '0', '', '5', 'admin', '2022-10-27 19:35:58');
INSERT INTO `sys_oper_log` VALUES ('115', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '48', 'admin', '2022-10-27 19:47:29');
INSERT INTO `sys_oper_log` VALUES ('116', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-10-27 20:05:40');
INSERT INTO `sys_oper_log` VALUES ('117', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-27 20:10:58');
INSERT INTO `sys_oper_log` VALUES ('118', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"regfse\",\"id\":31,\"locId\":\"1\",\"status\":0}}', '0', '', '50', 'admin', '2022-10-28 11:10:26');
INSERT INTO `sys_oper_log` VALUES ('119', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/32', '127.0.0.1', '内网IP，无法获取位置', '{id=32}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666871250000,\"delFlag\":0,\"devId\":\"er3r443\",\"devName\":\"re\",\"id\":32,\"locId\":\"1\",\"status\":0}}', '0', '', '0', 'admin', '2022-10-28 11:11:24');
INSERT INTO `sys_oper_log` VALUES ('120', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/32', '127.0.0.1', '内网IP，无法获取位置', '{id=32}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666871250000,\"delFlag\":0,\"devId\":\"er3r443\",\"devName\":\"re\",\"id\":32,\"locId\":\"1\",\"status\":0}}', '0', '', '4', 'admin', '2022-10-28 11:11:32');
INSERT INTO `sys_oper_log` VALUES ('121', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"regfse\",\"id\":31,\"locId\":\"1\",\"status\":0}}', '0', '', '4', 'admin', '2022-10-28 11:14:31');
INSERT INTO `sys_oper_log` VALUES ('122', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/1', '127.0.0.1', '内网IP，无法获取位置', '{id=1}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"01\",\"devName\":\"测试设备01\",\"id\":1,\"locId\":\"11\",\"remark\":\"空\",\"status\":0,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '5', 'admin', '2022-10-28 11:15:03');
INSERT INTO `sys_oper_log` VALUES ('123', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/3', '127.0.0.1', '内网IP，无法获取位置', '{id=3}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"03\",\"devName\":\"测试设备03\",\"id\":3,\"locId\":\"11\",\"remark\":\"空\",\"status\":1,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '0', 'admin', '2022-10-28 11:15:35');
INSERT INTO `sys_oper_log` VALUES ('124', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/3', '127.0.0.1', '内网IP，无法获取位置', '{id=3}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"03\",\"devName\":\"测试设备03\",\"id\":3,\"locId\":\"11\",\"remark\":\"空\",\"status\":1,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '4', 'admin', '2022-10-28 11:16:06');
INSERT INTO `sys_oper_log` VALUES ('125', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/1', '127.0.0.1', '内网IP，无法获取位置', '{id=1}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"01\",\"devName\":\"测试设备01\",\"id\":1,\"locId\":\"11\",\"remark\":\"空\",\"status\":0,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '0', 'admin', '2022-10-28 11:22:04');
INSERT INTO `sys_oper_log` VALUES ('126', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/1', '127.0.0.1', '内网IP，无法获取位置', '{id=1}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"01\",\"devName\":\"测试设备01\",\"id\":1,\"locId\":\"11\",\"remark\":\"空\",\"status\":0,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '3', 'admin', '2022-10-28 11:23:32');
INSERT INTO `sys_oper_log` VALUES ('127', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"regfse\",\"id\":31,\"locId\":\"1\",\"status\":0}}', '0', '', '0', 'admin', '2022-10-28 11:23:37');
INSERT INTO `sys_oper_log` VALUES ('128', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"regfse\",\"id\":31,\"locId\":\"1\",\"status\":0}}', '0', '', '3', 'admin', '2022-10-28 11:23:51');
INSERT INTO `sys_oper_log` VALUES ('129', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"regfse\",\"id\":31,\"locId\":\"1\",\"status\":0}}', '0', '', '38', 'admin', '2022-10-28 12:22:41');
INSERT INTO `sys_oper_log` VALUES ('130', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '12', 'admin', '2022-10-28 12:22:48');
INSERT INTO `sys_oper_log` VALUES ('131', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"r4e3tregfrsef\",\"id\":31,\"locId\":\"2\",\"remark\":\"etertgersghreth\",\"status\":0,\"updateUser\":\"\"}}', '0', '', '975', 'admin', '2022-10-28 12:24:07');
INSERT INTO `sys_oper_log` VALUES ('132', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 12:24:13');
INSERT INTO `sys_oper_log` VALUES ('133', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"ceshi1\",\"id\":31,\"locId\":\"2\",\"remark\":\"etertgersghrethfdsefgerg er wer\",\"status\":0,\"updateUser\":\"\"}}', '0', '', '18', 'admin', '2022-10-28 12:25:24');
INSERT INTO `sys_oper_log` VALUES ('134', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"ceshi1\",\"id\":31,\"locId\":\"2\",\"remark\":\"etertgersghrethfdsefgerg er wer\",\"status\":0,\"updateUser\":\"\"}}', '0', '', '10', 'admin', '2022-10-28 12:25:27');
INSERT INTO `sys_oper_log` VALUES ('135', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 12:25:38');
INSERT INTO `sys_oper_log` VALUES ('136', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"ceshi1\",\"id\":31,\"locId\":\"5\",\"remark\":\"\",\"status\":0,\"updateUser\":\"\"}}', '0', '', '0', 'admin', '2022-10-28 12:25:46');
INSERT INTO `sys_oper_log` VALUES ('137', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/31', '127.0.0.1', '内网IP，无法获取位置', '{id=31}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666870558000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"tr4t4re5\",\"devName\":\"ceshi1\",\"id\":31,\"locId\":\"5\",\"remark\":\"\",\"status\":0,\"updateUser\":\"\"}}', '0', '', '4', 'admin', '2022-10-28 12:28:43');
INSERT INTO `sys_oper_log` VALUES ('138', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 12:28:49');
INSERT INTO `sys_oper_log` VALUES ('139', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '16', 'admin', '2022-10-28 12:33:21');
INSERT INTO `sys_oper_log` VALUES ('140', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-10-28 12:35:55');
INSERT INTO `sys_oper_log` VALUES ('141', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/35', '127.0.0.1', '内网IP，无法获取位置', '{id=35}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666931755000,\"delFlag\":0,\"devId\":\"testUpdate\",\"devName\":\"测试更新\",\"id\":35,\"locId\":\"2\",\"status\":1}}', '0', '', '7', 'admin', '2022-10-28 12:35:58');
INSERT INTO `sys_oper_log` VALUES ('142', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 12:36:06');
INSERT INTO `sys_oper_log` VALUES ('143', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/35', '127.0.0.1', '内网IP，无法获取位置', '{id=35}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666931755000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"testUpdate\",\"devName\":\"测试更新\",\"id\":35,\"locId\":\"3\",\"remark\":\"1\",\"status\":1,\"updateUser\":\"\"}}', '0', '', '96', 'admin', '2022-10-28 12:43:15');
INSERT INTO `sys_oper_log` VALUES ('144', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备已经存在\",\"code\":500}', '0', '', '27', 'admin', '2022-10-28 12:43:20');
INSERT INTO `sys_oper_log` VALUES ('145', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/35', '127.0.0.1', '内网IP，无法获取位置', '{id=35}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666931755000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"testUpdate\",\"devName\":\"测试更新\",\"id\":35,\"locId\":\"3\",\"remark\":\"1\",\"status\":1,\"updateUser\":\"\"}}', '0', '', '2', 'admin', '2022-10-28 12:43:34');
INSERT INTO `sys_oper_log` VALUES ('146', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备已经存在\",\"code\":500}', '0', '', '0', 'admin', '2022-10-28 12:43:38');
INSERT INTO `sys_oper_log` VALUES ('147', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/35', '127.0.0.1', '内网IP，无法获取位置', '{id=35}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666931755000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"testUpdate\",\"devName\":\"测试更新\",\"id\":35,\"locId\":\"3\",\"remark\":\"1\",\"status\":1,\"updateUser\":\"\"}}', '0', '', '79', 'admin', '2022-10-28 12:45:35');
INSERT INTO `sys_oper_log` VALUES ('148', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '24', 'admin', '2022-10-28 12:45:41');
INSERT INTO `sys_oper_log` VALUES ('149', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/13', '127.0.0.1', '内网IP，无法获取位置', '{id=13}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666164145000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"13\",\"devName\":\"测试设备0D\",\"id\":13,\"locId\":\"15\",\"remark\":\"空\",\"status\":1,\"updateTime\":1666164149000,\"updateUser\":\"1\"}}', '0', '', '3', 'admin', '2022-10-28 13:03:54');
INSERT INTO `sys_oper_log` VALUES ('150', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/1', '127.0.0.1', '内网IP，无法获取位置', '{id=1}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"01\",\"devName\":\"测试设备01\",\"id\":1,\"locId\":\"11\",\"remark\":\"空\",\"status\":0,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '4', 'admin', '2022-10-28 13:04:31');
INSERT INTO `sys_oper_log` VALUES ('151', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/2', '127.0.0.1', '内网IP，无法获取位置', '{id=2}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"02\",\"devName\":\"测试设备02\",\"id\":2,\"locId\":\"11\",\"remark\":\"空\",\"status\":0,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '4', 'admin', '2022-10-28 13:04:39');
INSERT INTO `sys_oper_log` VALUES ('152', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/35', '127.0.0.1', '内网IP，无法获取位置', '{id=35}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666931755000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"testUpdate\",\"devName\":\"测试更新\",\"id\":35,\"locId\":\"4\",\"remark\":\"1，2\",\"status\":1,\"updateTime\":1666932341000,\"updateUser\":\"\"}}', '0', '', '4', 'admin', '2022-10-28 13:21:19');
INSERT INTO `sys_oper_log` VALUES ('153', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 13:21:23');
INSERT INTO `sys_oper_log` VALUES ('154', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/1', '127.0.0.1', '内网IP，无法获取位置', '{id=1}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"01\",\"devName\":\"测试设备01\",\"id\":1,\"locId\":\"11\",\"remark\":\"空\",\"status\":0,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '3', 'admin', '2022-10-28 13:34:59');
INSERT INTO `sys_oper_log` VALUES ('155', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/35', '127.0.0.1', '内网IP，无法获取位置', '{id=35}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1666931755000,\"createUser\":\"\",\"delFlag\":0,\"devId\":\"testUpdate\",\"devName\":\"测试更新\",\"id\":35,\"locId\":\"4\",\"remark\":\"1，2,4\",\"status\":1,\"updateTime\":1666934483000,\"updateUser\":\"\"}}', '0', '', '0', 'admin', '2022-10-28 13:49:44');
INSERT INTO `sys_oper_log` VALUES ('156', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '40', 'admin', '2022-10-28 14:37:12');
INSERT INTO `sys_oper_log` VALUES ('157', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '73', 'admin', '2022-10-28 14:44:19');
INSERT INTO `sys_oper_log` VALUES ('158', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/1', '127.0.0.1', '内网IP，无法获取位置', '{id=1}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"01\",\"devName\":\"测试设备01\",\"id\":1,\"locId\":\"11\",\"remark\":\"空\",\"status\":0,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '52', 'admin', '2022-10-28 14:46:34');
INSERT INTO `sys_oper_log` VALUES ('159', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/35', '127.0.0.1', '内网IP，无法获取位置', '{ids=35}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '58', 'admin', '2022-10-28 15:06:33');
INSERT INTO `sys_oper_log` VALUES ('160', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/35,36', '127.0.0.1', '内网IP，无法获取位置', '{ids=35,36}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '31', 'admin', '2022-10-28 15:07:47');
INSERT INTO `sys_oper_log` VALUES ('161', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/37', '127.0.0.1', '内网IP，无法获取位置', '{ids=37}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '48', 'admin', '2022-10-28 15:11:30');
INSERT INTO `sys_oper_log` VALUES ('162', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备已经存在\",\"code\":500}', '0', '', '17', 'admin', '2022-10-28 15:12:54');
INSERT INTO `sys_oper_log` VALUES ('163', '设备管理', '0', 'com.asurplus.device.controller.DevInfoController.get()', 'GET', '/device/management/1', '127.0.0.1', '内网IP，无法获取位置', '{id=1}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"createTime\":1665975191000,\"createUser\":\"1\",\"delFlag\":0,\"devId\":\"01\",\"devName\":\"测试设备01\",\"id\":1,\"locId\":\"11\",\"remark\":\"空\",\"status\":0,\"updateTime\":1665975197000,\"updateUser\":\"1\"}}', '0', '', '28', 'admin', '2022-10-28 15:41:22');
INSERT INTO `sys_oper_log` VALUES ('164', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/35,36', '127.0.0.1', '内网IP，无法获取位置', '{ids=35,36}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '64', 'admin', '2022-10-28 17:50:37');
INSERT INTO `sys_oper_log` VALUES ('165', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '22', 'admin', '2022-10-28 17:52:00');
INSERT INTO `sys_oper_log` VALUES ('166', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-10-28 17:52:11');
INSERT INTO `sys_oper_log` VALUES ('167', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/38,39', '127.0.0.1', '内网IP，无法获取位置', '{ids=38,39}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '12', 'admin', '2022-10-28 17:52:17');
INSERT INTO `sys_oper_log` VALUES ('168', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 17:54:04');
INSERT INTO `sys_oper_log` VALUES ('169', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 17:54:14');
INSERT INTO `sys_oper_log` VALUES ('170', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/40,41', '127.0.0.1', '内网IP，无法获取位置', '{ids=40,41}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 17:54:21');
INSERT INTO `sys_oper_log` VALUES ('171', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 18:08:12');
INSERT INTO `sys_oper_log` VALUES ('172', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 18:08:23');
INSERT INTO `sys_oper_log` VALUES ('173', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/42,43', '127.0.0.1', '内网IP，无法获取位置', '{ids=42,43}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:08:31');
INSERT INTO `sys_oper_log` VALUES ('174', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '4', 'admin', '2022-10-28 18:09:52');
INSERT INTO `sys_oper_log` VALUES ('175', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/37,38', '127.0.0.1', '内网IP，无法获取位置', '{ids=37,38}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 18:10:42');
INSERT INTO `sys_oper_log` VALUES ('176', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 18:10:56');
INSERT INTO `sys_oper_log` VALUES ('177', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/39,43,42,41,40', '127.0.0.1', '内网IP，无法获取位置', '{ids=39,43,42,41,40}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:11:14');
INSERT INTO `sys_oper_log` VALUES ('178', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 18:11:24');
INSERT INTO `sys_oper_log` VALUES ('179', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 18:11:28');
INSERT INTO `sys_oper_log` VALUES ('180', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/44,46,45', '127.0.0.1', '内网IP，无法获取位置', '{ids=44,46,45}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 18:11:37');
INSERT INTO `sys_oper_log` VALUES ('181', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 18:13:50');
INSERT INTO `sys_oper_log` VALUES ('182', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 18:13:53');
INSERT INTO `sys_oper_log` VALUES ('183', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 18:13:57');
INSERT INTO `sys_oper_log` VALUES ('184', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/47,48', '127.0.0.1', '内网IP，无法获取位置', '{ids=47,48}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:14:02');
INSERT INTO `sys_oper_log` VALUES ('185', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 18:14:42');
INSERT INTO `sys_oper_log` VALUES ('186', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/49,50', '127.0.0.1', '内网IP，无法获取位置', '{ids=49,50}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 18:14:53');
INSERT INTO `sys_oper_log` VALUES ('187', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 18:17:13');
INSERT INTO `sys_oper_log` VALUES ('188', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/51', '127.0.0.1', '内网IP，无法获取位置', '{ids=51}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:17:20');
INSERT INTO `sys_oper_log` VALUES ('189', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '50', 'admin', '2022-10-28 18:23:57');
INSERT INTO `sys_oper_log` VALUES ('190', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '16', 'admin', '2022-10-28 18:24:00');
INSERT INTO `sys_oper_log` VALUES ('191', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/52,53', '127.0.0.1', '内网IP，无法获取位置', '{ids=52,53}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 18:24:17');
INSERT INTO `sys_oper_log` VALUES ('192', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:29:50');
INSERT INTO `sys_oper_log` VALUES ('193', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:29:54');
INSERT INTO `sys_oper_log` VALUES ('194', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/54,55', '127.0.0.1', '内网IP，无法获取位置', '{ids=54,55}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:30:00');
INSERT INTO `sys_oper_log` VALUES ('195', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/45,46,47,48,49,50,51,52,53,44', '127.0.0.1', '内网IP，无法获取位置', '{ids=45,46,47,48,49,50,51,52,53,44}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '12', 'admin', '2022-10-28 18:30:20');
INSERT INTO `sys_oper_log` VALUES ('196', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '6', 'admin', '2022-10-28 18:30:24');
INSERT INTO `sys_oper_log` VALUES ('197', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '44', 'admin', '2022-10-28 18:49:18');
INSERT INTO `sys_oper_log` VALUES ('198', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/54', '127.0.0.1', '内网IP，无法获取位置', '{ids=54}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:50:04');
INSERT INTO `sys_oper_log` VALUES ('199', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/54', '127.0.0.1', '内网IP，无法获取位置', '{ids=54}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 18:50:14');
INSERT INTO `sys_oper_log` VALUES ('200', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/54,55', '127.0.0.1', '内网IP，无法获取位置', '{ids=54,55}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 18:50:20');
INSERT INTO `sys_oper_log` VALUES ('201', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '3', 'admin', '2022-10-28 19:00:39');
INSERT INTO `sys_oper_log` VALUES ('202', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 19:00:44');
INSERT INTO `sys_oper_log` VALUES ('203', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/56,57', '127.0.0.1', '内网IP，无法获取位置', '{ids=56,57}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:00:51');
INSERT INTO `sys_oper_log` VALUES ('204', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/56', '127.0.0.1', '内网IP，无法获取位置', '{ids=56}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:01:33');
INSERT INTO `sys_oper_log` VALUES ('205', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/57', '127.0.0.1', '内网IP，无法获取位置', '{ids=57}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:01:37');
INSERT INTO `sys_oper_log` VALUES ('206', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-10-28 19:06:52');
INSERT INTO `sys_oper_log` VALUES ('207', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-10-28 19:06:57');
INSERT INTO `sys_oper_log` VALUES ('208', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/57,58,59', '127.0.0.1', '内网IP，无法获取位置', '{ids=57,58,59}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:07:06');
INSERT INTO `sys_oper_log` VALUES ('209', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/57', '127.0.0.1', '内网IP，无法获取位置', '{ids=57}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '16', 'admin', '2022-10-28 19:07:13');
INSERT INTO `sys_oper_log` VALUES ('210', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/58', '127.0.0.1', '内网IP，无法获取位置', '{ids=58}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:07:25');
INSERT INTO `sys_oper_log` VALUES ('211', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/59', '127.0.0.1', '内网IP，无法获取位置', '{ids=59}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:08:59');
INSERT INTO `sys_oper_log` VALUES ('212', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-10-28 19:09:55');
INSERT INTO `sys_oper_log` VALUES ('213', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:10:03');
INSERT INTO `sys_oper_log` VALUES ('214', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/59,60,61', '127.0.0.1', '内网IP，无法获取位置', '{ids=59,60,61}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:10:09');
INSERT INTO `sys_oper_log` VALUES ('215', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/59', '127.0.0.1', '内网IP，无法获取位置', '{ids=59}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:10:16');
INSERT INTO `sys_oper_log` VALUES ('216', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/61', '127.0.0.1', '内网IP，无法获取位置', '{ids=61}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:10:19');
INSERT INTO `sys_oper_log` VALUES ('217', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/60', '127.0.0.1', '内网IP，无法获取位置', '{ids=60}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:10:29');
INSERT INTO `sys_oper_log` VALUES ('218', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:10:57');
INSERT INTO `sys_oper_log` VALUES ('219', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:11:00');
INSERT INTO `sys_oper_log` VALUES ('220', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:11:02');
INSERT INTO `sys_oper_log` VALUES ('221', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/61,64,63,62', '127.0.0.1', '内网IP，无法获取位置', '{ids=61,64,63,62}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:11:18');
INSERT INTO `sys_oper_log` VALUES ('222', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/61', '127.0.0.1', '内网IP，无法获取位置', '{ids=61}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:11:23');
INSERT INTO `sys_oper_log` VALUES ('223', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/62', '127.0.0.1', '内网IP，无法获取位置', '{ids=62}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:11:45');
INSERT INTO `sys_oper_log` VALUES ('224', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/63', '127.0.0.1', '内网IP，无法获取位置', '{ids=63}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:13:25');
INSERT INTO `sys_oper_log` VALUES ('225', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/64', '127.0.0.1', '内网IP，无法获取位置', '{ids=64}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:14:26');
INSERT INTO `sys_oper_log` VALUES ('226', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/64', '127.0.0.1', '内网IP，无法获取位置', '{ids=64}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:14:33');
INSERT INTO `sys_oper_log` VALUES ('227', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:15:22');
INSERT INTO `sys_oper_log` VALUES ('228', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:15:25');
INSERT INTO `sys_oper_log` VALUES ('229', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/65,66', '127.0.0.1', '内网IP，无法获取位置', '{ids=65,66}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:15:31');
INSERT INTO `sys_oper_log` VALUES ('230', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/65', '127.0.0.1', '内网IP，无法获取位置', '{ids=65}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:15:37');
INSERT INTO `sys_oper_log` VALUES ('231', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/66', '127.0.0.1', '内网IP，无法获取位置', '{ids=66}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:15:59');
INSERT INTO `sys_oper_log` VALUES ('232', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:17:11');
INSERT INTO `sys_oper_log` VALUES ('233', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:17:14');
INSERT INTO `sys_oper_log` VALUES ('234', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/67', '127.0.0.1', '内网IP，无法获取位置', '{ids=67}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:17:29');
INSERT INTO `sys_oper_log` VALUES ('235', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/67', '127.0.0.1', '内网IP，无法获取位置', '{ids=67}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:17:37');
INSERT INTO `sys_oper_log` VALUES ('236', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/67', '127.0.0.1', '内网IP，无法获取位置', '{ids=67}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:22:49');
INSERT INTO `sys_oper_log` VALUES ('237', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/67', '127.0.0.1', '内网IP，无法获取位置', '{ids=67}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-10-28 19:22:55');
INSERT INTO `sys_oper_log` VALUES ('238', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/68', '127.0.0.1', '内网IP，无法获取位置', '{ids=68}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-10-28 19:23:06');
INSERT INTO `sys_oper_log` VALUES ('239', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/68', '127.0.0.1', '内网IP，无法获取位置', '{ids=68}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:23:15');
INSERT INTO `sys_oper_log` VALUES ('240', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:35:12');
INSERT INTO `sys_oper_log` VALUES ('241', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:35:19');
INSERT INTO `sys_oper_log` VALUES ('242', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/70', '127.0.0.1', '内网IP，无法获取位置', '{ids=70}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:35:32');
INSERT INTO `sys_oper_log` VALUES ('243', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/69', '127.0.0.1', '内网IP，无法获取位置', '{ids=69}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:35:56');
INSERT INTO `sys_oper_log` VALUES ('244', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/69', '127.0.0.1', '内网IP，无法获取位置', '{ids=69}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:36:01');
INSERT INTO `sys_oper_log` VALUES ('245', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/70', '127.0.0.1', '内网IP，无法获取位置', '{ids=70}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:36:27');
INSERT INTO `sys_oper_log` VALUES ('246', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:36:45');
INSERT INTO `sys_oper_log` VALUES ('247', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:36:48');
INSERT INTO `sys_oper_log` VALUES ('248', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:36:54');
INSERT INTO `sys_oper_log` VALUES ('249', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:36:57');
INSERT INTO `sys_oper_log` VALUES ('250', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/68,69,71,72,73', '127.0.0.1', '内网IP，无法获取位置', '{ids=68,69,71,72,73}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-10-28 19:37:09');
INSERT INTO `sys_oper_log` VALUES ('251', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/74', '127.0.0.1', '内网IP，无法获取位置', '{ids=74}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:37:16');
INSERT INTO `sys_oper_log` VALUES ('252', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/68,74', '127.0.0.1', '内网IP，无法获取位置', '{ids=68,74}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-10-28 19:37:36');
INSERT INTO `sys_oper_log` VALUES ('253', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/71', '127.0.0.1', '内网IP，无法获取位置', '{ids=71}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-10-28 19:37:47');
INSERT INTO `sys_oper_log` VALUES ('254', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/69', '127.0.0.1', '内网IP，无法获取位置', '{ids=69}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '41', 'admin', '2022-11-01 16:07:09');
INSERT INTO `sys_oper_log` VALUES ('255', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/72', '127.0.0.1', '内网IP，无法获取位置', '{ids=72}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '10', 'admin', '2022-11-01 16:07:14');
INSERT INTO `sys_oper_log` VALUES ('256', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/73', '127.0.0.1', '内网IP，无法获取位置', '{ids=73}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-11-01 16:07:19');
INSERT INTO `sys_oper_log` VALUES ('257', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '10', 'admin', '2022-11-01 16:08:56');
INSERT INTO `sys_oper_log` VALUES ('258', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-11-01 16:09:11');
INSERT INTO `sys_oper_log` VALUES ('259', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/75,76', '127.0.0.1', '内网IP，无法获取位置', '{ids=75,76}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-11-01 16:09:18');
INSERT INTO `sys_oper_log` VALUES ('260', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/75', '127.0.0.1', '内网IP，无法获取位置', '{ids=75}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-11-01 16:09:31');
INSERT INTO `sys_oper_log` VALUES ('261', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/76', '127.0.0.1', '内网IP，无法获取位置', '{ids=76}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-11-01 16:09:38');
INSERT INTO `sys_oper_log` VALUES ('262', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '63', 'admin', '2022-11-01 16:17:01');
INSERT INTO `sys_oper_log` VALUES ('263', '用户管理', '5', 'com.asurplus.system.controller.SysUserInfoController.exportXls()', 'GET', '/system/sys-user-info/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '223', 'admin', '2022-11-02 09:55:41');
INSERT INTO `sys_oper_log` VALUES ('264', '用户管理', '5', 'com.asurplus.system.controller.SysUserInfoController.exportXls()', 'GET', '/system/sys-user-info/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '17', 'admin', '2022-11-02 11:04:05');
INSERT INTO `sys_oper_log` VALUES ('265', '用户管理', '5', 'com.asurplus.system.controller.SysUserInfoController.exportXls()', 'GET', '/system/sys-user-info/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '20', 'admin', '2022-11-02 11:09:43');
INSERT INTO `sys_oper_log` VALUES ('266', '用户管理', '5', 'com.asurplus.system.controller.SysUserInfoController.exportXls()', 'GET', '/system/sys-user-info/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '11', 'admin', '2022-11-02 11:10:16');
INSERT INTO `sys_oper_log` VALUES ('267', '用户管理', '5', 'com.asurplus.system.controller.SysUserInfoController.exportXls()', 'GET', '/system/sys-user-info/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '21', 'admin', '2022-11-02 11:13:37');
INSERT INTO `sys_oper_log` VALUES ('268', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '18', 'admin', '2022-11-02 11:14:28');
INSERT INTO `sys_oper_log` VALUES ('269', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '4', 'admin', '2022-11-02 11:14:30');
INSERT INTO `sys_oper_log` VALUES ('270', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '0', 'admin', '2022-11-02 11:14:39');
INSERT INTO `sys_oper_log` VALUES ('271', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '3', 'admin', '2022-11-02 11:14:43');
INSERT INTO `sys_oper_log` VALUES ('272', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-11-02 11:14:46');
INSERT INTO `sys_oper_log` VALUES ('273', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-11-02 11:15:04');
INSERT INTO `sys_oper_log` VALUES ('274', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-11-02 11:16:54');
INSERT INTO `sys_oper_log` VALUES ('275', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '4', 'admin', '2022-11-02 11:17:42');
INSERT INTO `sys_oper_log` VALUES ('276', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '3', 'admin', '2022-11-02 11:17:43');
INSERT INTO `sys_oper_log` VALUES ('277', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该账户已经存在\",\"code\":500}', '0', '', '3', 'admin', '2022-11-02 11:18:17');
INSERT INTO `sys_oper_log` VALUES ('278', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-11-02 11:18:28');
INSERT INTO `sys_oper_log` VALUES ('279', '用户管理', '2', 'com.asurplus.system.controller.SysUserInfoController.update()', 'PUT', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '14', 'admin', '2022-11-02 11:19:48');
INSERT INTO `sys_oper_log` VALUES ('280', '用户管理', '1', 'com.asurplus.system.controller.SysUserInfoController.add()', 'POST', '/system/sys-user-info/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-11-02 11:19:56');
INSERT INTO `sys_oper_log` VALUES ('281', '用户管理', '5', 'com.asurplus.system.controller.SysUserInfoController.exportXls()', 'GET', '/system/sys-user-info/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '23', 'admin', '2022-11-02 11:20:08');
INSERT INTO `sys_oper_log` VALUES ('282', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', 'Excel导出错误', '177', 'admin', '2022-11-02 11:24:57');
INSERT INTO `sys_oper_log` VALUES ('283', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', 'Excel导出错误', '217', 'admin', '2022-11-02 11:27:44');
INSERT INTO `sys_oper_log` VALUES ('284', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '231', 'admin', '2022-11-02 11:57:03');
INSERT INTO `sys_oper_log` VALUES ('285', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '241', 'admin', '2022-11-02 11:59:07');
INSERT INTO `sys_oper_log` VALUES ('286', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '55', 'admin', '2022-11-02 12:03:19');
INSERT INTO `sys_oper_log` VALUES ('287', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '5', 'admin', '2022-11-02 12:04:56');
INSERT INTO `sys_oper_log` VALUES ('288', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '6', 'admin', '2022-11-02 12:08:38');
INSERT INTO `sys_oper_log` VALUES ('289', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '5', 'admin', '2022-11-02 12:08:49');
INSERT INTO `sys_oper_log` VALUES ('290', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '4', 'admin', '2022-11-02 12:09:04');
INSERT INTO `sys_oper_log` VALUES ('291', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '4', 'admin', '2022-11-02 12:09:16');
INSERT INTO `sys_oper_log` VALUES ('292', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '0', 'admin', '2022-11-02 12:11:23');
INSERT INTO `sys_oper_log` VALUES ('293', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '16', 'admin', '2022-11-02 12:12:09');
INSERT INTO `sys_oper_log` VALUES ('294', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '3', 'admin', '2022-11-02 12:12:24');
INSERT INTO `sys_oper_log` VALUES ('295', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '4', 'admin', '2022-11-02 12:12:46');
INSERT INTO `sys_oper_log` VALUES ('296', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '15', 'admin', '2022-11-02 12:14:09');
INSERT INTO `sys_oper_log` VALUES ('297', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '4', 'admin', '2022-11-02 12:16:59');
INSERT INTO `sys_oper_log` VALUES ('298', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '4', 'admin', '2022-11-02 12:18:16');
INSERT INTO `sys_oper_log` VALUES ('299', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '4', 'admin', '2022-11-02 12:20:22');
INSERT INTO `sys_oper_log` VALUES ('300', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '3', 'admin', '2022-11-02 12:20:29');
INSERT INTO `sys_oper_log` VALUES ('301', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"该设备不存在\",\"code\":500}', '0', '', '51', 'admin', '2022-11-02 12:23:53');
INSERT INTO `sys_oper_log` VALUES ('302', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '56', 'admin', '2022-11-02 12:27:00');
INSERT INTO `sys_oper_log` VALUES ('303', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-11-02 12:27:41');
INSERT INTO `sys_oper_log` VALUES ('304', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-11-02 12:28:38');
INSERT INTO `sys_oper_log` VALUES ('305', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-11-02 12:28:43');
INSERT INTO `sys_oper_log` VALUES ('306', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '0', 'admin', '2022-11-02 12:32:21');
INSERT INTO `sys_oper_log` VALUES ('307', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '131', 'admin', '2022-11-02 12:32:29');
INSERT INTO `sys_oper_log` VALUES ('308', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '7', 'admin', '2022-11-02 12:32:58');
INSERT INTO `sys_oper_log` VALUES ('309', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '12', 'admin', '2022-11-02 12:33:16');
INSERT INTO `sys_oper_log` VALUES ('310', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '176', 'admin', '2022-11-02 12:35:00');
INSERT INTO `sys_oper_log` VALUES ('311', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '13', 'admin', '2022-11-02 12:35:52');
INSERT INTO `sys_oper_log` VALUES ('312', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '16', 'admin', '2022-11-02 12:36:32');
INSERT INTO `sys_oper_log` VALUES ('313', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '231', 'admin', '2022-11-02 12:43:39');
INSERT INTO `sys_oper_log` VALUES ('314', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', 'Excel导出错误', '282', 'admin', '2022-11-02 12:45:15');
INSERT INTO `sys_oper_log` VALUES ('315', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '238', 'admin', '2022-11-02 12:49:38');
INSERT INTO `sys_oper_log` VALUES ('316', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '17', 'admin', '2022-11-02 12:49:51');
INSERT INTO `sys_oper_log` VALUES ('317', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '168', 'admin', '2022-11-02 12:54:29');
INSERT INTO `sys_oper_log` VALUES ('318', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '15', 'admin', '2022-11-02 12:54:35');
INSERT INTO `sys_oper_log` VALUES ('319', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '11', 'admin', '2022-11-02 12:54:50');
INSERT INTO `sys_oper_log` VALUES ('320', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '10', 'admin', '2022-11-02 12:58:40');
INSERT INTO `sys_oper_log` VALUES ('321', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '12', 'admin', '2022-11-02 12:59:12');
INSERT INTO `sys_oper_log` VALUES ('322', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '9', 'admin', '2022-11-02 13:00:01');
INSERT INTO `sys_oper_log` VALUES ('323', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '9', 'admin', '2022-11-02 13:13:13');
INSERT INTO `sys_oper_log` VALUES ('324', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '6', 'admin', '2022-11-02 13:13:18');
INSERT INTO `sys_oper_log` VALUES ('325', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '9', 'admin', '2022-11-02 13:26:39');
INSERT INTO `sys_oper_log` VALUES ('326', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '7', 'admin', '2022-11-02 14:22:59');
INSERT INTO `sys_oper_log` VALUES ('327', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/68,69,74,75,77', '127.0.0.1', '内网IP，无法获取位置', '{ids=68,69,74,75,77}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '17', 'admin', '2022-11-02 14:24:16');
INSERT INTO `sys_oper_log` VALUES ('328', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/78', '127.0.0.1', '内网IP，无法获取位置', '{ids=78}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '6', 'admin', '2022-11-02 14:24:20');
INSERT INTO `sys_oper_log` VALUES ('329', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '6', 'admin', '2022-11-02 14:24:33');
INSERT INTO `sys_oper_log` VALUES ('330', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '10', 'admin', '2022-11-02 14:25:11');
INSERT INTO `sys_oper_log` VALUES ('331', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '51', 'admin', '2022-11-02 14:55:58');
INSERT INTO `sys_oper_log` VALUES ('332', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '16', 'admin', '2022-11-02 15:05:41');
INSERT INTO `sys_oper_log` VALUES ('333', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', '', '43', 'admin', '2022-11-02 16:36:26');
INSERT INTO `sys_oper_log` VALUES ('334', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '190', 'admin', '2022-11-02 16:46:31');
INSERT INTO `sys_oper_log` VALUES ('335', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '170', 'admin', '2022-11-02 16:49:53');
INSERT INTO `sys_oper_log` VALUES ('336', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '25', 'admin', '2022-11-02 16:50:12');
INSERT INTO `sys_oper_log` VALUES ('337', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '24', 'admin', '2022-11-02 16:51:05');
INSERT INTO `sys_oper_log` VALUES ('338', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '24', 'admin', '2022-11-02 16:51:34');
INSERT INTO `sys_oper_log` VALUES ('339', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '21', 'admin', '2022-11-02 16:53:57');
INSERT INTO `sys_oper_log` VALUES ('340', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '23', 'admin', '2022-11-02 16:54:06');
INSERT INTO `sys_oper_log` VALUES ('341', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '22', 'admin', '2022-11-02 16:54:32');
INSERT INTO `sys_oper_log` VALUES ('342', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '20', 'admin', '2022-11-02 16:54:41');
INSERT INTO `sys_oper_log` VALUES ('343', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '170', 'admin', '2022-11-02 16:55:59');
INSERT INTO `sys_oper_log` VALUES ('344', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '43', 'admin', '2022-11-02 17:04:17');
INSERT INTO `sys_oper_log` VALUES ('345', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '62', 'admin', '2022-11-02 17:12:58');
INSERT INTO `sys_oper_log` VALUES ('346', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '56', 'admin', '2022-11-02 17:14:52');
INSERT INTO `sys_oper_log` VALUES ('347', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '208', 'admin', '2022-11-02 17:16:26');
INSERT INTO `sys_oper_log` VALUES ('348', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '171', 'admin', '2022-11-02 21:45:38');
INSERT INTO `sys_oper_log` VALUES ('349', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '203', 'admin', '2022-11-03 09:39:42');
INSERT INTO `sys_oper_log` VALUES ('350', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', 'java.lang.String cannot be cast to java.util.Date', '21', 'admin', '2022-11-03 09:47:27');
INSERT INTO `sys_oper_log` VALUES ('351', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', '', '167', 'admin', '2022-11-03 09:55:02');
INSERT INTO `sys_oper_log` VALUES ('352', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '66', 'admin', '2022-11-03 09:57:44');
INSERT INTO `sys_oper_log` VALUES ('353', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', 'For input string: \"0x10\"', '18', 'admin', '2022-11-03 10:02:09');
INSERT INTO `sys_oper_log` VALUES ('354', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '1', 'For input string: \"0x10\"', '156', 'admin', '2022-11-03 10:03:34');
INSERT INTO `sys_oper_log` VALUES ('355', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '\"\"', '0', '', '276', 'admin', '2022-11-03 10:04:36');
INSERT INTO `sys_oper_log` VALUES ('356', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '168', 'admin', '2022-11-03 11:11:25');
INSERT INTO `sys_oper_log` VALUES ('357', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '33', 'admin', '2022-11-03 11:13:16');
INSERT INTO `sys_oper_log` VALUES ('358', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '216', 'admin', '2022-11-03 11:19:07');
INSERT INTO `sys_oper_log` VALUES ('359', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/88,89,90,91,92,93', '127.0.0.1', '内网IP，无法获取位置', '{ids=88,89,90,91,92,93}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '45', 'admin', '2022-11-03 11:52:11');
INSERT INTO `sys_oper_log` VALUES ('360', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '184', 'admin', '2022-11-03 11:56:43');
INSERT INTO `sys_oper_log` VALUES ('361', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '25', 'admin', '2022-11-03 11:57:19');
INSERT INTO `sys_oper_log` VALUES ('362', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/11,12,13,14,15', '127.0.0.1', '内网IP，无法获取位置', '{ids=11,12,13,14,15}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '12', 'admin', '2022-11-03 11:57:34');
INSERT INTO `sys_oper_log` VALUES ('363', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '30', 'admin', '2022-11-03 11:57:51');
INSERT INTO `sys_oper_log` VALUES ('364', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '58', 'admin', '2022-11-03 11:58:30');
INSERT INTO `sys_oper_log` VALUES ('365', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/11,12,13,14,15', '127.0.0.1', '内网IP，无法获取位置', '{ids=11,12,13,14,15}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-11-03 12:02:53');
INSERT INTO `sys_oper_log` VALUES ('366', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '45', 'admin', '2022-11-03 12:03:32');
INSERT INTO `sys_oper_log` VALUES ('367', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/11,12,13,14,15', '127.0.0.1', '内网IP，无法获取位置', '{ids=11,12,13,14,15}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-11-03 12:03:41');
INSERT INTO `sys_oper_log` VALUES ('368', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/11,12,13,14,15', '127.0.0.1', '内网IP，无法获取位置', '{ids=11,12,13,14,15}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-11-03 12:03:45');
INSERT INTO `sys_oper_log` VALUES ('369', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '46', 'admin', '2022-11-03 12:04:14');
INSERT INTO `sys_oper_log` VALUES ('370', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '63', 'admin', '2022-11-03 12:07:37');
INSERT INTO `sys_oper_log` VALUES ('371', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '41', 'admin', '2022-11-03 12:10:50');
INSERT INTO `sys_oper_log` VALUES ('372', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '46', 'admin', '2022-11-03 12:11:51');
INSERT INTO `sys_oper_log` VALUES ('373', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '21', 'admin', '2022-11-03 12:12:12');
INSERT INTO `sys_oper_log` VALUES ('374', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '176', 'admin', '2022-11-03 12:13:41');
INSERT INTO `sys_oper_log` VALUES ('375', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '75', 'admin', '2022-11-03 12:15:00');
INSERT INTO `sys_oper_log` VALUES ('376', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '244', 'admin', '2022-11-03 12:16:25');
INSERT INTO `sys_oper_log` VALUES ('377', '设备管理', '1', 'com.asurplus.device.controller.DevInfoController.add()', 'POST', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '69', 'admin', '2022-11-03 12:44:42');
INSERT INTO `sys_oper_log` VALUES ('378', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/21', '127.0.0.1', '内网IP，无法获取位置', '{ids=21}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '10', 'admin', '2022-11-03 12:44:49');
INSERT INTO `sys_oper_log` VALUES ('379', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '159', 'admin', '2022-11-03 12:44:55');
INSERT INTO `sys_oper_log` VALUES ('380', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/16,17,18,19,20', '127.0.0.1', '内网IP，无法获取位置', '{ids=16,17,18,19,20}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '13', 'admin', '2022-11-03 12:45:05');
INSERT INTO `sys_oper_log` VALUES ('381', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/1,2,3,4,5,6,7,8,9,10', '127.0.0.1', '内网IP，无法获取位置', '{ids=1,2,3,4,5,6,7,8,9,10}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '14', 'admin', '2022-11-03 12:46:31');
INSERT INTO `sys_oper_log` VALUES ('382', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/1,2,3,4,5,6,7,8,9,10', '127.0.0.1', '内网IP，无法获取位置', '{ids=1,2,3,4,5,6,7,8,9,10}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-11-03 12:46:35');
INSERT INTO `sys_oper_log` VALUES ('383', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/16,17,18,19,20,21', '127.0.0.1', '内网IP，无法获取位置', '{ids=16,17,18,19,20,21}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-11-03 12:46:39');
INSERT INTO `sys_oper_log` VALUES ('384', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '55', 'admin', '2022-11-03 12:46:53');
INSERT INTO `sys_oper_log` VALUES ('385', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '94', 'admin', '2022-11-03 12:51:18');
INSERT INTO `sys_oper_log` VALUES ('386', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/22,23,24,25,26,27,28,29,30,31', '127.0.0.1', '内网IP，无法获取位置', '{ids=22,23,24,25,26,27,28,29,30,31}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '13', 'admin', '2022-11-03 12:53:39');
INSERT INTO `sys_oper_log` VALUES ('387', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/32,33,34,35,36', '127.0.0.1', '内网IP，无法获取位置', '{ids=32,33,34,35,36}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '7', 'admin', '2022-11-03 12:53:44');
INSERT INTO `sys_oper_log` VALUES ('388', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '33', 'admin', '2022-11-03 12:54:08');
INSERT INTO `sys_oper_log` VALUES ('389', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/22,23,24,25,26,27,28,29,30,31', '127.0.0.1', '内网IP，无法获取位置', '{ids=22,23,24,25,26,27,28,29,30,31}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '5', 'admin', '2022-11-03 12:54:34');
INSERT INTO `sys_oper_log` VALUES ('390', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/32,33,34,35,36', '127.0.0.1', '内网IP，无法获取位置', '{ids=32,33,34,35,36}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-11-03 12:54:37');
INSERT INTO `sys_oper_log` VALUES ('391', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '44', 'admin', '2022-11-03 12:54:58');
INSERT INTO `sys_oper_log` VALUES ('392', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/37,38,39,40,41,42,43,44,45,46', '127.0.0.1', '内网IP，无法获取位置', '{ids=37,38,39,40,41,42,43,44,45,46}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '10', 'admin', '2022-11-03 13:00:01');
INSERT INTO `sys_oper_log` VALUES ('393', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/47,48,49,50,51', '127.0.0.1', '内网IP，无法获取位置', '{ids=47,48,49,50,51}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '10', 'admin', '2022-11-03 13:00:04');
INSERT INTO `sys_oper_log` VALUES ('394', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/37,38,39,40,41,42,43,44,45,46,47,48,49,50,51', '127.0.0.1', '内网IP，无法获取位置', '{ids=37,38,39,40,41,42,43,44,45,46,47,48,49,50,51}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '4', 'admin', '2022-11-03 13:00:20');
INSERT INTO `sys_oper_log` VALUES ('395', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '45', 'admin', '2022-11-03 13:00:31');
INSERT INTO `sys_oper_log` VALUES ('396', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '215', 'admin', '2022-11-03 13:03:43');
INSERT INTO `sys_oper_log` VALUES ('397', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.update()', 'PUT', '/device/management/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '15', 'admin', '2022-11-03 13:03:59');
INSERT INTO `sys_oper_log` VALUES ('398', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '20', 'admin', '2022-11-03 13:06:33');
INSERT INTO `sys_oper_log` VALUES ('399', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '262', 'admin', '2022-11-04 13:11:53');
INSERT INTO `sys_oper_log` VALUES ('400', '设备管理', '6', 'com.asurplus.device.controller.DevInfoController.importData()', 'POST', '/device/management/importData', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '80', 'admin', '2022-11-04 13:12:09');
INSERT INTO `sys_oper_log` VALUES ('401', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.delete()', 'DELETE', '/device/management/82,83,84,85,86,87', '127.0.0.1', '内网IP，无法获取位置', '{ids=82,83,84,85,86,87}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-11-04 13:12:26');
INSERT INTO `sys_oper_log` VALUES ('402', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/82,83,84,85,86', '127.0.0.1', '内网IP，无法获取位置', '{ids=82,83,84,85,86}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '16', 'admin', '2022-11-04 13:12:36');
INSERT INTO `sys_oper_log` VALUES ('403', '设备管理', '3', 'com.asurplus.device.controller.DevInfoController.remove()', 'DELETE', '/device/management/bin/87', '127.0.0.1', '内网IP，无法获取位置', '{ids=87}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '13', 'admin', '2022-11-04 13:12:40');
INSERT INTO `sys_oper_log` VALUES ('404', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/67,68,69,70,71,72,73,74,75,76', '127.0.0.1', '内网IP，无法获取位置', '{ids=67,68,69,70,71,72,73,74,75,76}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '10', 'admin', '2022-11-04 13:12:55');
INSERT INTO `sys_oper_log` VALUES ('405', '设备管理', '2', 'com.asurplus.device.controller.DevInfoController.restore()', 'GET', '/device/management/bin/77,78,79,80,81', '127.0.0.1', '内网IP，无法获取位置', '{ids=77,78,79,80,81}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '8', 'admin', '2022-11-04 13:12:59');
INSERT INTO `sys_oper_log` VALUES ('406', '网关配置', '2', 'com.asurplus.gateway.controller.GatewayInfoController.update()', 'PUT', '/config/gateway/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '59', 'admin', '2022-11-04 16:48:48');
INSERT INTO `sys_oper_log` VALUES ('407', '网关配置', '1', 'com.asurplus.gateway.controller.GatewayInfoController.add()', 'POST', '/config/gateway/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '11', 'admin', '2022-11-04 16:49:31');
INSERT INTO `sys_oper_log` VALUES ('408', '网关配置', '1', 'com.asurplus.gateway.controller.GatewayInfoController.add()', 'POST', '/config/gateway/', '127.0.0.1', '内网IP，无法获取位置', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '9', 'admin', '2022-11-04 16:49:37');
INSERT INTO `sys_oper_log` VALUES ('409', '网关配置', '3', 'com.asurplus.gateway.controller.GatewayInfoController.delete()', 'DELETE', '/config/gateway/5', '127.0.0.1', '内网IP，无法获取位置', '{ids=5}', '{\"msg\":\"操作成功\",\"code\":200}', '0', '', '11', 'admin', '2022-11-04 16:49:42');
INSERT INTO `sys_oper_log` VALUES ('410', '参数配置', '5', 'com.asurplus.config.controller.SysParamController.export()', 'GET', '/config/sys-param/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '177', 'admin', '2022-11-04 16:49:48');
INSERT INTO `sys_oper_log` VALUES ('411', '设备管理', '5', 'com.asurplus.device.controller.DevInfoController.exportXls()', 'GET', '/device/management/export', '127.0.0.1', '内网IP，无法获取位置', '{}', 'null', '0', '', '11', 'admin', '2022-11-04 16:50:40');

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数名称',
  `param_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键名',
  `param_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键值',
  `type` tinyint DEFAULT '0' COMMENT '系统内置（0-是 1-否）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='系统参数配置表';

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES ('1', '用户默认密码', 'user.default.password', '123456', '0', '新用户的默认登录密码', '1', '2021-07-26 17:18:30', '1', '2021-07-26 17:18:30', '0');

-- ----------------------------
-- Table structure for sys_permission_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_info`;
CREATE TABLE `sys_permission_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `pid` int DEFAULT '0' COMMENT '父菜单ID',
  `sort` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `is_frame` tinyint DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` tinyint DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` tinyint DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `sign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=65647 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_permission_info
-- ----------------------------
INSERT INTO `sys_permission_info` VALUES ('1', '系统管理', '0', '1', 'system', null, '1', '0', 'M', '0', null, 'system', '系统管理目录', '1', '2022-09-08 17:20:36', '1', '2022-09-08 17:21:03', '0');
INSERT INTO `sys_permission_info` VALUES ('2', '系统监控', '0', '2', 'monitor', '', '1', '0', 'M', '0', '', 'monitor', '系统监控目录', '1', '2021-07-18 13:22:21', '1', '2021-07-18 13:32:10', '0');
INSERT INTO `sys_permission_info` VALUES ('3', '系统配置', '0', '3', 'config', '', '1', '0', 'M', '0', '', 'code', '', '1', '2021-07-26 16:40:07', '0', '2021-07-26 16:50:12', '0');
INSERT INTO `sys_permission_info` VALUES ('4', '系统工具', '0', '4', 'tool', '', '1', '0', 'M', '0', '', 'tool', '系统工具目录', '1', '2021-07-18 13:22:21', '1', '2021-07-26 16:50:11', '0');
INSERT INTO `sys_permission_info` VALUES ('5', '设备管理', '0', '0', 'devmanage', null, '1', '0', 'M', '0', null, 'tree', '所有设备管理', '1', '2022-10-17 10:13:00', '0', '2022-10-17 14:28:53', '0');
INSERT INTO `sys_permission_info` VALUES ('100', '用户管理', '1', '1', 'user', 'system/user/index', '1', '0', 'C', '0', 'system:user:list', 'user', '用户管理菜单', '0', '2021-07-20 21:01:21', '0', '2021-07-20 21:08:03', '0');
INSERT INTO `sys_permission_info` VALUES ('101', '角色管理', '1', '2', 'role', 'system/role/index', '1', '0', 'C', '0', 'system:role:list', 'peoples', '角色管理菜单', '0', '2021-07-20 21:01:24', '0', '2021-07-20 21:08:04', '0');
INSERT INTO `sys_permission_info` VALUES ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '1', '0', 'C', '0', 'system:menu:list', 'tree-table', '菜单管理菜单', '0', '2021-07-20 21:01:25', '0', '2021-07-20 21:08:05', '0');
INSERT INTO `sys_permission_info` VALUES ('103', '部门管理', '1', '4', 'dept', 'system/dept/index', '1', '0', 'C', '0', 'system:dept:list', 'tree', '部门管理菜单', '0', '2021-07-20 21:01:29', '0', '2021-07-20 21:08:06', '0');
INSERT INTO `sys_permission_info` VALUES ('104', '字典管理', '1', '5', 'dict', 'system/dict/index', '1', '0', 'C', '0', 'system:dict:list', 'dict', '字典管理菜单', '0', '2021-07-20 21:01:27', '0', '2021-07-20 21:08:07', '0');
INSERT INTO `sys_permission_info` VALUES ('200', '登录日志', '2', '1', 'logininfo', 'monitor/logininfor/index', '1', '0', 'C', '0', 'monitor:logininfor:list', 'logininfor', '登录日志菜单', '0', '2021-07-20 21:19:43', '0', '2021-07-20 21:21:30', '0');
INSERT INTO `sys_permission_info` VALUES ('201', '操作日志', '2', '2', 'operlog', 'monitor/operlog/index', '1', '0', 'C', '0', 'monitor:operlog:list', 'form', '操作日志菜单', '0', '2021-07-20 21:19:50', '0', '2021-07-20 21:21:35', '0');
INSERT INTO `sys_permission_info` VALUES ('202', '数据监控', '2', '3', 'druid', 'monitor/druid/index', '1', '0', 'C', '0', 'monitor:druid:list', 'druid', '数据监控菜单', '0', '2021-07-20 21:33:47', '0', '2021-07-20 21:37:38', '0');
INSERT INTO `sys_permission_info` VALUES ('203', '定时任务', '2', '4', 'job', 'monitor/job/index', '1', '0', 'C', '0', 'monitor:job:list', 'job', '定时任务菜单', '0', '2021-07-20 21:29:19', '0', '2021-07-20 21:33:57', '0');
INSERT INTO `sys_permission_info` VALUES ('204', '服务监控', '2', '5', 'server', 'monitor/server/index', '1', '0', 'C', '0', 'monitor:server:list', 'server', '服务监控菜单', '0', '2021-07-20 21:32:51', '0', '2021-07-20 21:36:04', '0');
INSERT INTO `sys_permission_info` VALUES ('205', '缓存监控', '2', '6', 'cache', 'monitor/cache/index', '1', '0', 'C', '0', 'monitor:cache:list', 'redis', '缓存监控菜单', '0', '2021-07-20 21:33:01', '0', '2021-07-20 21:36:45', '0');
INSERT INTO `sys_permission_info` VALUES ('300', '表单构建', '4', '1', 'build', 'tool/build/index', '1', '0', 'C', '0', 'tool:build:list', 'build', '表单构建菜单', '0', '2021-07-20 21:39:47', '0', '2021-07-26 16:50:26', '0');
INSERT INTO `sys_permission_info` VALUES ('301', '代码生成', '4', '2', 'gen', 'tool/gen/index', '1', '0', 'C', '0', 'tool:gen:list', 'code', '代码生成菜单', '0', '2021-07-20 21:39:58', '0', '2021-07-26 16:50:26', '0');
INSERT INTO `sys_permission_info` VALUES ('302', '接口文档', '4', '3', 'swagger', 'tool/swagger/index', '1', '0', 'C', '0', 'tool:swagger:list', 'swagger', '接口文档菜单', '0', '2021-07-20 21:40:12', '0', '2021-07-26 16:50:26', '0');
INSERT INTO `sys_permission_info` VALUES ('400', '参数配置', '3', '2', 'param', 'config/param/index', '1', '0', 'C', '0', 'config:param:list', 'log', '参数配置菜单', '0', '2021-07-20 21:01:21', '1', '2022-09-22 10:24:21', '0');
INSERT INTO `sys_permission_info` VALUES ('401', 'APP版本', '3', '2', 'app', 'config/app/index', '1', '0', 'C', '0', 'config:app:list', 'phone', 'APP版本菜单', '0', '2021-07-20 21:01:21', '1', '2021-07-26 21:59:10', '0');
INSERT INTO `sys_permission_info` VALUES ('402', '网关配置', '3', '1', 'gateway', 'config/gateway/index', '1', '0', 'C', '0', 'config:gateway:list', 'online', '网关配置菜单', '0', '2021-07-20 21:40:12', '1', '2022-09-22 10:24:16', '0');
INSERT INTO `sys_permission_info` VALUES ('403', '设备配置', '3', '1', 'device', 'config/device/index', '1', '0', 'C', '0', 'config:device:list', 'switch', '设备配置菜单', '0', '2021-07-20 21:40:12', '1', '2022-09-22 10:24:19', '0');
INSERT INTO `sys_permission_info` VALUES ('601', '表格', '6', '1', 'table', 'show/table', '1', '0', 'C', '0', null, 'table', '表格显示', '0', '2022-08-11 20:16:29', '0', '2022-09-22 10:10:00', '1');
INSERT INTO `sys_permission_info` VALUES ('602', '折线图', '6', '2', 'chart', 'show.chart', '1', '0', 'C', '0', null, 'chart', '折线图', '0', '2022-08-11 20:22:29', '0', '2022-09-22 10:10:02', '1');
INSERT INTO `sys_permission_info` VALUES ('10000', '查询', '100', '1', '', null, '1', '0', 'F', '0', 'system:user:query', '#', '用户查询', '0', '2021-07-20 21:08:03', '0', '2021-07-20 21:24:31', '0');
INSERT INTO `sys_permission_info` VALUES ('10001', '新增', '100', '2', '', null, '1', '0', 'F', '0', 'system:user:add', '#', '', '0', '2021-07-20 21:09:03', '0', '2021-07-20 21:24:35', '0');
INSERT INTO `sys_permission_info` VALUES ('10002', '修改', '100', '3', '', '', '1', '0', 'F', '0', 'system:user:edit', '#', '', '0', '2021-07-20 21:09:19', '1', '2021-07-20 21:24:41', '0');
INSERT INTO `sys_permission_info` VALUES ('10003', '删除', '100', '4', '', null, '1', '0', 'F', '0', 'system:user:del', '#', '', '0', '2021-07-20 21:09:48', '0', '2021-07-20 21:24:45', '0');
INSERT INTO `sys_permission_info` VALUES ('10004', '重置密码', '100', '5', '', null, '1', '0', 'F', '0', 'system:user:reset', '#', '', '0', '2021-07-20 21:10:15', '0', '2021-07-20 21:24:48', '0');
INSERT INTO `sys_permission_info` VALUES ('10005', '导入', '100', '6', '', null, '1', '0', 'F', '0', 'system:user:import', '#', '', '0', '2021-07-20 21:10:25', '0', '2021-07-20 21:24:51', '0');
INSERT INTO `sys_permission_info` VALUES ('10006', '导出', '100', '7', '', null, '1', '0', 'F', '0', 'system:user:export', '#', '', '0', '2021-07-20 21:10:32', '0', '2021-07-20 21:24:55', '0');
INSERT INTO `sys_permission_info` VALUES ('10100', '查询', '101', '1', '', null, '1', '0', 'F', '0', 'system:role:query', '#', '角色查询', '0', '2021-07-20 21:12:19', '0', '2021-07-20 21:25:00', '0');
INSERT INTO `sys_permission_info` VALUES ('10101', '新增', '101', '2', '', null, '1', '0', 'F', '0', 'system:role:add', '#', '', '0', '2021-07-20 21:12:27', '0', '2021-07-20 21:25:03', '0');
INSERT INTO `sys_permission_info` VALUES ('10102', '修改', '101', '3', '', null, '1', '0', 'F', '0', 'system:role:edit', '#', '', '0', '2021-07-20 21:12:30', '0', '2021-07-20 21:25:10', '0');
INSERT INTO `sys_permission_info` VALUES ('10103', '删除', '101', '4', '', null, '1', '0', 'F', '0', 'system:role:del', '#', '', '0', '2021-07-20 21:12:43', '0', '2021-07-20 21:25:13', '0');
INSERT INTO `sys_permission_info` VALUES ('10104', '导出', '101', '5', '', null, '1', '0', 'F', '0', 'system:role:export', '#', '', '0', '2021-07-20 21:12:52', '0', '2021-07-20 21:25:16', '0');
INSERT INTO `sys_permission_info` VALUES ('10200', '查询', '102', '1', '', null, '1', '0', 'F', '0', 'system:menu:query', '#', '菜单查询', '0', '2021-07-20 21:13:49', '0', '2021-07-20 21:25:22', '0');
INSERT INTO `sys_permission_info` VALUES ('10201', '新增', '102', '2', '', null, '1', '0', 'F', '0', 'system:menu:add', '#', '', '0', '2021-07-20 21:13:51', '0', '2021-07-20 21:25:25', '0');
INSERT INTO `sys_permission_info` VALUES ('10202', '修改', '102', '3', '', null, '1', '0', 'F', '0', 'system:menu:edit', '#', '', '0', '2021-07-20 21:13:53', '0', '2021-07-20 21:25:29', '0');
INSERT INTO `sys_permission_info` VALUES ('10203', '删除', '102', '4', '', null, '1', '0', 'F', '0', 'system:menu:del', '#', '', '0', '2021-07-20 21:13:59', '0', '2021-07-20 21:25:32', '0');
INSERT INTO `sys_permission_info` VALUES ('10300', '查询', '103', '1', '', null, '1', '0', 'F', '0', 'system:dept:query', '#', '部门查询', '0', '2021-07-20 21:15:40', '0', '2021-07-20 21:25:37', '0');
INSERT INTO `sys_permission_info` VALUES ('10301', '新增', '103', '2', '', null, '1', '0', 'F', '0', 'system:dept:add', '#', '', '0', '2021-07-20 21:15:45', '0', '2021-07-20 21:25:40', '0');
INSERT INTO `sys_permission_info` VALUES ('10302', '修改', '103', '3', '', null, '1', '0', 'F', '0', 'system:dept:edit', '#', '', '0', '2021-07-20 21:15:47', '0', '2021-07-20 21:25:43', '0');
INSERT INTO `sys_permission_info` VALUES ('10303', '删除', '103', '4', '', null, '1', '0', 'F', '0', 'system:dept:del', '#', '', '0', '2021-07-20 21:15:51', '0', '2021-07-20 21:25:46', '0');
INSERT INTO `sys_permission_info` VALUES ('10400', '查询', '104', '1', '', null, '1', '0', 'F', '0', 'system:dict:query', '#', '字典查询', '0', '2021-07-20 21:16:54', '0', '2021-07-20 21:25:51', '0');
INSERT INTO `sys_permission_info` VALUES ('10401', '新增', '104', '2', '', null, '1', '0', 'F', '0', 'system:dict:add', '#', '', '0', '2021-07-20 21:17:00', '0', '2021-07-20 21:27:41', '0');
INSERT INTO `sys_permission_info` VALUES ('10402', '修改', '104', '3', '', null, '1', '0', 'F', '0', 'system:dict:edit', '#', '', '0', '2021-07-20 21:17:03', '0', '2021-07-20 21:27:42', '0');
INSERT INTO `sys_permission_info` VALUES ('10403', '删除', '104', '4', '', null, '1', '0', 'F', '0', 'system:dict:del', '#', '', '0', '2021-07-20 21:17:05', '0', '2021-07-20 21:27:43', '0');
INSERT INTO `sys_permission_info` VALUES ('10404', '导出', '104', '5', '', null, '1', '0', 'F', '0', 'system:dict:export', '#', '', '0', '2021-07-20 21:17:15', '0', '2021-07-20 21:27:43', '0');
INSERT INTO `sys_permission_info` VALUES ('20000', '查询', '200', '1', '', null, '1', '0', 'F', '0', 'monitor:logininfor:query', '#', '登录日志查询', '0', '2021-07-20 21:22:43', '0', '2021-07-20 21:27:44', '0');
INSERT INTO `sys_permission_info` VALUES ('20001', '删除', '200', '2', '', null, '1', '0', 'F', '0', 'monitor:logininfor:del', '#', '', '0', '2021-07-20 21:22:47', '0', '2021-07-20 21:27:45', '0');
INSERT INTO `sys_permission_info` VALUES ('20002', '清空', '200', '3', '', null, '1', '0', 'F', '0', 'monitor:logininfor:clear', '#', '', '0', '2021-07-20 21:23:18', '0', '2021-07-20 21:27:46', '0');
INSERT INTO `sys_permission_info` VALUES ('20003', '导出', '200', '4', '', null, '1', '0', 'F', '0', 'monitor:logininfor:export', '#', '', '0', '2021-07-20 21:23:18', '0', '2021-07-20 21:27:46', '0');
INSERT INTO `sys_permission_info` VALUES ('20100', '查询', '201', '1', '', null, '1', '0', 'F', '0', 'monitor:operlog:query', '#', '操作日志查询', '0', '2021-07-20 21:26:44', '0', '2021-07-20 21:27:47', '0');
INSERT INTO `sys_permission_info` VALUES ('20101', '删除', '201', '2', '', null, '1', '0', 'F', '0', 'monitor:operlog:del', '#', '', '0', '2021-07-20 21:26:58', '0', '2021-07-20 21:27:48', '0');
INSERT INTO `sys_permission_info` VALUES ('20102', '清空', '202', '3', '', null, '1', '0', 'F', '0', 'monitor:operlog:clear', '#', '', '0', '2021-07-20 21:27:20', '0', '2021-07-20 21:27:48', '0');
INSERT INTO `sys_permission_info` VALUES ('20103', '导出', '202', '4', '', null, '1', '0', 'F', '0', 'monitor:operlog:export', '#', '', '0', '2021-07-20 21:27:34', '0', '2021-07-20 21:27:50', '0');
INSERT INTO `sys_permission_info` VALUES ('20300', '查询', '203', '1', '', null, '1', '0', 'F', '0', 'monitor:job:query', '#', '定时任务查询', '0', '2021-07-20 21:30:41', '0', '2021-07-20 21:32:09', '0');
INSERT INTO `sys_permission_info` VALUES ('20301', '新增', '203', '2', '', null, '1', '0', 'F', '0', 'monitor:job:add', '#', '', '0', '2021-07-20 21:30:45', '0', '2021-07-20 21:31:44', '0');
INSERT INTO `sys_permission_info` VALUES ('20302', '修改', '203', '3', '', null, '1', '0', 'F', '0', 'monitor:job:edit', '#', '', '0', '2021-07-20 21:30:48', '0', '2021-07-20 21:31:47', '0');
INSERT INTO `sys_permission_info` VALUES ('20303', '删除', '203', '4', '', null, '1', '0', 'F', '0', 'monitor:job:del', '#', '', '0', '2021-07-20 21:30:50', '0', '2021-07-20 21:31:52', '0');
INSERT INTO `sys_permission_info` VALUES ('20304', '启/停止', '203', '5', '', null, '1', '0', 'F', '0', 'monitor:job:status', '#', '', '0', '2021-07-20 21:31:08', '0', '2021-07-20 21:31:56', '0');
INSERT INTO `sys_permission_info` VALUES ('20305', '导出', '203', '6', '', null, '1', '0', 'F', '0', 'monitor:job:export', '#', '', '0', '2021-07-20 21:31:11', '0', '2021-07-20 21:32:01', '0');
INSERT INTO `sys_permission_info` VALUES ('30101', '查询', '301', '1', '', null, '1', '0', 'F', '0', 'tool:gen:query', '#', '代码生成查询', '0', '2021-07-20 21:42:36', '0', '2021-07-20 21:44:29', '0');
INSERT INTO `sys_permission_info` VALUES ('30102', '修改', '301', '2', '', null, '1', '0', 'F', '0', 'tool:gen:edit', '#', '', '0', '2021-07-20 21:42:43', '0', '2021-07-20 21:43:45', '0');
INSERT INTO `sys_permission_info` VALUES ('30103', '删除', '301', '3', '', null, '1', '0', 'F', '0', 'tool:gen:del', '#', '', '0', '2021-07-20 21:42:54', '0', '2021-07-20 21:43:48', '0');
INSERT INTO `sys_permission_info` VALUES ('30104', '导入', '301', '4', '', null, '1', '0', 'F', '0', 'tool:gen:import', '#', '', '0', '2021-07-20 21:42:57', '0', '2021-07-20 21:43:54', '0');
INSERT INTO `sys_permission_info` VALUES ('30105', '预览', '301', '5', '', null, '1', '0', 'F', '0', 'tool:gen:preview', '#', '', '0', '2021-07-20 21:43:02', '0', '2021-07-20 21:44:18', '0');
INSERT INTO `sys_permission_info` VALUES ('30106', '生成', '301', '6', '', null, '1', '0', 'F', '0', 'tool:gen:code', '#', '', '0', '2021-07-20 21:43:08', '0', '2021-07-20 21:44:22', '0');
INSERT INTO `sys_permission_info` VALUES ('40000', '查询', '400', '1', '', null, '1', '0', 'F', '0', 'config:param:query', '#', '参数配置查询', '1', '2021-07-26 16:58:40', '0', '2021-07-26 17:01:04', '0');
INSERT INTO `sys_permission_info` VALUES ('40001', '新增', '400', '2', '', null, '1', '0', 'F', '0', 'config:param:add', '#', '', '1', '2021-07-26 16:58:40', '0', '2021-07-26 16:59:21', '0');
INSERT INTO `sys_permission_info` VALUES ('40002', '修改', '400', '3', '', null, '1', '0', 'F', '0', 'config:param:edit', '#', '', '1', '2021-07-26 16:58:40', '0', '2021-07-26 17:00:46', '0');
INSERT INTO `sys_permission_info` VALUES ('40003', '删除', '400', '4', '', null, '1', '0', 'F', '0', 'config:param:del', '#', '', '1', '2021-07-26 16:58:40', '0', '2021-07-26 17:00:50', '0');
INSERT INTO `sys_permission_info` VALUES ('40004', '导出', '400', '5', '', null, '1', '0', 'F', '0', 'config:param:export', '#', '', '1', '2021-07-26 16:58:40', '0', '2021-07-26 17:00:55', '0');
INSERT INTO `sys_permission_info` VALUES ('40100', '查询', '401', '1', '', null, '1', '0', 'F', '0', 'config:app:query', '#', 'app版本查询', '1', '2021-07-26 16:58:40', '0', '2021-07-26 17:01:04', '0');
INSERT INTO `sys_permission_info` VALUES ('40101', '新增', '401', '2', '', null, '1', '0', 'F', '0', 'config:app:add', '#', 'app版本查询', '1', '2021-07-26 16:58:40', '0', '2021-07-26 21:55:05', '0');
INSERT INTO `sys_permission_info` VALUES ('40102', '修改', '401', '3', '', null, '1', '0', 'F', '0', 'config:app:edit', '#', 'app版本查询', '1', '2021-07-26 16:58:40', '0', '2021-07-26 21:55:23', '0');
INSERT INTO `sys_permission_info` VALUES ('40103', '删除', '401', '4', '', null, '1', '0', 'F', '0', 'config:app:del', '#', 'app版本查询', '1', '2021-07-26 16:58:40', '0', '2021-07-26 21:55:18', '0');
INSERT INTO `sys_permission_info` VALUES ('40104', '导出', '401', '5', '', null, '1', '0', 'F', '0', 'config:app:export', '#', 'app版本查询', '1', '2021-07-26 16:58:40', '0', '2021-07-26 21:55:26', '0');

-- ----------------------------
-- Table structure for sys_quartz_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_info`;
CREATE TABLE `sys_quartz_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务类名',
  `cron_expression` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'cron表达式',
  `param` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数',
  `status` tinyint DEFAULT '0' COMMENT '启动状态（0--启动1--停止）',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `sort` int DEFAULT '0' COMMENT '显示顺序(越大越靠前)',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='定时任务信息表';

-- ----------------------------
-- Records of sys_quartz_info
-- ----------------------------
INSERT INTO `sys_quartz_info` VALUES ('1', 'com.asurplus.monitor.job.TestJob', '0/10 * * * * ?', '', '1', '无参测试', '0', '1', '2021-08-05 22:02:56', '1', '2021-08-05 22:02:56', '0');
INSERT INTO `sys_quartz_info` VALUES ('2', 'com.asurplus.monitor.job.TestParamJob', '0/10 * * * * ?', '{\"test\": \"下午好！\"}', '1', '有参测试', '0', '1', '2021-08-05 22:12:35', '1', '2021-08-05 22:12:35', '0');

-- ----------------------------
-- Table structure for sys_role_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_info`;
CREATE TABLE `sys_role_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `sign` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色标志',
  `remark` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `status` tinyint DEFAULT '0' COMMENT '状态（0--正常1--停用）',
  `sort` int DEFAULT '0' COMMENT '排序（越大越靠前）',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role_info
-- ----------------------------
INSERT INTO `sys_role_info` VALUES ('1', '超级管理员', 'administrator', '超级管理员，拥有至高无上的权力', '0', '3', '0', '2020-09-06 19:40:31', '0', '2022-09-09 17:22:35', '0');
INSERT INTO `sys_role_info` VALUES ('2', '测试人员', 'test', '只有查看数据的权限，无CRUD权限', '0', '2', '0', '2020-09-06 19:40:31', '1', '2022-09-09 17:22:36', '0');
INSERT INTO `sys_role_info` VALUES ('3', '超级赛亚人', 'super', '元气弹', '0', '1', '1', '2022-09-09 17:20:23', '0', '2022-09-09 17:22:39', '0');
INSERT INTO `sys_role_info` VALUES ('4', '33', '33', null, '0', '1', '1', '2022-09-09 17:23:09', '0', '2022-09-09 17:23:09', '0');
INSERT INTO `sys_role_info` VALUES ('5', '测试A', 'b', null, '0', '1', '1', '2022-09-09 17:26:47', '0', '2022-09-09 17:26:47', '0');
INSERT INTO `sys_role_info` VALUES ('6', '测试3', 'r', null, '0', '1', '1', '2022-09-09 17:27:16', '0', '2022-09-09 17:27:16', '0');
INSERT INTO `sys_role_info` VALUES ('7', 'yt', 'fe', null, '0', '1', '1', '2022-09-09 17:27:25', '0', '2022-09-09 17:27:25', '0');
INSERT INTO `sys_role_info` VALUES ('8', 'rtggt', 'tgr', null, '0', '1', '1', '2022-09-09 17:27:34', '0', '2022-09-09 17:27:34', '0');
INSERT INTO `sys_role_info` VALUES ('10', 'fasdfa', 'f', null, '0', '0', '1', '2022-09-09 17:28:00', '0', '2022-09-09 17:28:00', '0');
INSERT INTO `sys_role_info` VALUES ('11', 'fadfef', 'dafdf', null, '0', '1', '1', '2022-09-09 17:28:14', '0', '2022-09-09 17:28:14', '0');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
  `role_id` int DEFAULT '0' COMMENT '角色id',
  `permission_id` int DEFAULT '0' COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='角色-权限关系表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_id` int DEFAULT '0' COMMENT '所属部门',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录账号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `sex` tinyint DEFAULT '0' COMMENT '性别（0--未知1--男2--女）',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `status` tinyint DEFAULT '0' COMMENT '状态（0--正常1--冻结）',
  `create_user` int DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int DEFAULT '0' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES ('1', '1', 'admin', 'a66abb5684c45962d887564f08346e8d', '超级管理员', '18888888888', 'http://112.74.169.8:9000/asurplus-bucket/system/20210909/d6fb5fe2-758f-42c9-84b6-581e93cbb849.blob', '1', '123@11.com', '0', '1', '2021-01-16 21:42:04', '1', '2021-11-08 14:31:00', '0');
INSERT INTO `sys_user_info` VALUES ('2', '0', '13888888888', 'b97c90a3d0e487396a1da3280624a35e', '测试账号', '13888888886', 'http://112.74.169.8:9000/asurplus-bucket/system/20210909/d6fb5fe2-758f-42c9-84b6-581e93cbb849.blob', '0', '', '0', '1', '2020-09-30 09:35:28', '1', '2021-11-08 14:30:59', '0');
INSERT INTO `sys_user_info` VALUES ('3', '4', '3543', 'b97c90a3d0e487396a1da3280624a35e', 'gds', '3213424', 'http://112.74.169.8:9000/asurplus-bucket/system/20210909/d6fb5fe2-758f-42c9-84b6-581e93cbb849.blob', '0', '', '0', '0', '2022-10-19 12:54:03', '0', '2022-10-19 13:47:08', '1');
INSERT INTO `sys_user_info` VALUES ('4', '4', 'R43254', 'a845819999ff3fef27a4008e114e7ffe', '1', '19028292222', null, '0', '123214@Q.COM', '0', '1', '2022-10-19 13:48:07', '0', '2022-10-19 13:48:07', '0');
INSERT INTO `sys_user_info` VALUES ('5', '4', '13w232', '4b3e1ab8b3ddaa3816b4f8f9df60c8cf', '3232', '19023456789', null, '0', null, '0', '1', '2022-10-27 19:17:45', '0', '2022-10-27 19:17:45', '0');
INSERT INTO `sys_user_info` VALUES ('6', '0', '534543tr', '65f047c9ba69a5f7742323d136cb3c99', 'frewaf', '18872637453', '', '0', 'dfa@qq.com', '0', '1', '2022-10-27 19:18:37', '1', '2022-10-27 19:18:37', '0');
INSERT INTO `sys_user_info` VALUES ('7', '0', 'ewedw', 'f1f8621218fcf83fc5a26916298dd6f2', '132q3e2', '14567543212', '', '0', '3232ws4@qq.com', '0', '1', '2022-10-27 19:22:07', '1', '2022-10-27 19:22:07', '0');
INSERT INTO `sys_user_info` VALUES ('8', '0', '投入trew', '84131da2e0a076550d04d906844fd1d2', 'rf', null, null, '0', null, '0', '1', '2022-11-02 11:14:28', '0', '2022-11-02 11:14:28', '0');
INSERT INTO `sys_user_info` VALUES ('9', '4', '投入trewtr4te', '9abac21e4de637de3ba67f7eb50dcac4', 'rfetre', null, null, '0', null, '0', '1', '2022-11-02 11:14:46', '0', '2022-11-02 11:14:46', '0');
INSERT INTO `sys_user_info` VALUES ('10', '0', 'adminfrwetgfertg', '1ac1c272a85050427f2f8ba705bfdae6', 'fgewsrfwesr', null, null, '0', null, '0', '1', '2022-11-02 11:15:04', '0', '2022-11-02 11:15:04', '0');
INSERT INTO `sys_user_info` VALUES ('11', '0', 'adminrfw4tre4tg', '26cc8fa569177972ba7f6d74a0662076', 'fdasefc', null, null, '0', null, '0', '1', '2022-11-02 11:16:54', '0', '2022-11-02 11:16:54', '0');
INSERT INTO `sys_user_info` VALUES ('12', '0', 'wqrw4trwe4t', 'a83f779019700774cf48c69bb82ff7cf', 'fedsaf', '15567893456', '', '0', '', '0', '1', '2022-11-02 11:18:28', '1', '2022-11-02 11:18:28', '0');
INSERT INTO `sys_user_info` VALUES ('13', '0', 'adminr3rw4er', '57d3ca97d565825bd89acccc458fecc7', 'rwer', null, null, '0', null, '0', '1', '2022-11-02 11:19:56', '0', '2022-11-02 11:19:56', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
  `user_id` int DEFAULT '0' COMMENT '用户id',
  `role_id` int DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='用户-角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '4', '7');

-- ----------------------------
-- Table structure for test_table
-- ----------------------------
DROP TABLE IF EXISTS `test_table`;
CREATE TABLE `test_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resp_data` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of test_table
-- ----------------------------
INSERT INTO `test_table` VALUES ('2', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('3', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('4', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('5', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('6', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('7', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('8', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('9', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('10', '0808 0000 01 03 9c41 0102');
INSERT INTO `test_table` VALUES ('11', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('12', '0808 0000 0006 01 03 fffff ffff');
INSERT INTO `test_table` VALUES ('13', '0000 0000 0000 0000 0000');
INSERT INTO `test_table` VALUES ('14', '0000 0000 0000 0000 0000');
INSERT INTO `test_table` VALUES ('15', '0000 0000 0000 0000 0000');
INSERT INTO `test_table` VALUES ('16', '0808 0000 01 03 9c41 0102');
INSERT INTO `test_table` VALUES ('17', '0000 0000 0000 0000 0000');
INSERT INTO `test_table` VALUES ('18', '0000 0000 0000 0000 0000');
INSERT INTO `test_table` VALUES ('19', '0000 0000 0000 0000 0000');
INSERT INTO `test_table` VALUES ('20', '0000 0000 0000 220.4 0000');
INSERT INTO `test_table` VALUES ('21', '0808 0000 0006 01 03 9c41 0001');
INSERT INTO `test_table` VALUES ('22', '0808 0000 0006 01 03 9c41 0001');
