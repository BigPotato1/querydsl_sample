SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_family_member
-- ----------------------------
DROP TABLE IF EXISTS `t_family_member`;
CREATE TABLE `t_family_member`  (
                                    `t_id` int NOT NULL,
                                    `t_relationship` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `t_user_id` int NULL DEFAULT NULL,
                                    PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_family_member
-- ----------------------------
INSERT INTO `t_family_member` VALUES (1, '父', 1);
INSERT INTO `t_family_member` VALUES (2, '母', 1);
INSERT INTO `t_family_member` VALUES (3, '父', 2);
INSERT INTO `t_family_member` VALUES (4, '母', 2);
INSERT INTO `t_family_member` VALUES (5, '妻', 2);
INSERT INTO `t_family_member` VALUES (6, '妻', 3);
INSERT INTO `t_family_member` VALUES (7, '子', 3);

SET FOREIGN_KEY_CHECKS = 1;
