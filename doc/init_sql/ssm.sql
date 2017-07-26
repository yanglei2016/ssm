/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : yanglei

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-07-26 15:08:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(10) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` varchar(10) DEFAULT NULL COMMENT '上级菜单ID',
  `req_url` varchar(200) DEFAULT NULL COMMENT '请求URL',
  `menu_type` varchar(1) DEFAULT NULL COMMENT '菜单类型：M=菜单，B=按钮',
  `menu_level` int(1) DEFAULT NULL COMMENT '菜单级别：0=根菜单，1=一级菜单，2=二级菜单',
  `order_seq` int(2) DEFAULT NULL COMMENT '同级序号',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0', '管理系统', '0', null, 'M', '0', '0');
INSERT INTO `sys_menu` VALUES ('01', '系统管理', '0', null, 'M', '1', '1');
INSERT INTO `sys_menu` VALUES ('0101', '用户管理', '01', 'system/user/userList.do', 'M', '2', '1');
INSERT INTO `sys_menu` VALUES ('010101', '新增', '0101', '', 'B', '3', '1');
INSERT INTO `sys_menu` VALUES ('010102', '修改', '0101', '', 'B', '3', '2');
INSERT INTO `sys_menu` VALUES ('010103', '删除', '0101', '', 'B', '3', '3');
INSERT INTO `sys_menu` VALUES ('010104', '导出', '0101', '', 'B', '3', '4');
INSERT INTO `sys_menu` VALUES ('0102', '角色管理', '01', 'system/role/roleList.do', 'M', '2', '2');
INSERT INTO `sys_menu` VALUES ('010201', '新增', '0102', '', 'B', '3', '1');
INSERT INTO `sys_menu` VALUES ('010202', '修改', '0102', '', 'B', '3', '2');
INSERT INTO `sys_menu` VALUES ('010203', '删除', '0102', '', 'B', '3', '3');
INSERT INTO `sys_menu` VALUES ('0103', '菜单管理', '01', 'system/menu/menuTree.do', 'M', '2', '3');
INSERT INTO `sys_menu` VALUES ('02', '一级菜单2', '0', null, 'M', '1', '2');
INSERT INTO `sys_menu` VALUES ('0201', '二级菜单4', '02', null, 'M', '2', '1');
INSERT INTO `sys_menu` VALUES ('0202', '二级菜单5', '02', null, 'M', '2', '2');
INSERT INTO `sys_menu` VALUES ('03', '一级菜单3', '0', null, 'M', '1', '3');
INSERT INTO `sys_menu` VALUES ('0301', '二级菜单6', '03', null, 'M', '2', '1');
INSERT INTO `sys_menu` VALUES ('0302', '二级菜单77', '03', 'asdf', 'M', '2', '2');
INSERT INTO `sys_menu` VALUES ('0303', '二级', '03', 'www.baidu.com', 'M', '2', '3');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '管理员');
INSERT INTO `sys_role` VALUES ('2', '研发', '研发');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(4) NOT NULL COMMENT '角色ID',
  `menu_id` varchar(10) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '0');
INSERT INTO `sys_role_menu` VALUES ('2', '01');
INSERT INTO `sys_role_menu` VALUES ('2', '0101');
INSERT INTO `sys_role_menu` VALUES ('2', '010101');
INSERT INTO `sys_role_menu` VALUES ('2', '010102');
INSERT INTO `sys_role_menu` VALUES ('2', '010103');
INSERT INTO `sys_role_menu` VALUES ('2', '0102');
INSERT INTO `sys_role_menu` VALUES ('2', '010201');
INSERT INTO `sys_role_menu` VALUES ('2', '010202');
INSERT INTO `sys_role_menu` VALUES ('2', '010203');
INSERT INTO `sys_role_menu` VALUES ('2', '0103');
INSERT INTO `sys_role_menu` VALUES ('1', '0');
INSERT INTO `sys_role_menu` VALUES ('1', '01');
INSERT INTO `sys_role_menu` VALUES ('1', '0101');
INSERT INTO `sys_role_menu` VALUES ('1', '010101');
INSERT INTO `sys_role_menu` VALUES ('1', '010102');
INSERT INTO `sys_role_menu` VALUES ('1', '010103');
INSERT INTO `sys_role_menu` VALUES ('1', '010104');
INSERT INTO `sys_role_menu` VALUES ('1', '0102');
INSERT INTO `sys_role_menu` VALUES ('1', '010201');
INSERT INTO `sys_role_menu` VALUES ('1', '010202');
INSERT INTO `sys_role_menu` VALUES ('1', '0103');
INSERT INTO `sys_role_menu` VALUES ('1', '02');
INSERT INTO `sys_role_menu` VALUES ('1', '0201');
INSERT INTO `sys_role_menu` VALUES ('1', '0202');
INSERT INTO `sys_role_menu` VALUES ('1', '03');
INSERT INTO `sys_role_menu` VALUES ('1', '0301');
INSERT INTO `sys_role_menu` VALUES ('1', '0302');
INSERT INTO `sys_role_menu` VALUES ('1', '0303');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('admin', 'admin', '96E79218965EB72C92A549DD5A330112');
INSERT INTO `sys_user` VALUES ('test', '测试', '098F6BCD4621D373CADE4E832627B4F6');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `role_id` int(4) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('admin', '1');
INSERT INTO `sys_user_role` VALUES ('test', '1');
