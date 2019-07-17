/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : mytest

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-06-25 15:47:35
*/

USE mytest;

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `permission_id` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `permission_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `url` VARCHAR(255) DEFAULT NULL,
  `level` INT(11) DEFAULT NULL COMMENT '权限级别：一级菜单，二级菜单',
  `parent_id` INT(11) DEFAULT NULL,
  `type` INT(1) DEFAULT NULL,
  `order_num` INT(11) DEFAULT NULL,
  `icon` VARCHAR(255) DEFAULT NULL,
  `status` INT(1) DEFAULT '1' COMMENT '状态：1有效;0删除',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` VARCHAR(20) DEFAULT NULL,
  `role_name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `status` INT(1) DEFAULT '1' COMMENT '状态：1有效;0删除',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN', 'ROLE_ADMIN', '1', '2019-06-25 15:35:48', '2019-06-25 15:35:52');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', 'ROLE_USER', 'ROLE_USER', '1', '2019-06-25 15:35:55', '2019-06-25 15:35:57');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` VARCHAR(20) DEFAULT NULL,
  `permission_id` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userName` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `salt` VARCHAR(255) DEFAULT NULL COMMENT '加密盐值',
  `status` INT(1) NOT NULL DEFAULT '1' COMMENT '用户状态：1有效;0删除',
  `create_Time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `user_role_id` (`userId`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'tom', 'tom', '123', 'salt', '1', '2019-06-25 15:05:29', '2019-06-25 15:05:29');
INSERT INTO `sys_user` VALUES ('2', 'jack', 'jack', '123', 'salt', '1', '2019-06-25 15:36:53', '2019-06-25 15:36:56');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) DEFAULT NULL,
  `role_id` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
