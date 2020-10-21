/*
 Navicat MySQL Data Transfer

 Source Server         : shenqingwen
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : querydsl_test

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 20/10/2020 19:38:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `t_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `t_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `t_age` int NULL DEFAULT NULL COMMENT '年龄',
  `t_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `t_pwd` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '用户登录密码',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '张三', 20, '广东珠海', 'root');
INSERT INTO `t_user` VALUES (2, '李四', 21, '江西南昌', '123');
INSERT INTO `t_user` VALUES (3, '王五', 19, '福建厦门', 'abc');

SET FOREIGN_KEY_CHECKS = 1;
