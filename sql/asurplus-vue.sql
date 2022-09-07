/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : asurplus-vue

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 08/11/2021 14:32:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` int(11) NULL DEFAULT 0 COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'APP名称',
  `version_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本名称',
  `version_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '版本号',
  `version_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本描述',
  `is_force` tinyint(4) NULL DEFAULT 0 COMMENT '是否强制更新（0-否1-是）',
  `ios` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '【IOS】下载地址',
  `android` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '【Android】下载地址',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'app版本信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_info`;
CREATE TABLE `sys_dept_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT 0 COMMENT '上级部门',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `link_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `sort` int(8) NULL DEFAULT 0 COMMENT '排序（值越大，越靠前）',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态（0--正常1--停用）',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_info
-- ----------------------------
INSERT INTO `sys_dept_info` VALUES (1, 0, '技术部', '张三', '15685963325', 2, 0, 0, '2020-11-24 16:19:16', 1, '2020-12-05 20:01:46', 0);
INSERT INTO `sys_dept_info` VALUES (2, 1, '项目一组', '李四', '13588895626', 3, 0, 0, '2020-11-24 16:19:40', 1, '2020-12-05 20:01:46', 0);
INSERT INTO `sys_dept_info` VALUES (3, 1, '项目二组', '王五', '15888889654', 2, 0, 0, '2020-11-24 16:20:01', 0, '2020-12-05 20:01:46', 0);
INSERT INTO `sys_dept_info` VALUES (4, 0, '人事部', '赵六', '16233355665', 1, 0, 0, '2020-11-24 17:11:46', 0, '2020-11-25 15:03:19', 0);
INSERT INTO `sys_dept_info` VALUES (5, 4, '人事一部', '王七', '15266668895', 1, 0, 0, '2020-11-24 17:12:10', 0, '2020-11-25 15:03:19', 0);
INSERT INTO `sys_dept_info` VALUES (6, 4, '人事二部', '李好', '14566333589', 0, 0, 0, '2020-11-24 17:12:39', 0, '2020-11-25 15:03:19', 0);
INSERT INTO `sys_dept_info` VALUES (7, 2, '项目成员一', '粒粒', '18588889999', 0, 0, 1, '2020-11-25 14:28:31', 1, '2020-12-05 20:01:46', 0);
INSERT INTO `sys_dept_info` VALUES (8, 3, '项目成员二', '老领', '15866633355', 1, 0, 1, '2020-11-25 14:31:17', 1, '2020-12-05 20:01:46', 0);
INSERT INTO `sys_dept_info` VALUES (9, 1, '项目三组', '威威', '15622266656', 1, 0, 1, '2020-11-25 15:25:29', 1, '2020-12-05 20:01:46', 0);
INSERT INTO `sys_dept_info` VALUES (10, 1, '项目四组', '咳咳', '18555666669', 0, 0, 1, '2020-11-25 15:25:44', 1, '2020-12-05 20:01:46', 0);
INSERT INTO `sys_dept_info` VALUES (11, 0, '销售部', '连聊', '18885668676', 3, 0, 1, '2020-11-25 15:26:09', 0, '2021-07-12 13:27:02', 0);
INSERT INTO `sys_dept_info` VALUES (12, 11, '销售一部', '普片', '14778786877', 0, 0, 1, '2020-11-25 15:26:27', 0, '2021-07-12 13:27:02', 0);
INSERT INTO `sys_dept_info` VALUES (13, 0, '经理部', NULL, NULL, 1, 0, 1, '2021-07-20 10:18:05', 0, '2021-07-20 10:18:05', 0);
INSERT INTO `sys_dept_info` VALUES (14, 13, '经理一部', '', '', 1, 0, 1, '2021-07-20 10:18:16', 1, '2021-07-20 10:34:24', 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remake` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0，正常，1已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'status', '数据状态', '', 1, '2021-07-18 22:22:20', 1, '2021-07-18 23:17:15', 0);
INSERT INTO `sys_dict` VALUES (2, 'sex', '用户性别', '', 1, '2021-07-18 23:16:58', 1, '2021-07-18 23:16:58', 0);
INSERT INTO `sys_dict` VALUES (3, 'visible', '菜单是否显示', '', 1, '2021-07-18 23:18:48', 1, '2021-07-18 23:18:48', 0);
INSERT INTO `sys_dict` VALUES (4, 'is_success', '是否成功', NULL, 1, '2021-07-20 11:45:03', 0, '2021-07-20 11:45:03', 0);
INSERT INTO `sys_dict` VALUES (5, 'is_run', '是否启动', '', 1, '2021-07-22 21:24:22', 1, '2021-07-22 21:24:22', 0);
INSERT INTO `sys_dict` VALUES (6, 'oper_type', '操作日志--操作类型', '', 1, '2021-07-26 11:50:45', 1, '2021-07-26 11:50:45', 0);
INSERT INTO `sys_dict` VALUES (7, 'is_builtIn', '是否内置', NULL, 1, '2021-07-26 17:07:11', 0, '2021-07-26 17:07:11', 0);
INSERT INTO `sys_dict` VALUES (8, 'is_force', '是否强制更新', NULL, 1, '2021-07-26 21:57:30', 0, '2021-07-26 21:57:30', 0);

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `list_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '键值样式',
  `remake` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, 'status', '0', '正常', 'primary', NULL, 1, '2021-07-18 23:16:35', 0, '2021-07-18 23:17:21');
INSERT INTO `sys_dict_detail` VALUES (2, 'status', '1', '停用', 'danger', '', 1, '2021-07-18 23:16:43', 1, '2021-07-18 23:17:22');
INSERT INTO `sys_dict_detail` VALUES (3, 'sex', '0', '保密', 'info', NULL, 1, '2021-07-18 23:17:38', 0, '2021-07-18 23:17:38');
INSERT INTO `sys_dict_detail` VALUES (4, 'sex', '1', '男', 'primary', '', 1, '2021-07-18 23:17:43', 1, '2021-07-18 23:17:43');
INSERT INTO `sys_dict_detail` VALUES (5, 'sex', '2', '女', 'warning', NULL, 1, '2021-07-18 23:17:56', 0, '2021-07-18 23:17:56');
INSERT INTO `sys_dict_detail` VALUES (6, 'visible', '0', '显示', 'primary', NULL, 1, '2021-07-18 23:18:56', 0, '2021-07-18 23:18:56');
INSERT INTO `sys_dict_detail` VALUES (7, 'visible', '1', '隐藏', 'warning', NULL, 1, '2021-07-18 23:19:03', 0, '2021-07-18 23:19:03');
INSERT INTO `sys_dict_detail` VALUES (8, 'is_success', '0', '成功', 'primary', NULL, 1, '2021-07-20 11:45:14', 0, '2021-07-20 11:45:14');
INSERT INTO `sys_dict_detail` VALUES (9, 'is_success', '1', '失败', 'danger', '', 1, '2021-07-20 11:45:19', 1, '2021-07-20 11:45:19');
INSERT INTO `sys_dict_detail` VALUES (10, 'is_run', '0', '启动', 'primary', NULL, 1, '2021-07-22 21:24:42', 0, '2021-07-22 21:24:42');
INSERT INTO `sys_dict_detail` VALUES (11, 'is_run', '1', '停止', 'warning', NULL, 1, '2021-07-22 21:24:52', 0, '2021-07-22 21:24:52');
INSERT INTO `sys_dict_detail` VALUES (12, 'oper_type', '0', '其他', 'info', '', 1, '2021-07-26 11:51:39', 1, '2021-07-26 12:31:23');
INSERT INTO `sys_dict_detail` VALUES (13, 'oper_type', '1', '新增', 'primary', NULL, 1, '2021-07-26 11:51:51', 0, '2021-07-26 12:31:27');
INSERT INTO `sys_dict_detail` VALUES (14, 'oper_type', '2', '修改', 'success', '', 1, '2021-07-26 11:52:08', 1, '2021-07-26 12:31:29');
INSERT INTO `sys_dict_detail` VALUES (15, 'oper_type', '3', '删除', 'danger', '', 1, '2021-07-26 11:52:30', 1, '2021-07-26 12:31:29');
INSERT INTO `sys_dict_detail` VALUES (16, 'oper_type', '4', '授权', 'primary', '', 1, '2021-07-26 11:52:58', 1, '2021-07-26 12:31:30');
INSERT INTO `sys_dict_detail` VALUES (17, 'oper_type', '5', '导出', 'warning', '', 1, '2021-07-26 11:53:20', 1, '2021-07-26 12:31:30');
INSERT INTO `sys_dict_detail` VALUES (18, 'oper_type', '6', '导入', 'warning', NULL, 1, '2021-07-26 11:53:42', 0, '2021-07-26 12:31:31');
INSERT INTO `sys_dict_detail` VALUES (19, 'oper_type', '7', '强退', 'danger', NULL, 1, '2021-07-26 11:53:55', 0, '2021-07-26 12:31:31');
INSERT INTO `sys_dict_detail` VALUES (20, 'oper_type', '8', '生成代码', 'info', NULL, 1, '2021-07-26 11:54:06', 0, '2021-07-26 12:31:32');
INSERT INTO `sys_dict_detail` VALUES (21, 'oper_type', '9', '清空数据', 'danger', NULL, 1, '2021-07-26 11:54:28', 0, '2021-07-26 12:31:33');
INSERT INTO `sys_dict_detail` VALUES (22, 'is_builtIn', '0', '是', 'primary', NULL, 1, '2021-07-26 17:07:28', 0, '2021-07-26 17:07:28');
INSERT INTO `sys_dict_detail` VALUES (23, 'is_builtIn', '1', '否', 'info', NULL, 1, '2021-07-26 17:07:33', 0, '2021-07-26 17:07:33');
INSERT INTO `sys_dict_detail` VALUES (24, 'is_force', '0', '否', 'info', NULL, 1, '2021-07-26 21:57:39', 0, '2021-07-26 21:57:39');
INSERT INTO `sys_dict_detail` VALUES (25, 'is_force', '1', '是', 'primary', NULL, 1, '2021-07-26 21:57:46', 0, '2021-07-26 21:57:46');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户',
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地理位置',
  `browser` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `device` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态（0--成功1--失败）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `type` tinyint(4) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `fun` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `location` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `spend_time` int(11) NULL DEFAULT 0 COMMENT '耗时',
  `oper_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`  (
  `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `param_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `param_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `type` tinyint(4) NULL DEFAULT 0 COMMENT '系统内置（0-是 1-否）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES (1, '用户默认密码', 'user.default.password', '123456', 0, '新用户的默认登录密码', 1, '2021-07-26 17:18:30', 1, '2021-07-26 17:18:30', 0);

-- ----------------------------
-- Table structure for sys_permission_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_info`;
CREATE TABLE `sys_permission_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `pid` int(11) NULL DEFAULT 0 COMMENT '父菜单ID',
  `sort` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` tinyint(4) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` tinyint(4) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` tinyint(4) NULL DEFAULT 0 COMMENT '菜单状态（0显示 1隐藏）',
  `sign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40105 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_info
-- ----------------------------
INSERT INTO `sys_permission_info` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', 0, '', 'system', '系统管理目录', 1, '2021-07-18 13:22:21', 1, '2021-07-18 13:32:10', 0);
INSERT INTO `sys_permission_info` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 0, 'M', 0, '', 'monitor', '系统监控目录', 1, '2021-07-18 13:22:21', 1, '2021-07-18 13:32:10', 0);
INSERT INTO `sys_permission_info` VALUES (3, '系统配置', 0, 3, 'config', NULL, 1, 0, 'M', 0, NULL, 'code', '', 1, '2021-07-26 16:40:07', 0, '2021-07-26 16:50:12', 0);
INSERT INTO `sys_permission_info` VALUES (4, '系统工具', 0, 4, 'tool', '', 1, 0, 'M', 0, '', 'tool', '系统工具目录', 1, '2021-07-18 13:22:21', 1, '2021-07-26 16:50:11', 0);
INSERT INTO `sys_permission_info` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', 0, 'system:user:list', 'user', '用户管理菜单', 0, '2021-07-20 21:01:21', 0, '2021-07-20 21:08:03', 0);
INSERT INTO `sys_permission_info` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', 0, 'system:role:list', 'peoples', '角色管理菜单', 0, '2021-07-20 21:01:24', 0, '2021-07-20 21:08:04', 0);
INSERT INTO `sys_permission_info` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', 0, 'system:menu:list', 'tree-table', '菜单管理菜单', 0, '2021-07-20 21:01:25', 0, '2021-07-20 21:08:05', 0);
INSERT INTO `sys_permission_info` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', 0, 'system:dept:list', 'tree', '部门管理菜单', 0, '2021-07-20 21:01:29', 0, '2021-07-20 21:08:06', 0);
INSERT INTO `sys_permission_info` VALUES (104, '字典管理', 1, 5, 'dict', 'system/dict/index', 1, 0, 'C', 0, 'system:dict:list', 'dict', '字典管理菜单', 0, '2021-07-20 21:01:27', 0, '2021-07-20 21:08:07', 0);
INSERT INTO `sys_permission_info` VALUES (200, '登录日志', 2, 1, 'logininfo', 'monitor/logininfor/index', 1, 0, 'C', 0, 'monitor:logininfor:list', 'logininfor', '登录日志菜单', 0, '2021-07-20 21:19:43', 0, '2021-07-20 21:21:30', 0);
INSERT INTO `sys_permission_info` VALUES (201, '操作日志', 2, 2, 'operlog', 'monitor/operlog/index', 1, 0, 'C', 0, 'monitor:operlog:list', 'form', '操作日志菜单', 0, '2021-07-20 21:19:50', 0, '2021-07-20 21:21:35', 0);
INSERT INTO `sys_permission_info` VALUES (202, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 0, 'C', 0, 'monitor:druid:list', 'druid', '数据监控菜单', 0, '2021-07-20 21:33:47', 0, '2021-07-20 21:37:38', 0);
INSERT INTO `sys_permission_info` VALUES (203, '定时任务', 2, 4, 'job', 'monitor/job/index', 1, 0, 'C', 0, 'monitor:job:list', 'job', '定时任务菜单', 0, '2021-07-20 21:29:19', 0, '2021-07-20 21:33:57', 0);
INSERT INTO `sys_permission_info` VALUES (204, '服务监控', 2, 5, 'server', 'monitor/server/index', 1, 0, 'C', 0, 'monitor:server:list', 'server', '服务监控菜单', 0, '2021-07-20 21:32:51', 0, '2021-07-20 21:36:04', 0);
INSERT INTO `sys_permission_info` VALUES (205, '缓存监控', 2, 6, 'cache', 'monitor/cache/index', 1, 0, 'C', 0, 'monitor:cache:list', 'redis', '缓存监控菜单', 0, '2021-07-20 21:33:01', 0, '2021-07-20 21:36:45', 0);
INSERT INTO `sys_permission_info` VALUES (300, '表单构建', 4, 1, 'build', 'tool/build/index', 1, 0, 'C', 0, 'tool:build:list', 'build', '表单构建菜单', 0, '2021-07-20 21:39:47', 0, '2021-07-26 16:50:26', 0);
INSERT INTO `sys_permission_info` VALUES (301, '代码生成', 4, 2, 'gen', 'tool/gen/index', 1, 0, 'C', 0, 'tool:gen:list', 'code', '代码生成菜单', 0, '2021-07-20 21:39:58', 0, '2021-07-26 16:50:26', 0);
INSERT INTO `sys_permission_info` VALUES (302, '接口文档', 4, 3, 'swagger', 'tool/swagger/index', 1, 0, 'C', 0, 'tool:swagger:list', 'swagger', '接口文档菜单', 0, '2021-07-20 21:40:12', 0, '2021-07-26 16:50:26', 0);
INSERT INTO `sys_permission_info` VALUES (400, '参数配置', 3, 1, 'param', 'config/param/index', 1, 0, 'C', 0, 'config:param:list', 'log', '参数配置菜单', 0, '2021-07-20 21:01:21', 1, '2021-07-26 16:52:35', 0);
INSERT INTO `sys_permission_info` VALUES (401, 'APP版本', 3, 2, 'app', 'config/app/index', 1, 0, 'C', 0, 'config:app:list', 'phone', 'APP版本菜单', 0, '2021-07-20 21:01:21', 1, '2021-07-26 21:59:10', 0);
INSERT INTO `sys_permission_info` VALUES (10000, '查询', 100, 1, '', NULL, 1, 0, 'F', 0, 'system:user:query', '#', '用户查询', 0, '2021-07-20 21:08:03', 0, '2021-07-20 21:24:31', 0);
INSERT INTO `sys_permission_info` VALUES (10001, '新增', 100, 2, '', NULL, 1, 0, 'F', 0, 'system:user:add', '#', '', 0, '2021-07-20 21:09:03', 0, '2021-07-20 21:24:35', 0);
INSERT INTO `sys_permission_info` VALUES (10002, '修改', 100, 3, '', '', 1, 0, 'F', 0, 'system:user:edit', '#', '', 0, '2021-07-20 21:09:19', 1, '2021-07-20 21:24:41', 0);
INSERT INTO `sys_permission_info` VALUES (10003, '删除', 100, 4, '', NULL, 1, 0, 'F', 0, 'system:user:del', '#', '', 0, '2021-07-20 21:09:48', 0, '2021-07-20 21:24:45', 0);
INSERT INTO `sys_permission_info` VALUES (10004, '重置密码', 100, 5, '', NULL, 1, 0, 'F', 0, 'system:user:reset', '#', '', 0, '2021-07-20 21:10:15', 0, '2021-07-20 21:24:48', 0);
INSERT INTO `sys_permission_info` VALUES (10005, '导入', 100, 6, '', NULL, 1, 0, 'F', 0, 'system:user:import', '#', '', 0, '2021-07-20 21:10:25', 0, '2021-07-20 21:24:51', 0);
INSERT INTO `sys_permission_info` VALUES (10006, '导出', 100, 7, '', NULL, 1, 0, 'F', 0, 'system:user:export', '#', '', 0, '2021-07-20 21:10:32', 0, '2021-07-20 21:24:55', 0);
INSERT INTO `sys_permission_info` VALUES (10100, '查询', 101, 1, '', NULL, 1, 0, 'F', 0, 'system:role:query', '#', '角色查询', 0, '2021-07-20 21:12:19', 0, '2021-07-20 21:25:00', 0);
INSERT INTO `sys_permission_info` VALUES (10101, '新增', 101, 2, '', NULL, 1, 0, 'F', 0, 'system:role:add', '#', '', 0, '2021-07-20 21:12:27', 0, '2021-07-20 21:25:03', 0);
INSERT INTO `sys_permission_info` VALUES (10102, '修改', 101, 3, '', NULL, 1, 0, 'F', 0, 'system:role:edit', '#', '', 0, '2021-07-20 21:12:30', 0, '2021-07-20 21:25:10', 0);
INSERT INTO `sys_permission_info` VALUES (10103, '删除', 101, 4, '', NULL, 1, 0, 'F', 0, 'system:role:del', '#', '', 0, '2021-07-20 21:12:43', 0, '2021-07-20 21:25:13', 0);
INSERT INTO `sys_permission_info` VALUES (10104, '导出', 101, 5, '', NULL, 1, 0, 'F', 0, 'system:role:export', '#', '', 0, '2021-07-20 21:12:52', 0, '2021-07-20 21:25:16', 0);
INSERT INTO `sys_permission_info` VALUES (10200, '查询', 102, 1, '', NULL, 1, 0, 'F', 0, 'system:menu:query', '#', '菜单查询', 0, '2021-07-20 21:13:49', 0, '2021-07-20 21:25:22', 0);
INSERT INTO `sys_permission_info` VALUES (10201, '新增', 102, 2, '', NULL, 1, 0, 'F', 0, 'system:menu:add', '#', '', 0, '2021-07-20 21:13:51', 0, '2021-07-20 21:25:25', 0);
INSERT INTO `sys_permission_info` VALUES (10202, '修改', 102, 3, '', NULL, 1, 0, 'F', 0, 'system:menu:edit', '#', '', 0, '2021-07-20 21:13:53', 0, '2021-07-20 21:25:29', 0);
INSERT INTO `sys_permission_info` VALUES (10203, '删除', 102, 4, '', NULL, 1, 0, 'F', 0, 'system:menu:del', '#', '', 0, '2021-07-20 21:13:59', 0, '2021-07-20 21:25:32', 0);
INSERT INTO `sys_permission_info` VALUES (10300, '查询', 103, 1, '', NULL, 1, 0, 'F', 0, 'system:dept:query', '#', '部门查询', 0, '2021-07-20 21:15:40', 0, '2021-07-20 21:25:37', 0);
INSERT INTO `sys_permission_info` VALUES (10301, '新增', 103, 2, '', NULL, 1, 0, 'F', 0, 'system:dept:add', '#', '', 0, '2021-07-20 21:15:45', 0, '2021-07-20 21:25:40', 0);
INSERT INTO `sys_permission_info` VALUES (10302, '修改', 103, 3, '', NULL, 1, 0, 'F', 0, 'system:dept:edit', '#', '', 0, '2021-07-20 21:15:47', 0, '2021-07-20 21:25:43', 0);
INSERT INTO `sys_permission_info` VALUES (10303, '删除', 103, 4, '', NULL, 1, 0, 'F', 0, 'system:dept:del', '#', '', 0, '2021-07-20 21:15:51', 0, '2021-07-20 21:25:46', 0);
INSERT INTO `sys_permission_info` VALUES (10400, '查询', 104, 1, '', NULL, 1, 0, 'F', 0, 'system:dict:query', '#', '字典查询', 0, '2021-07-20 21:16:54', 0, '2021-07-20 21:25:51', 0);
INSERT INTO `sys_permission_info` VALUES (10401, '新增', 104, 2, '', NULL, 1, 0, 'F', 0, 'system:dict:add', '#', '', 0, '2021-07-20 21:17:00', 0, '2021-07-20 21:27:41', 0);
INSERT INTO `sys_permission_info` VALUES (10402, '修改', 104, 3, '', NULL, 1, 0, 'F', 0, 'system:dict:edit', '#', '', 0, '2021-07-20 21:17:03', 0, '2021-07-20 21:27:42', 0);
INSERT INTO `sys_permission_info` VALUES (10403, '删除', 104, 4, '', NULL, 1, 0, 'F', 0, 'system:dict:del', '#', '', 0, '2021-07-20 21:17:05', 0, '2021-07-20 21:27:43', 0);
INSERT INTO `sys_permission_info` VALUES (10404, '导出', 104, 5, '', NULL, 1, 0, 'F', 0, 'system:dict:export', '#', '', 0, '2021-07-20 21:17:15', 0, '2021-07-20 21:27:43', 0);
INSERT INTO `sys_permission_info` VALUES (20000, '查询', 200, 1, '', NULL, 1, 0, 'F', 0, 'monitor:logininfor:query', '#', '登录日志查询', 0, '2021-07-20 21:22:43', 0, '2021-07-20 21:27:44', 0);
INSERT INTO `sys_permission_info` VALUES (20001, '删除', 200, 2, '', NULL, 1, 0, 'F', 0, 'monitor:logininfor:del', '#', '', 0, '2021-07-20 21:22:47', 0, '2021-07-20 21:27:45', 0);
INSERT INTO `sys_permission_info` VALUES (20002, '清空', 200, 3, '', NULL, 1, 0, 'F', 0, 'monitor:logininfor:clear', '#', '', 0, '2021-07-20 21:23:18', 0, '2021-07-20 21:27:46', 0);
INSERT INTO `sys_permission_info` VALUES (20003, '导出', 200, 4, '', NULL, 1, 0, 'F', 0, 'monitor:logininfor:export', '#', '', 0, '2021-07-20 21:23:18', 0, '2021-07-20 21:27:46', 0);
INSERT INTO `sys_permission_info` VALUES (20100, '查询', 201, 1, '', NULL, 1, 0, 'F', 0, 'monitor:operlog:query', '#', '操作日志查询', 0, '2021-07-20 21:26:44', 0, '2021-07-20 21:27:47', 0);
INSERT INTO `sys_permission_info` VALUES (20101, '删除', 201, 2, '', NULL, 1, 0, 'F', 0, 'monitor:operlog:del', '#', '', 0, '2021-07-20 21:26:58', 0, '2021-07-20 21:27:48', 0);
INSERT INTO `sys_permission_info` VALUES (20102, '清空', 202, 3, '', NULL, 1, 0, 'F', 0, 'monitor:operlog:clear', '#', '', 0, '2021-07-20 21:27:20', 0, '2021-07-20 21:27:48', 0);
INSERT INTO `sys_permission_info` VALUES (20103, '导出', 202, 4, '', NULL, 1, 0, 'F', 0, 'monitor:operlog:export', '#', '', 0, '2021-07-20 21:27:34', 0, '2021-07-20 21:27:50', 0);
INSERT INTO `sys_permission_info` VALUES (20300, '查询', 203, 1, '', NULL, 1, 0, 'F', 0, 'monitor:job:query', '#', '定时任务查询', 0, '2021-07-20 21:30:41', 0, '2021-07-20 21:32:09', 0);
INSERT INTO `sys_permission_info` VALUES (20301, '新增', 203, 2, '', NULL, 1, 0, 'F', 0, 'monitor:job:add', '#', '', 0, '2021-07-20 21:30:45', 0, '2021-07-20 21:31:44', 0);
INSERT INTO `sys_permission_info` VALUES (20302, '修改', 203, 3, '', NULL, 1, 0, 'F', 0, 'monitor:job:edit', '#', '', 0, '2021-07-20 21:30:48', 0, '2021-07-20 21:31:47', 0);
INSERT INTO `sys_permission_info` VALUES (20303, '删除', 203, 4, '', NULL, 1, 0, 'F', 0, 'monitor:job:del', '#', '', 0, '2021-07-20 21:30:50', 0, '2021-07-20 21:31:52', 0);
INSERT INTO `sys_permission_info` VALUES (20304, '启/停止', 203, 5, '', NULL, 1, 0, 'F', 0, 'monitor:job:status', '#', '', 0, '2021-07-20 21:31:08', 0, '2021-07-20 21:31:56', 0);
INSERT INTO `sys_permission_info` VALUES (20305, '导出', 203, 6, '', NULL, 1, 0, 'F', 0, 'monitor:job:export', '#', '', 0, '2021-07-20 21:31:11', 0, '2021-07-20 21:32:01', 0);
INSERT INTO `sys_permission_info` VALUES (30101, '查询', 301, 1, '', NULL, 1, 0, 'F', 0, 'tool:gen:query', '#', '代码生成查询', 0, '2021-07-20 21:42:36', 0, '2021-07-20 21:44:29', 0);
INSERT INTO `sys_permission_info` VALUES (30102, '修改', 301, 2, '', NULL, 1, 0, 'F', 0, 'tool:gen:edit', '#', '', 0, '2021-07-20 21:42:43', 0, '2021-07-20 21:43:45', 0);
INSERT INTO `sys_permission_info` VALUES (30103, '删除', 301, 3, '', NULL, 1, 0, 'F', 0, 'tool:gen:del', '#', '', 0, '2021-07-20 21:42:54', 0, '2021-07-20 21:43:48', 0);
INSERT INTO `sys_permission_info` VALUES (30104, '导入', 301, 4, '', NULL, 1, 0, 'F', 0, 'tool:gen:import', '#', '', 0, '2021-07-20 21:42:57', 0, '2021-07-20 21:43:54', 0);
INSERT INTO `sys_permission_info` VALUES (30105, '预览', 301, 5, '', NULL, 1, 0, 'F', 0, 'tool:gen:preview', '#', '', 0, '2021-07-20 21:43:02', 0, '2021-07-20 21:44:18', 0);
INSERT INTO `sys_permission_info` VALUES (30106, '生成', 301, 6, '', NULL, 1, 0, 'F', 0, 'tool:gen:code', '#', '', 0, '2021-07-20 21:43:08', 0, '2021-07-20 21:44:22', 0);
INSERT INTO `sys_permission_info` VALUES (40000, '查询', 400, 1, '', NULL, 1, 0, 'F', 0, 'config:param:query', '#', '参数配置查询', 1, '2021-07-26 16:58:40', 0, '2021-07-26 17:01:04', 0);
INSERT INTO `sys_permission_info` VALUES (40001, '新增', 400, 2, '', NULL, 1, 0, 'F', 0, 'config:param:add', '#', '', 1, '2021-07-26 16:58:40', 0, '2021-07-26 16:59:21', 0);
INSERT INTO `sys_permission_info` VALUES (40002, '修改', 400, 3, '', NULL, 1, 0, 'F', 0, 'config:param:edit', '#', '', 1, '2021-07-26 16:58:40', 0, '2021-07-26 17:00:46', 0);
INSERT INTO `sys_permission_info` VALUES (40003, '删除', 400, 4, '', NULL, 1, 0, 'F', 0, 'config:param:del', '#', '', 1, '2021-07-26 16:58:40', 0, '2021-07-26 17:00:50', 0);
INSERT INTO `sys_permission_info` VALUES (40004, '导出', 400, 5, '', NULL, 1, 0, 'F', 0, 'config:param:export', '#', '', 1, '2021-07-26 16:58:40', 0, '2021-07-26 17:00:55', 0);
INSERT INTO `sys_permission_info` VALUES (40100, '查询', 401, 1, '', NULL, 1, 0, 'F', 0, 'config:app:query', '#', 'app版本查询', 1, '2021-07-26 16:58:40', 0, '2021-07-26 17:01:04', 0);
INSERT INTO `sys_permission_info` VALUES (40101, '新增', 401, 2, '', NULL, 1, 0, 'F', 0, 'config:app:add', '#', 'app版本查询', 1, '2021-07-26 16:58:40', 0, '2021-07-26 21:55:05', 0);
INSERT INTO `sys_permission_info` VALUES (40102, '修改', 401, 3, '', NULL, 1, 0, 'F', 0, 'config:app:edit', '#', 'app版本查询', 1, '2021-07-26 16:58:40', 0, '2021-07-26 21:55:23', 0);
INSERT INTO `sys_permission_info` VALUES (40103, '删除', 401, 4, '', NULL, 1, 0, 'F', 0, 'config:app:del', '#', 'app版本查询', 1, '2021-07-26 16:58:40', 0, '2021-07-26 21:55:18', 0);
INSERT INTO `sys_permission_info` VALUES (40104, '导出', 401, 5, '', NULL, 1, 0, 'F', 0, 'config:app:export', '#', 'app版本查询', 1, '2021-07-26 16:58:40', 0, '2021-07-26 21:55:26', 0);

-- ----------------------------
-- Table structure for sys_quartz_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_info`;
CREATE TABLE `sys_quartz_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务类名',
  `cron_expression` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `param` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '启动状态（0--启动1--停止）',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT 0 COMMENT '显示顺序(越大越靠前)',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_quartz_info
-- ----------------------------
INSERT INTO `sys_quartz_info` VALUES (1, 'com.asurplus.monitor.job.TestJob', '0/10 * * * * ?', '', 1, '无参测试', 0, 1, '2021-08-05 22:02:56', 1, '2021-08-05 22:02:56', 0);
INSERT INTO `sys_quartz_info` VALUES (2, 'com.asurplus.monitor.job.TestParamJob', '0/10 * * * * ?', '{\"test\": \"下午好！\"}', 1, '有参测试', 0, 1, '2021-08-05 22:12:35', 1, '2021-08-05 22:12:35', 0);

-- ----------------------------
-- Table structure for sys_role_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_info`;
CREATE TABLE `sys_role_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `sign` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标志',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态（0--正常1--停用）',
  `sort` int(8) NULL DEFAULT 0 COMMENT '排序（越大越靠前）',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_info
-- ----------------------------
INSERT INTO `sys_role_info` VALUES (1, '超级管理员', 'administrator', '超级管理员，拥有至高无上的权力', 0, 0, 0, '2020-09-06 19:40:31', 0, '2020-09-06 19:40:31', 0);
INSERT INTO `sys_role_info` VALUES (2, '测试人员', 'test', '只有查看数据的权限，无CRUD权限', 0, 1, 0, '2020-09-06 19:40:31', 1, '2021-04-08 11:13:05', 0);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
  `role_id` int(11) NULL DEFAULT 0 COMMENT '角色id',
  `permission_id` int(11) NULL DEFAULT 0 COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-权限关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_id` int(11) NULL DEFAULT 0 COMMENT '所属部门',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` tinyint(4) NULL DEFAULT 0 COMMENT '性别（0--未知1--男2--女）',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态（0--正常1--冻结）',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES (1, 1, 'admin', 'a66abb5684c45962d887564f08346e8d', '超级管理员', '18888888888', 'http://112.74.169.8:9000/asurplus-bucket/system/20210909/d6fb5fe2-758f-42c9-84b6-581e93cbb849.blob', 1, '', 0, 1, '2021-01-16 21:42:04', 1, '2021-11-08 14:31:00', 0);
INSERT INTO `sys_user_info` VALUES (2, 0, '13888888888', 'b97c90a3d0e487396a1da3280624a35e', '测试账号', '13888888886', 'http://112.74.169.8:9000/asurplus-bucket/system/20210909/d6fb5fe2-758f-42c9-84b6-581e93cbb849.blob', 0, '', 0, 1, '2020-09-30 09:35:28', 1, '2021-11-08 14:30:59', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '用户id',
  `role_id` int(11) NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
